package org.telegram.tomatophile.cryptokoshbot.bot.processed.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.tomatophile.bottemplate.keyboard.ReplyKeyboardMarkupBuilder;
import org.telegram.tomatophile.bottemplate.processed.command.Command;
import org.telegram.tomatophile.bottemplate.processed.text.Text;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {
    @Getter
    private final String command = "/start";

    private final List<Text> texts;

    @Override
    public List<PartialBotApiMethod<Message>> process(Update update) {
        var keyboard = ReplyKeyboardMarkupBuilder.create(update.getMessage().getChatId().toString());

        keyboard.setText("Привет, "+update.getMessage().getChat().getFirstName()+ "!\n" +
                "Меня зовут cryptoKosh_bot." +
                " Я бот, который помогает тем, кто хочет знать самую актуальную информацию о курсе криптовалют." +
                " Здесь можно оформить подписку на обновления их курса.");

        for (var i = 0; i < texts.size(); i++) {
            keyboard.addButton(texts.get(i).getText());
            if (i == texts.size() - 1) {
                break;
            }
            keyboard.nextRow();
        }

        return List.of(keyboard.build());
    }
}
