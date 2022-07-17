package com.bot.demo.service;

import com.bot.demo.controller.vo.SimpleMessageDTO;
import com.bot.demo.respository.CountersRepo;
import com.bot.demo.respository.TodosRepo;
import com.bot.demo.vo.Counter;
import com.bot.demo.vo.Todo;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodosRepo todosRepo;
    private final UserService userService;
    private final CountersRepo countersRepository;

    public List<Todo> todoList(String userId) {
        User user = userService.getUser(userId);
        return todosRepo.findAllByOwner(user.getId());
    }

    public Long todoComplete(String userId, Integer todoId) {
        long result = 0;
        try {
            User user = userService.getUser(userId);
            Todo todo = todosRepo.findFirstByOwnerAndTodoId(user.getId(), todoId);
            todo.setIsCompleted(!todo.getIsCompleted());
            result = todosRepo.updateTodo(todo);
        } catch (Exception e) {

        }
        return result;
    }

    public Todo add(Todo _todo, String userId) {
        Todo todo = null;
        try {
            User user = userService.getUser(userId);
            _todo.setOwner(user.getId());
            Counter counter = countersRepository.sequence("Todo");
            _todo.setTodoId(counter.getSeq_value());
            todo = todosRepo.insert(createTodo(_todo));
        } catch (Exception e) {

        }
        return todo;
    }

    public boolean remove(String userId, Integer todoId) {
        boolean result = false;
        User user = userService.getUser(userId);
        Todo todo = todosRepo.findFirstByOwnerAndTodoId(user.getId(), todoId);
        if(!ObjectUtils.isEmpty(todo)) {
            todosRepo.deleteTodosByOwnerAndId(user.getId(), todo.getId());
            result = true;
        }
        return result;
    }

    private Todo createTodo(Todo todo) {
        return Todo.builder()
                .todoId(todo.getTodoId())
                .title(todo.getTitle())
                .content(Optional.ofNullable(todo.getContent()).orElse(""))
                .isCompleted(Optional.ofNullable(todo.getIsCompleted()).orElse(false))
                .proceed(Optional.ofNullable(todo.getProceed()).orElse(0))
                .insDate(Optional.ofNullable(todo.getInsDate()).orElse(LocalDateTime.now()))
                .updDate(Optional.ofNullable(todo.getUpdDate()).orElse(LocalDateTime.now()))
                .owner(todo.getOwner())
                .build();
    }
}
