package org.telegram.tomatophile.cryptokoshbot.bot.processed.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.tomatophile.bottemplate.processed.command.Command;
import org.telegram.tomatophile.cryptokoshbot.service.ReplyService;
import org.telegram.tomatophile.cryptokoshbot.service.SubscribeService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UnsubscribeUpdateCommand implements Command {
    @Getter
    private final String command = "/unsubscribeUpdate";

    private final ReplyService replyService;

    private final SubscribeService subscribeService;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        var chatId = update.getMessage().getChatId().toString();

        if(update.getMessage().getText().split(" ").length!=2){
            return List.of(replyService.getTextMessage(chatId, "Что-то в этой комманде не так"));
        }

        var figi = update.getMessage().getText().split(" ")[1];

        if(figi.length()!=3||!figi.matches("[A-Z]{3}")){
            return List.of(replyService.getTextMessage(chatId, "Я не нашёл у себя в базе такую валюту!"));
        }

        subscribeService.unsubscribeOnUpdateCurrency(chatId, figi);
        return List.of(replyService.getTextMessage(chatId, String.format("Вы отписаны от обновления курса %s", figi)));
    }
}
