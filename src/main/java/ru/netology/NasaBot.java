package ru.netology;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class NasaBot extends TelegramLongPollingBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String NASA_URL = "https://api.nasa.gov/planetary/apod";
    private final String API_KEY = "?api_key=WFxpJcOwWLFDpzNtBtCdgdtdi9rVNVB2dqn2ORWp";


    public NasaBot(String botName, String botToken) {
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException exception) {
            exception.printStackTrace();
        }

    }

    void sendMessage(String msg, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(msg);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String answer = update.getMessage().getText();
            String[] separatedAnswer = answer.split(" ");
            String action = separatedAnswer[0];

            switch (action) {
                case "/help":
                    sendMessage("Привет. Я умею присылать картинку дня от Nasa. Напиши /start", chatId);
                    break;
                case "/start":
                    sendMessage("Привет. Для того что бы я прислал фото дня, напиши /image", chatId);
                    break;
                case "/image":
                    String url = Utils.getUrl(NASA_URL + API_KEY);
                    sendMessage(url, chatId);
                    break;
                case "/date":
                    String date = separatedAnswer[1];
                    url = Utils.getUrl(NASA_URL + API_KEY + "&date=" + date);
                    sendMessage(url, chatId);
                    break;
                    default:
                    sendMessage("Я не понимаю чего ты от меня хочешь.", chatId);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}