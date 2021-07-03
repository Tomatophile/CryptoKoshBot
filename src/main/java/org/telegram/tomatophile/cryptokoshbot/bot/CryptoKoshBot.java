package org.telegram.tomatophile.cryptokoshbot.bot;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.tomatophile.bottemplate.AbstractTelegramBot;
import org.telegram.tomatophile.bottemplate.receiver.UpdateReceiver;
import org.telegram.tomatophile.cryptokoshbot.service.ReplyService;

@Getter
@Component
public class CryptoKoshBot extends AbstractTelegramBot {
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.blank.unknownCommand}")
    private String unknownCommand;

    private final ReplyService replyService;

    private final UpdateReceiver updateReceiver;

    public CryptoKoshBot(UpdateReceiver updateReceiver, ReplyService replyService) {
        super(updateReceiver);
        this.updateReceiver = updateReceiver;
        this.replyService = replyService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        var responseToUser = updateReceiver.receive(update);

        if(!responseToUser.isEmpty()){
            for (var send : responseToUser) {
                if (send instanceof SendMessage) {
                    execute((SendMessage) send);
                } else if(send instanceof SendPhoto){
                    execute((SendPhoto) send);
                }
            }
        } else {
            execute(replyService.getTextMessage(update.getMessage().getChatId().toString(), unknownCommand));
        }
    }
}
