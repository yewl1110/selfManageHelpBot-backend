package com.bot.demo.respository;

import com.bot.demo.vo.Channel;
import com.bot.demo.vo.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsersRepo extends MongoRepository<User, String> {
    User findByUserId(String userId);
    void deleteUserByUserId(String userId);
//    void deleteTodosUserIdAndAccessKey
    // update
    User insert(User user);
}
