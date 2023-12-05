package academy.kata.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataGenerator {

    public static String generateData() {
        int length = 25;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    public static String generateData101symb() {
        int length = 101;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    public static String generateDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.format(date);


    }

    public static String generateUpdated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
