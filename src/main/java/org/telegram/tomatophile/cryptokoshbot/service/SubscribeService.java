package org.telegram.tomatophile.cryptokoshbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.tomatophile.cryptokoshbot.pojo.Subscribe;

@Service
public class SubscribeService {
    @Value("${currencyListener.baseUrl}")
    private String currencyListenerUrl;
    @Value("${currencyListener.subscribeUpdateUrl}")
    private String subscribeUpdateUrl;
    @Value("${currencyListener.unsubscribeUpdateUrl}")
    private String unsubscribeUpdateUrl;
    @Value("${currencyListener.subscribeFallUrl}")
    private String subscribeFallUrl;

    private final RestTemplate restTemplate;

    public SubscribeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void subscribeOnUpdateCurrency(String chatId, String figi) {
        var url = currencyListenerUrl.concat(subscribeUpdateUrl);

        var subscribe = Subscribe.builder().chatId(chatId).figi(figi).build();

        restTemplate.postForObject(url, subscribe, Subscribe.class);
    }

    public void unsubscribeOnUpdateCurrency(String chatId, String figi) {
        var url = currencyListenerUrl.concat(unsubscribeUpdateUrl);

        var subscribe = Subscribe.builder().chatId(chatId).figi(figi).build();

        restTemplate.postForObject(url, subscribe, Subscribe.class);
    }

    public void subscribeOnFallCurrency(String chatId, String figi, int fallPercent) {
        var url = currencyListenerUrl.concat(subscribeFallUrl);

        var subscribe = Subscribe.builder().chatId(chatId).figi(figi).fallPercent(fallPercent).build();

        restTemplate.postForObject(url, subscribe, Subscribe.class);
    }
}
