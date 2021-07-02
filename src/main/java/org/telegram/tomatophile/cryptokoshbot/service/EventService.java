package org.telegram.tomatophile.cryptokoshbot.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.tomatophile.cryptokoshbot.bot.CryptoKoshBot;
import org.telegram.tomatophile.cryptokoshbot.pojo.Event;

@Service
@RequiredArgsConstructor
public class EventService {

    private final CryptoKoshBot cryptoKoshBot;

    @SneakyThrows
    public void sendUpdate(Event event){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(event.getChatId());
        sendMessage.setText(String.format("Стоимость %s составляет $%s", event.getFigi(), event.getPrice()));

        cryptoKoshBot.execute(sendMessage);
    }

    @SneakyThrows
    public void sendFall(Event event){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(event.getChatId());
        sendMessage.setText(String.format("Стоимость %s упала, и теперь составляет $%s", event.getFigi(), event.getPrice()));

        cryptoKoshBot.execute(sendMessage);
    }
}
