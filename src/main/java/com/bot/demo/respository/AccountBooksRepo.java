package com.bot.demo.respository;

import com.bot.demo.vo.AccountBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountBooksRepo extends MongoRepository<AccountBook, String> {
    AccountBook findBy_id(String _id);
    AccountBook insert(AccountBook accountBook);
    void deleteAccountBookBy_id(String string);

}
