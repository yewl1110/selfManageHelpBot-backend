package com.bot.demo.controller;

import com.bot.demo.service.TodoService;
import com.bot.demo.vo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService todoService;


    @GetMapping("/{todoId}")
    public Todo getTodoById(@PathVariable(value = "todoId") String todoId) {
        return todoService.getTodoById(todoId);
    }

    @PostMapping("/list")
    public Map<String, Object> getTodoList(@RequestBody Map<String, Object> params) {
        return todoService.getTodoList(params);
    }

    @PostMapping("")
    public Todo insTodo(@RequestBody Todo todo) {

        return todoService.insertTodo(todo);
    }

    @PatchMapping("")
    public int updTodo(@RequestBody Todo todo) {
        todoService.updateTodo(todo);
        return 1;
    }

    @DeleteMapping("")
    public int delTodo(@RequestBody Todo todo) {
        todoService.deleteTodo(todo);
        return 1;
    }
}
