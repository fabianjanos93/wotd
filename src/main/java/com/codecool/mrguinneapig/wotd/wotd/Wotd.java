package com.codecool.mrguinneapig.wotd.wotd;

import org.springframework.core.io.ClassPathResource;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

public class Wotd {

    public static final String STATIC_WORDS_TXT = "static/words.txt";
    String wotd;
    List<String> lines;


    public Wotd() throws IOException {
        this.wotd = WordOfTheDay();
    }

    public void setWotd(String wotd) {
        this.wotd = wotd;
    }

    public String getWotd() {
        return wotd;
    }

    private void fillLines() {
        lines.addAll(Arrays.asList());
    }

    public String WordOfTheDay() throws IOException {
        List<String> lines = new LinkedList<>();

        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream(STATIC_WORDS_TXT);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }

        return lines.get(getSeededRandom(lines.size()));
    }

    public static int getSeededRandom(int max) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String seed = sdf.format(date);
        Random seededRandom = new Random(Integer.parseInt(seed.replace("/", "")));
        int random = Math.abs(seededRandom.nextInt(max));
        return random;
    }
}
