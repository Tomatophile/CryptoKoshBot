package org.telegram.tomatophile.cryptokoshbot.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.tomatophile.cryptokoshbot.bot.CryptoKoshBot;
import org.telegram.tomatophile.cryptokoshbot.pojo.Event;

@Service
@RequiredArgsConstructor
public class EventService {
    @Value("${telegram.bot.blank.updateEvent}")
    private String updateEvent;
    @Value("${telegram.bot.blank.fallEvent}")
    private String fallEvent;
    @Value("${telegram.bot.blank.errorEvent}")
    private String errorEvent;

    private final CryptoKoshBot cryptoKoshBot;

    @SneakyThrows
    public void sendUpdate(Event event){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(event.getChatId());
        sendMessage.setText(String.format(updateEvent, event.getFigi(), event.getPrice()));

        cryptoKoshBot.execute(sendMessage);
    }

    @SneakyThrows
    public void sendFall(Event event){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(event.getChatId());
        sendMessage.setText(String.format(fallEvent, event.getFigi(), event.getPrice()));

        cryptoKoshBot.execute(sendMessage);
    }

    @SneakyThrows
    public void sendError(String chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(errorEvent);

        cryptoKoshBot.execute(sendMessage);
    }
}
