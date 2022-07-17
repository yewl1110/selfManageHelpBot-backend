package com.bot.demo.respository.custom;

import com.bot.demo.vo.Todo;

import java.util.Map;

public interface TodoRepository {
//    Map<String, Object> getTodoList(Map<String, Object> params);
    long updateTodo(Todo todo);

}
