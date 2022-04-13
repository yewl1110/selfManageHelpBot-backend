package com.bot.demo.respository;

import com.bot.demo.vo.Todos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodosRepo extends MongoRepository<Todos, String> {
    List<Todos> findAll();
    Todos findBy_id(String id);
    Todos insert(Todos todo);
    void deleteTodosBy_id(String id);
}
