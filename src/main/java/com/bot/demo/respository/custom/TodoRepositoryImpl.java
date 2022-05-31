package com.bot.demo.respository.custom;

import com.bot.demo.vo.Todo;
import com.bot.demo.vo.base.Pagination;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
@Slf4j
public class TodoRepositoryImpl implements TodoRepository{
    private final MongoTemplate mongoTemplate;

    @Override
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
            log.error("%class{0}.%method: %L - %m%n");
        }

        return map;
    }

    @Override
    public void updateTodo(Todo todo) {
//        db.todos.updateOne({todoId:1}, {$set:{isCompleted: true}})
        try {
            Query query = new Query(Criteria.where("_id").is(todo.getId()));

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
            log.error("%class{0}.%method: %L - %m%n");
        }
    }
}
