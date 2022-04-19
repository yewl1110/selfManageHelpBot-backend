package com.bot.demo.service;

import com.bot.demo.respository.TodosRepo;
import com.bot.demo.vo.Todo;
import com.bot.demo.vo.base.Pagination;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodosRepo todosRepo;
    private final MongoTemplate mongoTemplate;

    public Todo getTodoById(String todoId) {
        Todo result = new Todo();
        try {
            result = Optional.ofNullable(todosRepo.findBy_id(todoId)).orElse(result);
        } catch(Exception e) {

        }
        return result;
    }

    public Map<String, Object> getTodoList(Map<String, Object> params) {
        Map map = new HashMap();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Pagination pagination = objectMapper.convertValue(params, Pagination.class);
//            Todos todo = objectMapper.convertValue(params, Todos.class);

            Query query = new Query();
            query.with(Sort.by(Sort.Direction.DESC, "date"));
            query.with(Pageable.ofSize(pagination.getPagePerCnt()).withPage(pagination.getPage()));

            map.put("list", mongoTemplate.find(query, Todo.class));
            map.put("pagination", pagination);
        } catch (Exception e) {

        }

        return map;
    }

    public Todo insertTodo(Todo todo) {
        return todosRepo.insert(todo);
    }

    public void updateTodo(Todo todo) {
//        db.todos.updateOne({todoId:1}, {$set:{isCompleted: true}})
        try {
            Query query = new Query(Criteria.where("_id").is(todo.get_id()));

            Update update = new Update();
            Class<?> clazz = todo.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields) {
                f.setAccessible(true);
                String fieldName = f.getName();
                String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Object o = clazz.getMethod(methodName).invoke(todo);
                if(!ObjectUtils.isEmpty(o)) {
                    update.set(fieldName, o);
                }
            }

            mongoTemplate.updateFirst(query, update, Todo.class);
        } catch (Exception e) {

        }
    }

    public void deleteTodo(Todo todo) {
        todosRepo.deleteTodosBy_id(todo.get_id());
    }
}
