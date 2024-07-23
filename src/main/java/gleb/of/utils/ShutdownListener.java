package gleb.of.utils;

import gleb.of.bot.OFBot;
import gleb.of.callback.SendMessageCallback;
import gleb.of.service.BotUtilityService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;


@Component
public class ShutdownListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(final ContextClosedEvent event) {

    }

//    @Value("${admin.tech-id}")
//    private Long techAdminId;
//
//    private final OFBot OFBot;
//    private final BotUtilityService botUtilityService;
//    private SendMessageCallback sendMessageCallback;
//
//    public ShutdownListener(final OFBot OFBot, final BotUtilityService botUtilityService) {
//        this.OFBot = OFBot;
//        this.botUtilityService = botUtilityService;
//    }
//
//    @PostConstruct
//    public void initCallbacks() {
//        this.sendMessageCallback = OFBot.getSendMessageCallback();
//    }
//
//    @Override
//    public void onApplicationEvent(final ContextClosedEvent event) {
//        sendMessageCallback.execute(botUtilityService.buildSendMessage("Application is shutting down!", techAdminId));
//    }

}
