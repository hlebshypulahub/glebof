package gleb.of.bot;

import gleb.of.callback.*;
import gleb.of.service.*;
import gleb.of.utils.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;
import java.util.function.Predicate;


@Component
public class OFBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;
    @Value("${admin.id}")
    private long adminId;

    private final BotService botService;
    private final BotUtilityService botUtilityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OFBot.class);


    public OFBot(@Lazy final BotService botService, final BotUtilityService botUtilityService) {
        this.botService = botService;
        this.botUtilityService = botUtilityService;
    }

    @PostConstruct
    public void fillActionsMap() {

    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            var msg = update.getMessage();
            System.out.println(msg);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

//
//    private boolean processChatIdActions(final String command, final long chatId) {
//        return chatIdActions.entrySet().stream()
//                .filter(entry -> entry.getKey().test(command))
//                .findFirst()
//                .map(entry -> {
//                    entry.getValue().execute(chatId);
//                    return true;
//                })
//                .orElse(false);
//    }
//
//    private boolean processCommandWithChatIdActions(final String command, final long chatId) {
//        return commandWithChatIdActions.entrySet().stream()
//                .filter(entry -> entry.getKey().test(command))
//                .findFirst()
//                .map(entry -> {
//                    entry.getValue().execute(command, chatId);
//                    return true;
//                })
//                .orElse(false);
//    }
//
//    private boolean processMessage(String command, Long chatId) {
//        if (processChatIdActions(command, chatId) || processCommandWithChatIdActions(command, chatId)) {
//            return true;
//        }
//
//        if (userService.userIsInActiveState(chatId)) {
//            return userService.processStatefulUserResponse(command, chatId);
//        }
//
//        return false;
//    }
//
//    public SendMessageWithPhotoCallback getSendMessageWithPhotoCallback() {
//        return this::sendImageWithCaption;
//    }
//
//    public GetFileFromBotCallback getGetFileFromBotCallback() {
//        return this::getFileFromBot;
//    }
//
//    public SendMessageCallback getSendMessageCallback() {
//        return this::sendMessage;
//    }
//
//    public EditMessageCallback getEditMessageCallback() {
//        return this::editMessage;
//    }
//
//    public SendMusicFileCallback getSendMusicFileCallback() {
//        return this::sendMusicFile;
//    }
//
//    public DeleteMessageCallback getDeleteMessageCallback() {
//        return this::deleteMessage;
//    }
//
//    public EditMessageCallback getEditMessageWithPhotoCallback() {
//        return this::editImageCaptionByMessageId;
//    }
//
//    public SendMessageWithInputFileCallback getSendMessageWithInputFileCallback() {
//        return this::sendImageWithCaption;
//    }
//
//    @Override
//    public String getBotUsername() {
//        return botUsername;
//    }
//
//    @Override
//    public String getBotToken() {
//        return botToken;
//    }
//
//    private File getFileFromBot(final GetFile getFileRequest) throws TelegramApiException {
//        return execute(getFileRequest);
//    }
//
//    private MessageIdInChat sendImageWithCaption(final String photoPath, final String caption, final Long chatId) {
//        try {
//            SendPhoto sendPhoto = new SendPhoto();
//            sendPhoto.setChatId(chatId.toString());
//            sendPhoto.setPhoto(botUtilityService.getPhotoInputFile(photoPath));
//            sendPhoto.setCaption(caption);
//            sendPhoto.setParseMode("Markdown");
//            Message sentMessage = execute(sendPhoto);
//            return new MessageIdInChat(sentMessage.getChatId(), sentMessage.getMessageId());
//        } catch (TelegramApiException e) {
//            LOGGER.trace(e.getMessage());
//            return null;
//        }
//    }
//
//    private MessageIdInChat sendImageWithCaption(final InputFile inputFile, final String caption, final Long chatId) {
//        try {
//            SendPhoto sendPhoto = new SendPhoto();
//            sendPhoto.setChatId(chatId.toString());
//            sendPhoto.setPhoto(inputFile);
//            sendPhoto.setCaption(caption);
//            sendPhoto.setParseMode("Markdown");
//            Message sentMessage = execute(sendPhoto);
//            return new MessageIdInChat(sentMessage.getChatId(), sentMessage.getMessageId());
//        } catch (TelegramApiException e) {
//            LOGGER.trace(e.getMessage());
//            return null;
//        }
//    }
//
//    private void sendMessage(BotApiMethodMessage message) {
//        try {
//            if (message != null) {
//                execute(message);
//            }
//        } catch (Exception e) {
//            LOGGER.trace(e.getMessage());
//        }
//    }
//
//    private MessageIdInChat sendMusicFile(final SendAudio sendAudio) {
//        try {
//            Message sentMessage = execute(sendAudio);
//            return new MessageIdInChat(sentMessage.getChatId(), sentMessage.getMessageId());
//        } catch (Exception e) {
//            LOGGER.trace(e.getMessage());
//            return null;
//        }
//    }
//
//    private void deleteMessage(DeleteMessage deleteMessage) {
//        try {
//            execute(deleteMessage);
//        } catch (TelegramApiException e) {
//            LOGGER.trace(e.getMessage());
//        }
//    }
//
//    private void editMessage(final Long chatId, final Integer messageId, final String editedMessage) {
//        var editMessageText = EditMessageText.builder()
//                .chatId(chatId)
//                .messageId(messageId)
//                .text(editedMessage)
//                .parseMode("Markdown")
//                .build();
//
//        try {
//            execute(editMessageText);
//        } catch (Exception e) {
//            LOGGER.trace(e.getMessage());
//        }
//    }
//
//    public void editImageCaptionByMessageId(final Long chatId, final int messageId, final String newCaption) {
//        EditMessageCaption editMessageCaption = EditMessageCaption.builder()
//                .chatId(chatId)
//                .messageId(messageId)
//                .caption(newCaption)
//                .parseMode("Markdown")
//                .build();
//
//        try {
//            execute(editMessageCaption);
//        } catch (Exception e) {
//            LOGGER.trace(e.getMessage());
//        }
//    }
}
