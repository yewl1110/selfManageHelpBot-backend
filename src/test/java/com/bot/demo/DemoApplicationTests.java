package com.bot.demo;

import com.bot.demo.respository.TodosRepo;
import com.bot.demo.vo.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//@SpringBootTest
class DemoApplicationTests {
    @Autowired
    TodosRepo todosRepo;
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    void contextLoads(){
        Todo todo = new Todo();

        System.out.println(todosRepo.findAll());
//        System.out.println(todosRepo.update(todos));
        System.out.println(todosRepo.findAll());
    }

    @Test
    public void updateTest(){

//        PetEntity pet = PetEntity.builder().kind("CAT").name("나비").age(0).build();
//        mongoTemplate.insert(pet);
        // "db.todos.updateOne({todoId:1}, {$set:{isCompleted: true}})"
        Todo todo = Todo.builder().todoId(1).build();

        Query query = new Query(Criteria.where("todoId").is(todo.getTodoId()));

        String updatedName = "노랑이";
        int increaseAge = 5;
        Update update = Update.update("isCompleted", false);
        mongoTemplate.updateFirst(query, update, Todo.class);


        System.out.println(todosRepo.findAll());
    }

    @Test
    public void methods() {
        Todo todo = new Todo();
        todo.set_id("tmptmp");
        Class<?> c = todo.getClass();
        Field[] fields = c.getDeclaredFields();
        for(Field f:fields) {
            f.setAccessible(true);
            System.out.println(f.getName());
        }

        System.out.println("------------");

        Method[] allMethods = c.getDeclaredMethods();
        for(Method m : allMethods) {
            m.setAccessible(true);
            try{
                System.out.println(m.getName());
            } catch (Exception e) {

            }
        }

        try {
            System.out.println(c.getDeclaredMethod("getisCompleted").invoke(todo));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
