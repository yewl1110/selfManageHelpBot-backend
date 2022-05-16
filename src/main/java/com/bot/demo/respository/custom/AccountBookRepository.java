package com.bot.demo.respository.custom;

import com.bot.demo.vo.AccountBook;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AccountBookRepository {
    List<AccountBook> getListByUserIdAndPeriod(LocalDate startDate, LocalDate endDate, String userId);
    int update(AccountBook accountBook);
}
