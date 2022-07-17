package com.bot.demo.controller;

import com.bot.demo.controller.vo.SimpleMessageDTO;
import com.bot.demo.service.TodoService;
import com.bot.demo.vo.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoController {
    final private TodoService todoService;

    @GetMapping("")
    ResponseEntity<List<Todo>> todoList(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(todoService.todoList(userId));
    }

    @GetMapping("/complete")
    ResponseEntity<SimpleMessageDTO> todoComplete(@RequestParam("userId") String userId, @RequestParam("todoId") Integer todoId) {
        SimpleMessageDTO messageDTO;
        long result = todoService.todoComplete(userId, todoId);
        if(result > 0) {
            messageDTO = SimpleMessageDTO.getDefaultSuccess();
        } else {
            messageDTO = SimpleMessageDTO.getDefaultFailed();
        }
        return ResponseEntity.ok(messageDTO);
    }

    @PostMapping("")
    ResponseEntity<Todo> addTodo(@RequestBody @Valid Todo todo, String userId) {
        return ResponseEntity.ok(todoService.add(todo, userId));
    }

    @DeleteMapping("")
    ResponseEntity<SimpleMessageDTO> removeTodo(@RequestParam("userId") String userId, @RequestParam("todoId") Integer todoId) {
        SimpleMessageDTO messageDTO;
        if(todoService.remove(userId, todoId)) {
            messageDTO = SimpleMessageDTO.getDefaultSuccess();
        } else {
            messageDTO = SimpleMessageDTO.getDefaultFailed();
        }
        return ResponseEntity.ok(messageDTO);
    }
}
