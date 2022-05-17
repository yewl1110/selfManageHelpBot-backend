package com.bot.demo.respository;

import com.bot.demo.respository.custom.CounterRepository;
import com.bot.demo.vo.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountersRepo extends MongoRepository<Counter, String>, CounterRepository {
    Counter findByName(String name);
}
