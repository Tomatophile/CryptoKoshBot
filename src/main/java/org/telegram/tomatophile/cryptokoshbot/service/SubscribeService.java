package org.telegram.tomatophile.cryptokoshbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.tomatophile.cryptokoshbot.pojo.Event;
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
    @Value("${currencyListener.getOneUrl}")
    private String getOneUrl;

    private final RestTemplate restTemplate;

    public SubscribeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void subscribeOnUpdateCurrency(String chatId, String figi) {
        var url = currencyListenerUrl.concat(subscribeUpdateUrl);

        var subscribe = Subscribe.builder().chatId(chatId).figi(figi).build();

        restTemplate.postForObject(url, subscribe, Subscribe.class);
    }

    public ResponseEntity<Subscribe> unsubscribeOnUpdateCurrency(String chatId, String figi) {
        var url = currencyListenerUrl.concat(unsubscribeUpdateUrl);

        var subscribe = Subscribe.builder().chatId(chatId).figi(figi).build();

        return restTemplate.postForEntity(url, subscribe, Subscribe.class);
    }

    public void subscribeOnFallCurrency(String chatId, String figi, Double fallPercent) {
        var url = currencyListenerUrl.concat(subscribeFallUrl);

        var subscribe = Subscribe.builder().chatId(chatId).figi(figi).fallPercent(fallPercent).build();

        restTemplate.postForObject(url, subscribe, Subscribe.class);
    }

    public ResponseEntity<Event> getOne(String figi) {
        var url = currencyListenerUrl.concat(getOneUrl).concat(figi);

        return restTemplate.getForEntity(url, Event.class);
    }
}
