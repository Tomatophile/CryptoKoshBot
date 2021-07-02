package org.telegram.tomatophile.cryptokoshbot;

import org.telegram.tomatophile.bottemplate.TelegramApplication;
import org.telegram.tomatophile.bottemplate.TelegramBotApplication;

@TelegramBotApplication
public class CryptoKoshApp {
    public static void main(String[] args) {
        TelegramApplication.run(CryptoKoshApp.class, args);
    }
}
