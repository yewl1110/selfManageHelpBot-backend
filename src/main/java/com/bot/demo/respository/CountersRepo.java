package com.bot.demo.respository;

import com.bot.demo.vo.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountersRepo extends MongoRepository<Counter, String> {
    Counter findByName(String name);
}
