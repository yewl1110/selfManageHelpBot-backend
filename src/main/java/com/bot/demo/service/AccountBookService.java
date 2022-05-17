package com.bot.demo.service;

import com.bot.demo.respository.AccountBooksRepo;
import com.bot.demo.respository.CountersRepo;
import com.bot.demo.vo.AccountBook;
import com.bot.demo.vo.Counter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
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

    public List<AccountBook> accountBookList(String userId, String startDate, String endDate) {
        List<AccountBook> result = new ArrayList<>();
        try {
            result = accountBookRepository.getListByUserIdAndPeriod(LocalDate.parse(startDate), LocalDate.parse(endDate), userId);
        } catch (Exception e) {
            log.error("{}",e.getMessage());
        }
        return result;
    }

    public AccountBook get(String accountId) {
        return accountBookRepository.findById(accountId).orElse(new AccountBook());
    }

    public Map<String, Object> insert(AccountBook accountBook) {
        Map<String, Object> result = new HashMap<>();
        int code = 0;

        try {
            Counter counter = countersRepository.sequence("AccountBook");
            accountBook.setAccountId(counter.getSeq_value());
            accountBookRepository.insert(accountBook);
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
            AccountBook delCheck = accountBookRepository.findFirstByAccountIdAndUserId(accountBook.getAccountId(), accountBook.getUserId());
            if(!ObjectUtils.isEmpty(delCheck)){
                accountBookRepository.deleteAccountBookByAccountIdAndUserId(accountBook.getAccountId(), accountBook.getUserId());
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
