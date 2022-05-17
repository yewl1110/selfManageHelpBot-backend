package com.bot.demo.respository.custom;

import com.bot.demo.vo.Counter;

public interface CounterRepository {
    Counter sequence(String name);
}
