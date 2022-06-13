package com.bot.demo.respository.custom;

import com.bot.demo.annotation.PatchIgnore;
import com.bot.demo.vo.AccountBook;
import com.bot.demo.vo.Todo;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AccountBookRepositoryImpl implements AccountBookRepository{
    private final MongoTemplate mongoTemplate;

    @Override
    public List<AccountBook> getListByUserAndPeriod(DateTime startDate, DateTime endDate, User user) {
        List<AccountBook> result = new ArrayList<>();

        try {
            Query query = new Query();
            query.with(Sort.by(Sort.Direction.DESC, "date"));
            query.addCriteria(new Criteria().andOperator(
               Criteria.where("user").is(user.getId()), Criteria.where("date").gte(startDate).lt(endDate.plusDays(1))
            ));
            result = mongoTemplate.find(query, AccountBook.class);
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        }

        return result;
    }

    @Override
    public List<AccountBook> getFixedListByIdList(List<ObjectId> id) {
        List<AccountBook> list = new ArrayList<>();
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").in(id).and("isFixed").is(true));
            list = mongoTemplate.find(query, AccountBook.class);
        } catch (Exception e) {
        }
        return list;
    }


    @Override
    public int update(AccountBook accountBook, User user) {
        int result = 0;
//        db.todos.updateOne({todoId:1}, {$set:{isCompleted: true}})
        try {
            Query query = new Query();
            query.addCriteria(new Criteria().andOperator(
                    Criteria.where("user").is(user.getId()), (Criteria.where("accountId").is(accountBook.getAccountId()))
            ));

            Update update = new Update();
            Class<?> clazz = accountBook.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields) {
                f.setAccessible(true);
                String fieldName = f.getName();
                String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Object o = clazz.getMethod(methodName).invoke(accountBook);
                if(!ObjectUtils.isEmpty(o) && clazz.getDeclaredAnnotation(PatchIgnore.class) == null) {
                    update.set(fieldName, o);
                }
            }
            mongoTemplate.find(query, AccountBook.class);
            result = (int)mongoTemplate.updateFirst(query, update, AccountBook.class).getModifiedCount();
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        }
        return result;
    }
}
