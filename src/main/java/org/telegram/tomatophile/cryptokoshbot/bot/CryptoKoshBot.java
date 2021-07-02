package org.telegram.tomatophile.cryptokoshbot.bot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.tomatophile.bottemplate.AbstractTelegramBot;
import org.telegram.tomatophile.bottemplate.receiver.UpdateReceiver;

@Getter
@Setter
@Component
public class CryptoKoshBot extends AbstractTelegramBot {
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.username}")
    private String botUsername;

    public CryptoKoshBot(UpdateReceiver updateReceiver) {
        super(updateReceiver);
    }
}
