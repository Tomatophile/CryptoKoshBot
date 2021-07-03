package org.telegram.tomatophile.cryptokoshbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

@Configuration
public class ImageConfig {
    @Bean
    public InputFile image(){
        var path = getClass().getClassLoader().getResource("postcard/2.jpg").getPath();

        var image = new InputFile(new File(path));

        return image;
    }
}
