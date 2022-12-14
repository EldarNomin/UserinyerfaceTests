package utils;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_LETTERS_ALPHABET = 26;
    private static final int NUMBER_OF_DIGITS_IN_PASSWORD = 4;
    private static final int NUMBER_OF_CAPITAL_LETTERS_IN_PASSWORD = 3;
    private static final int NUMBER_OF_LOWERCASE_LETTERS_IN_PASSWORD = 2;

    public static String getEmail(String email) {
        return email.substring(0, email.indexOf('@'));
    }

    public static String getDomain(String email) {
        String secondPart = getAllDomain(email);
        return secondPart.substring(0, secondPart.indexOf("."));
    }

    public static String getDropDownOpener(String email) {
        String secondPart = getAllDomain(email);
        return secondPart.substring(secondPart.indexOf("."));
    }

    private static String getAllDomain(String email) {
        return email.substring(email.indexOf('@') + 1);
    }

    public static int getRandomNumber(int min, int max){
        return RANDOM.nextInt(min, max);
    }

    public static String generatePassword(String email) {
        StringBuilder dataForPassword = new StringBuilder();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < NUMBER_OF_DIGITS_IN_PASSWORD; i++) {
            dataForPassword.append(RANDOM.nextInt(9));
        }

        for (int i = 0; i < NUMBER_OF_LOWERCASE_LETTERS_IN_PASSWORD; i++) {
            dataForPassword.append((char) (RANDOM.nextInt(NUMBER_OF_LETTERS_ALPHABET) + 'a'));
        }

        for (int i = 0; i < NUMBER_OF_CAPITAL_LETTERS_IN_PASSWORD; i++) {
            dataForPassword.append(String.valueOf((char) (RANDOM.nextInt(NUMBER_OF_LETTERS_ALPHABET) + 'a')).toUpperCase());
        }

        dataForPassword.append(email.charAt(getRandomNumber(0,10)));

        int randomSymbol;
        int passwordLength = dataForPassword.length();

        for (int i = 0; i < passwordLength; i++) {
            randomSymbol = RANDOM.nextInt(dataForPassword.length());
            password.append(dataForPassword.charAt(randomSymbol));
            dataForPassword.deleteCharAt(randomSymbol);
        }
        return password.toString();
    }
}