package org.telegram.tomatophile.cryptokoshbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyService {
    @Value("${telegram.bot.url}")
    private String baseUrl;
    @Value("${telegram.bot.token}")
    private String token;

    private final RestTemplate restTemplate;

    public ReplyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public SendMessage getTextMessage(String chatId, String text) {
        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        return sendMessage;
    }

    public void sendSticker(String chatId, String fileId){
        var url = baseUrl.concat(token).concat("/sendsticker?").concat("chat_id=").concat(chatId).concat("&sticker=").concat(fileId);

        restTemplate.postForLocation(url, HttpEntity.EMPTY);
    }
}
