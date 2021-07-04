package org.telegram.tomatophile.cryptokoshbot.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subscribe {
    private String chatId;
    private String figi;
    private Double fallPercent;
    private Double lastPrice;
}
