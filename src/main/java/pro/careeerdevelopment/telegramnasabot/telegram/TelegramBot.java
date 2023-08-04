package pro.careeerdevelopment.telegramnasabot.telegram;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pro.careeerdevelopment.telegramnasabot.telegram.config.BotConfig;
import pro.careeerdevelopment.telegramnasabot.nasa.model.nasagov.entities.CollectionNasa;
import pro.careeerdevelopment.telegramnasabot.telegram.service.NasaService;
import pro.careeerdevelopment.telegramnasabot.telegram.service.message.NasaMessageCreator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String currentServiceModel = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getFrom().getFirstName());
                    break;
                case "/z":
                    testCommand(chatId, update);
                    break;
                case "/nu":
                    CollectionNasa nasaCollectionNasa = new CollectionNasa();
                    nasaService.getAllDate(currentServiceModel, nasaCollectionNasa);
                    break;
                case "/bot_update_video_links":
                    nasaService.updateDb();
                    //                    List<LinksItem> linksItemsList = ItemListService.getContentLinks("https://images-assets.nasa.gov/image/PIA01454/collection.json");
                    break;
                case "image" :
                    sendMessage(chatId, messageCreator.createMessage());

                case "en":
                    //                    nasaService.getNasaEntity(2L);
            }
        }

    }

    private void testCommand(Long chatId, Update update) throws ParseException {
        Long dateInteger = update.getMessage().getDate().longValue();
        Date date = new Date(dateInteger * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+4"));
        String formattedDate = sdf.format(date);
        String test = "test msg " + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom()
            .getLastName() + " " + " сообщение отправлено " + formattedDate;

        sendMessage(chatId, test);
    }

    private void startCommandReceived(Long chatId, String name) {
        String real_name = StringUtils.isNotBlank(name) ? name : "незнакомец";
        String answer = "Привет, " + real_name + ", рад Вас видеть!" + "\n"
            + "Меня зовут Ларч, я рад прислуживать Вам и всем людям. " + "\n"
            + "Вы можете попросить меня организовать встречу" + "\n" + "просто напишите meet";
        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setPhoto(new InputFile().setMedia("http://images-assets.nasa.gov/image/0700505/0700505~orig.jpg"));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}