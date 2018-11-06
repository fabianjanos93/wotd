package com.codecool.mrguinneapig.wotd.wotd;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Wotd {

    String wotd;

    public Wotd() throws IOException {
        this.wotd = WordOfTheDay();
    }

    public void setWotd(String wotd) {
        this.wotd = wotd;
    }

    public String getWotd() {
        return wotd;
    }

    public static int getSeededRandom(int max) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String seed = sdf.format(date);
        Random seededRandom = new Random(Integer.parseInt(seed.replace("/", "")));
        int random = Math.abs(seededRandom.nextInt(max));
        return random;
    }

    public String WordOfTheDay() throws IOException {
        List<String> lines;
        File file = new ClassPathResource("/static/words.txt").getFile();
        lines = FileUtils.readLines(file, Charset.forName("UTF-8"));

        return lines.get(getSeededRandom(lines.size()));
    }
}
