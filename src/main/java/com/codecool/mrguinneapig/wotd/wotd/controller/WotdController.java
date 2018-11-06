package com.codecool.mrguinneapig.wotd.wotd.controller;

import com.codecool.mrguinneapig.wotd.wotd.Wotd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WotdController {

    @GetMapping("/wotd")
    public Wotd wotd() throws IOException{
        return new Wotd();
    }

}
