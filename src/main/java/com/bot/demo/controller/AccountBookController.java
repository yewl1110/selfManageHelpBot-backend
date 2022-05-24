package com.bot.demo.controller;

import com.bot.demo.service.AccountBookService;
import com.bot.demo.vo.AccountBook;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/account-book")
@RestController
@RequiredArgsConstructor
public class AccountBookController {
    private final AccountBookService accountBookService;

    @GetMapping("list")
    List<AccountBook> accountBookList(
            @RequestParam("userId")String userId,
            @RequestParam("startDate")String startDate,
            @RequestParam("endDate")String endDate
    ) {
        return accountBookService.accountBookList(userId, startDate, endDate);
    }

    @GetMapping("{accountId}")
    AccountBook singleAccountBook(@PathVariable("accountId") String accountId) {
        return accountBookService.get(accountId);
    }

    @PostMapping("")
    Map<String, Object> insert(@Valid @RequestBody AccountBook accountBook) {
        return accountBookService.insert(accountBook);
    }

    @DeleteMapping("")
    Map<String, Object> delete(@RequestBody AccountBook accountBook) {
        return accountBookService.delete(accountBook);
    }

    @PatchMapping("")
    Map<String, Object> update(@RequestBody AccountBook accountBook) {
        return accountBookService.update(accountBook);
    }
}
