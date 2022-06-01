package com.bot.demo.service;

import com.bot.demo.respository.AccountBooksRepo;
import com.bot.demo.respository.CountersRepo;
import com.bot.demo.respository.StudiesRepo;
import com.bot.demo.respository.UsersRepo;
import com.bot.demo.vo.AccountBook;
import com.bot.demo.vo.Counter;
import com.bot.demo.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountBookService {
    private final AccountBooksRepo accountBookRepository;
    private final CountersRepo countersRepository;
    private final UsersRepo usersRepository;

    public List<AccountBook> accountBookList(String userId, String startDate, String endDate) {
        List<AccountBook> result = new ArrayList<>();
        try {
            User user = usersRepository.findByUserId(userId);
            if(!ObjectUtils.isEmpty(user)) {
                result = accountBookRepository.getListByUserAndPeriod(DateTime.parse(startDate), DateTime.parse(endDate), user);
            }
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        }
        return result;
    }

    public List<AccountBook> fixedAccountBookList(String userId) {
        List<AccountBook> result = new ArrayList<>();
        try {
            User user = usersRepository.findByUserId(userId);
            result = accountBookRepository.findAllByUserAndIsFixed(user.getId(), true);
        } catch (Exception e) {

        }
        return result;
    }

    public AccountBook get(String accountId) {
        return accountBookRepository.findById(accountId).orElse(new AccountBook());
    }

    public Map<String, Object> insert(AccountBook accountBook) {
        Map<String, Object> result = new HashMap<>();
        AccountBook insAccountBook = new AccountBook();
        int code = 0;

        try {
            Counter counter = countersRepository.sequence("AccountBook");
            accountBook.setAccountId(counter.getSeq_value());
            insAccountBook = accountBookRepository.insert(accountBook);
            code = 1;
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        } finally {
            String msg = "";
            switch (code) {
                case 0:
                    msg = "실패"; break;
                case 1:
                    msg = "성공"; break;
            }
            result.put("code", code);
            result.put("msg", msg);
            result.put("accountId", insAccountBook.getAccountId());
        }
        return result;
    }


    public Map<String, Object> update(AccountBook accountBook) {
        Map<String, Object> result = new HashMap<>();
        int code = 0;

        try {
            accountBookRepository.update(accountBook);
            code = 1;
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        } finally {
            String msg = "";
            switch (code) {
                case 0:
                    msg = "실패"; break;
                case 1:
                    msg = "성공"; break;
            }
            result.put("code", code);
            result.put("msg", msg);
        }
        return result;
    }


    public Map<String, Object> delete(AccountBook accountBook) {
        Map<String, Object> result = new HashMap<>();
        int code = 0;

        try {
            AccountBook delCheck = accountBookRepository.findFirstByAccountIdAndUser(accountBook.getAccountId(), accountBook.getUser());
            if(!ObjectUtils.isEmpty(delCheck)){
                accountBookRepository.deleteAccountBookByAccountIdAndUser(accountBook.getAccountId(), accountBook.getUser());
                code = 1;
            }
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        } finally {
            String msg = "";
            switch (code) {
                case 0:
                    msg = "실패"; break;
                case 1:
                    msg = "성공"; break;
            }
            result.put("code", code);
            result.put("msg", msg);
        }
        return result;
    }
}
