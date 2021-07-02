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
public class SubscribeFallCommand implements Command {
    @Getter
    private final String command = "/subscribeFall";

    private final ReplyService replyService;

    private final SubscribeService subscribeService;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        var chatId = update.getMessage().getChatId().toString();

        if(update.getMessage().getText().split(" ").length!=3){
            return List.of(replyService.getTextMessage(chatId, "Что-то в этой команде не так."));
        }

        var figi = update.getMessage().getText().split(" ")[1];
        var percent = update.getMessage().getText().split(" ")[2];
        int fallPercent;

        if(figi.length()!=3||!figi.matches("[A-Z]{1,3}")){
            return List.of(replyService.getTextMessage(chatId, "Я не нашёл у себя в базе такую валюту!"));
        }

        try{
            fallPercent = Integer.parseInt(percent);
        } catch (Exception e){
            return List.of(replyService.getTextMessage(chatId, "По моему, ты как-то не так указал процент падения."));
        }

        subscribeService.subscribeOnFallCurrency(chatId, figi, fallPercent);
        return List.of(replyService.getTextMessage(chatId, String.format("Вы подписаны на уведомление о падении %s на %s процентов", figi, fallPercent)));
    }
}
