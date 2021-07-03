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
public class SubscribeFallCommand implements Command {
    @Getter
    @Value("${telegram.bot.processed.command.subscribeFall}")
    private String command;

    @Value("${telegram.bot.blank.unknownFormat}")
    private String unknownFormat;
    @Value("${telegram.bot.blank.unknownCurrency}")
    private String unknownCurrency;
    @Value("${telegram.bot.blank.fallPercentError}")
    private String fallPercentError;
    @Value("${telegram.bot.blank.successSubscribeFall}")
    private String successSubscribeFall;

    private final ReplyService replyService;

    private final SubscribeService subscribeService;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        var chatId = update.getMessage().getChatId().toString();

        if(update.getMessage().getText().split(" ").length!=3){
            return List.of(replyService.getTextMessage(chatId, unknownFormat));
        }

        var figi = update.getMessage().getText().split(" ")[1];

        if(figi.length()!=3||!figi.matches("[A-Za-z]{3}")){
            return List.of(replyService.getTextMessage(chatId, unknownCurrency));
        }

        var percent = update.getMessage().getText().split(" ")[2];
        int fallPercent;

        try{
            fallPercent = Integer.parseInt(percent);
        } catch (Exception e){
            return List.of(replyService.getTextMessage(chatId, fallPercentError));
        }
        if(fallPercent>100||fallPercent<0){
            return List.of(replyService.getTextMessage(chatId, fallPercentError));
        }

        subscribeService.subscribeOnFallCurrency(chatId, figi, fallPercent);
        return List.of(replyService.getTextMessage(chatId, String.format(successSubscribeFall, figi, fallPercent)));
    }
}
