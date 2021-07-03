package org.telegram.tomatophile.cryptokoshbot.bot.processed.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.tomatophile.bottemplate.processed.text.Text;
import org.telegram.tomatophile.cryptokoshbot.service.ReplyService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IWantGetOneText implements Text {
    @Getter
    @Value("${telegram.bot.processed.text.iWantGetOne}")
    private String text;

    @Value("${telegram.bot.blank.guideGetOne}")
    private String guideGetOne;

    private final ReplyService replyService;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        return List.of(replyService.getTextMessage(update.getMessage().getChatId().toString(), guideGetOne));
    }
}
