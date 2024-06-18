package data;

import com.github.javafaker.Faker;

import java.security.SecureRandom;

import static data.DataGenerator.RandomString.randomString;

public class DataGenerator {

    private String getValidLogin() {
        return "test@protei.ru";
    }

    private String getValidPassword() {
        return "test";
    }

    public User getValidUser() {
        return new User(getValidLogin(), getValidPassword());
    }

    public class User {
        private final String login;
        private final String password;

        public User (String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String generateString(int len, String locale) {
        return randomString(len, locale);
    }

    public static class RandomString {
        static final String ru = "0123456789АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        static final String en = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        static SecureRandom rnd = new SecureRandom();

        public static String randomString(int len, String lang) {
            if(lang.equals("ru")) {
                lang = ru;
            } else if (lang.equals("en")) {
                lang = en;
            }
            else {
                return null;
            }
            StringBuilder sb = new StringBuilder(len);
            for(int i = 0; i < len; i++)
                sb.append(lang.charAt(rnd.nextInt(lang.length())));
            return sb.toString();
        }
    }
}
