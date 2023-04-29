package by.ciao.bot;

import by.ciao.utils.BotService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class AdminBot extends TelegramLongPollingBot {

    @Value("${bot_token}")
    private String botToken;
    @Value("${bot_username}")
    private String botUsername;
    private static final Logger log = LoggerFactory.getLogger(AdminBot.class);
    private final BotService botService;

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Scheduled(cron = "${data_sending_rate}")
    public void callScheduledDataSending() throws IOException {
        sendUserDataToAdmin();
    }

    public void sendUserDataToAdmin() throws IOException {
        SendDocument usersCSV = botService.generateUsersCSV();

        try {
            execute(usersCSV);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }
}
