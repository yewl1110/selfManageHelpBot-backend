package com.bot.demo.respository;

import com.bot.demo.vo.ChannelUserGoal;
import com.bot.demo.vo.Study;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudiesRepo extends MongoRepository<Study, String> {
    List<Study> findAllByOwner(ChannelUserGoal vo);
    Study insert(Study study);
//    update / delete
}