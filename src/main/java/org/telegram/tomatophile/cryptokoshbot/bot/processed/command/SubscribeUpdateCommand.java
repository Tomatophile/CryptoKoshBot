package org.telegram.tomatophile.cryptokoshbot.bot.processed.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
public class SubscribeUpdateCommand implements Command {
    @Getter
    @Value("${telegram.bot.processed.command.subscribeUpdate}")
    private String command;

    @Value("${telegram.bot.blank.unknownFormat}")
    private String unknownFormat;
    @Value("${telegram.bot.blank.unknownCurrency}")
    private String unknownCurrency;
    @Value("${telegram.bot.blank.successSubscribeUpdate}")
    private String successSubscribeUpdate;

    private final ReplyService replyService;

    private final SubscribeService subscribeService;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        var chatId = update.getMessage().getChatId().toString();

        if(update.getMessage().getText().split(" ").length!=2){
            return List.of(replyService.getTextMessage(chatId, unknownFormat));
        }

        var figi = update.getMessage().getText().split(" ")[1];

        if(figi.length()!=3||!figi.matches("[A-Za-z]{3}")){
            return List.of(replyService.getTextMessage(chatId, unknownCurrency));
        }

        subscribeService.subscribeOnUpdateCurrency(chatId, figi);
        return List.of(replyService.getTextMessage(chatId, String.format(successSubscribeUpdate, figi)));
    }
}
