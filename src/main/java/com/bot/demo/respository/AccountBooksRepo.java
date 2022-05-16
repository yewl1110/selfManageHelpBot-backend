package com.bot.demo.respository;

import com.bot.demo.respository.custom.AccountBookRepository;
import com.bot.demo.vo.AccountBook;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountBooksRepo extends MongoRepository<AccountBook, String>, AccountBookRepository {
    Optional<AccountBook> findById(String id);
    AccountBook insert(AccountBook accountBook);
    void deleteAccountBookByAccountIdAndUserId(Integer aId, String uId);
}
