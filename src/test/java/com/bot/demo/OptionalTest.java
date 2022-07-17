package com.bot.demo;

import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OptionalTest {

    @Test
    void nullTest() {
        Optional.of(null).get();
    }
}
