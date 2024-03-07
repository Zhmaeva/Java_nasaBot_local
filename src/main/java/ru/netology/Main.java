package ru.netology;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        final String name = "java_nasa_telegram_bot";
        final String token = "6343658138:AAF0cv1FITu8-xUOfoO5X5YoAnFuB7lmsVE";

        new NasaBot(name, token);


    }
}