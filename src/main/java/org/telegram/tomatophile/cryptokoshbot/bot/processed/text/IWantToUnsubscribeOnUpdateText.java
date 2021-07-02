package org.telegram.tomatophile.cryptokoshbot.bot.processed.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.tomatophile.bottemplate.processed.text.Text;
import org.telegram.tomatophile.cryptokoshbot.service.ReplyService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IWantToUnsubscribeOnUpdateText implements Text {
    @Getter
    private final String text = "Хочу отписаться от обновлений курса криптовалюты.";

    private final ReplyService replyService;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        return List.of(replyService.getTextMessage(update.getMessage().getChatId().toString(), "Для этого напиши мне команду : /unsubscribeUpdate <FIGI валюты>"));
    }
}
