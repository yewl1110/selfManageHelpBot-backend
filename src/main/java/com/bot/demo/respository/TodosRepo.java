package com.bot.demo.respository;

import com.bot.demo.respository.custom.TodoRepository;
import com.bot.demo.vo.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodosRepo extends MongoRepository<Todo, String>, TodoRepository {
    List<Todo> findAll();
    Todo findBy_id(String id);
    Todo insert(Todo todo);
    void deleteTodosBy_id(String id);
}
