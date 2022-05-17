package com.bot.demo.respository.custom;

import com.bot.demo.vo.Counter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class CounterRepositoryImpl implements CounterRepository {
    final private MongoTemplate mongoTemplate;
    @Override
    public Counter sequence(String name) {
        Counter counter = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("name").is(name));
            counter = mongoTemplate.findOne(query, Counter.class);
            if(ObjectUtils.isEmpty(counter)) {
                counter = new Counter();
                counter.setName(name);
                counter.setSeq_value(1);
                mongoTemplate.insert(counter);
            } else {
                Update update = new Update();
                update.set("seq_value", counter.getSeq_value() + 1);
                mongoTemplate.updateFirst(query, update, Counter.class);
            }

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        return counter;
    }
}
