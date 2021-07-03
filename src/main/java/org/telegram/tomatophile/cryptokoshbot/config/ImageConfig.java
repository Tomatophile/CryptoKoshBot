package org.telegram.tomatophile.cryptokoshbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
@Configuration
public class ImageConfig {
    @Bean
    public InputFile images(){
        var path = getClass().getClassLoader().getResource("img/2.jpg").getPath();
        return new InputFile(new File(path));
    }
}
