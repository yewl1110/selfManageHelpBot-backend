package com.bot.demo.service;

import com.bot.demo.respository.TodosRepo;
import com.bot.demo.vo.Todo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
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
            result = todosRepo.findById(new ObjectId(todoId));
        } catch(Exception e) {

        }
        return result;
    }

    public Map<String, Object> getTodoList(Map<String, Object> params) {
        return todosRepo.getTodoList(params);
    }

    public Todo insertTodo(Todo todo) {
        return todosRepo.insert(todo);
    }

    public void updateTodo(Todo todo) {
        todosRepo.updateTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        todosRepo.deleteTodosById(todo.getId());
    }
}
