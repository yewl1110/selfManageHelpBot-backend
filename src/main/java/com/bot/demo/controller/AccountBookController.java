package com.bot.demo.controller;

import com.bot.demo.service.AccountBookService;
import com.bot.demo.vo.AccountBook;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
    Map<String, Object> insert(@Valid @RequestBody AccountBook accountBook, String userId) {
        return accountBookService.insert(accountBook, userId);
    }

    @DeleteMapping("")
    Map<String, Object> delete(@RequestBody Map<String, Object> params, String userId) {
        return accountBookService.delete(params, userId);
    }

    @PatchMapping("")
    Map<String, Object> update(@Valid @RequestBody AccountBook accountBook, String userId) {
        return accountBookService.updateByUserId(accountBook, userId);
    }

    @GetMapping("fixedList")
    List<AccountBook> fixedList(@RequestParam("userId")String userId) {
        return accountBookService.fixedAccountBookList(userId);
    }


}
