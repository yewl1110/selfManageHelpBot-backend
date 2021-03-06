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

import java.util.*;

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

    public Map<String, Object> insert(AccountBook accountBook, String userId) {
        Map<String, Object> result = new HashMap<>();
        AccountBook insAccountBook = new AccountBook();
        int code = 0;

        try {
            Counter counter = countersRepository.sequence("AccountBook");
            accountBook.setAccountId(counter.getSeq_value());

            User user = usersRepository.findByUserId(userId);
            if(!ObjectUtils.isEmpty(user)) {
                accountBook.setUser(user.getId());
                insAccountBook = accountBookRepository.insert(accountBook);
                code = 1;
            }
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        } finally {
            String msg = "";
            switch (code) {
                case 0:
                    msg = "??????"; break;
                case 1:
                    msg = "??????"; break;
            }
            result.put("code", code);
            result.put("msg", msg);
            result.put("accountId", Optional.ofNullable(insAccountBook).orElseGet(AccountBook::new).getAccountId());
        }
        return result;
    }


    public Map<String, Object> updateByUserId(AccountBook accountBook, String userId) {
        Map<String, Object> result = new HashMap<>();
        int code = 0;

        try {
            User user = usersRepository.findByUserId(userId);
            if(accountBookRepository.update(accountBook, user) > 0) {
                code = 1;
            };
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        } finally {
            String msg = "";
            switch (code) {
                case 0:
                    msg = "??????"; break;
                case 1:
                    msg = "??????"; break;
            }
            result.put("code", code);
            result.put("msg", msg);
        }
        return result;
    }


    public Map<String, Object> delete(Map<String, Object> params, String userId) {
        Map<String, Object> result = new HashMap<>();
        int code = 0;

        try {
            User user = usersRepository.findByUserId(userId);
            Integer accountId = (int)params.get("accountId");
            AccountBook delCheck = accountBookRepository.findFirstByAccountIdAndUser(accountId, user.getId());
            if(!ObjectUtils.isEmpty(delCheck)){
                accountBookRepository.deleteAccountBookByAccountIdAndUser(accountId, user.getId());
                code = 1;
            }
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        } finally {
            String msg = "";
            switch (code) {
                case 0:
                    msg = "??????"; break;
                case 1:
                    msg = "??????"; break;
            }
            result.put("code", code);
            result.put("msg", msg);
        }
        return result;
    }
}
