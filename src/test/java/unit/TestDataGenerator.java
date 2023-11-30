package unit;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataGenerator{
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
}
