package com.bot.demo.respository;

import com.bot.demo.respository.custom.TodoRepository;
import com.bot.demo.vo.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodosRepo extends MongoRepository<Todo, String>, TodoRepository {
    List<Todo> findAllByOwner(ObjectId userId);
    Todo findFirstByOwnerAndTodoId(ObjectId owner, Integer todoId);
    Todo insert(Todo todo);
    int deleteTodosByOwnerAndId(ObjectId userId, ObjectId todoId);
}
