package com.bot.demo.respository;

import com.bot.demo.respository.custom.AccountBookRepository;
import com.bot.demo.vo.AccountBook;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountBooksRepo extends MongoRepository<AccountBook, String>, AccountBookRepository {
    AccountBook findById(ObjectId id);
    AccountBook insert(AccountBook accountBook);
    AccountBook findFirstByAccountIdAndUser(Integer aId, ObjectId uId);
    void deleteAccountBookByAccountIdAndUser(Integer aId, ObjectId uId);
}
