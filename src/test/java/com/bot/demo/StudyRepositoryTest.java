package com.bot.demo;

import com.bot.demo.respository.StudiesRepo;
import com.bot.demo.respository.UsersRepo;
import com.bot.demo.service.AccountBookService;
import com.bot.demo.vo.User;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;

@SpringBootTest
public class StudyRepositoryTest {
    @Autowired
    StudiesRepo studiesRepo;
    @Autowired AccountBookService accountBookService;
    @Autowired
    UsersRepo usersRepository;

    @Test
    public void offsetDatetime() {
        String startDateStr = "2022-05-26";
        LocalDate localDate = LocalDate.parse(startDateStr);

        LocalDateTime localDateTime = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(localDateTime, ZoneId.of("BST", ZoneId.SHORT_IDS),null);
        OffsetDateTime offsetDateTime = zonedDateTime.toOffsetDateTime();
        System.out.println(offsetDateTime);
        DateTime dateTime = new DateTime(offsetDateTime.toInstant().toEpochMilli());
        System.out.println(accountBookService.accountBookList("6291bce4e53eb80749396570", "2022-05-26", "2022-05-29"));
        System.out.println(offsetDateTime);System.out.println(offsetDateTime);System.out.println(offsetDateTime);System.out.println(offsetDateTime);
        System.out.println(accountBookService.accountBookList("6291bce4e53eb80722396570", "2022-05-26", "2022-05-29"));
    }

    @Test
    public void selectStudy() {
        System.out.println(studiesRepo.findFirstById(new ObjectId("6291c05ec140daaabc44f13b")));
    }
    @Test
    @DisplayName("study repository에서 study list 가져오는 테스트")
    public void selectStudyList() {
        String userId = "gun4930";
        String startDateStr = "2022-05-29";
        int count = 7;

        DateTime startDate = DateTime.parse(startDateStr);
        DateTime endDate = startDate.plusDays(count);
        User user = usersRepository.findByUserId(userId);

        System.out.println(studiesRepo.findAllByOwnerAndEndDateAfterAndStartDateBefore(user.getId(), startDate, endDate));
    }
}
