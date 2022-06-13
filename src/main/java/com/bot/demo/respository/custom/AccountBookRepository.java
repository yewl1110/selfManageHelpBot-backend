package com.bot.demo.respository.custom;

import com.bot.demo.vo.AccountBook;
import com.bot.demo.vo.User;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AccountBookRepository {
    List<AccountBook> getListByUserAndPeriod(DateTime startDate, DateTime endDate, User user);
    List<AccountBook> getFixedListByIdList(List<ObjectId> id);
    int update(AccountBook accountBook, User user);
}
