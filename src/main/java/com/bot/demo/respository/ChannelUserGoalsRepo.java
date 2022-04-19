package com.bot.demo.respository;

import com.bot.demo.vo.ChannelUserGoal;
import com.bot.demo.vo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChannelUserGoalsRepo extends MongoRepository<ChannelUserGoal, String> {
    ChannelUserGoal insert(ChannelUserGoal vo);
    void deleteByUser(User user);
}
