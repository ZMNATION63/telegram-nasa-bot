package pro.careeerdevelopment.telegramnasabot.telegram;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.careeerdevelopment.telegramnasabot.telegram.config.BotConfig;
import pro.careeerdevelopment.telegramnasabot.telegram.service.NasaService;
import pro.careeerdevelopment.telegramnasabot.telegram.service.message.NasaMessageCreator;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Autowired
    private NasaService nasaService;

    @Autowired
    private  NasaMessageCreator messageCreator;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/bot_update_image_links":
                    nasaService.updateDb();
                    break;
                case "image" :
                    sendMessage(chatId, messageCreator.createMessage());
                    break;
                case "en":
                    //                    nasaService.getNasaEntity(2L);
            }
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}