package org.telegram.tomatophile.cryptokoshbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.tomatophile.cryptokoshbot.pojo.Event;
import org.telegram.tomatophile.cryptokoshbot.service.EventService;
import org.telegram.tomatophile.cryptokoshbot.service.ReplyService;

@RestController
@RequiredArgsConstructor
public class EventController {

    @Value("${telegram.bot.stickers.success}")
    private String successSticker;
    @Value("${telegram.bot.stickers.srError}")
    private String errorSticker;


    private final EventService eventService;

    private final ReplyService replyService;

    @PostMapping("/event/update")
    public ResponseEntity<Event> update(@RequestBody Event event) {
        eventService.sendUpdate(event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/event/fall")
    public ResponseEntity<Event> fall(@RequestBody Event event) {
        replyService.sendSticker(event.getChatId(), successSticker);
        eventService.sendFall(event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/event/error")
    public ResponseEntity<String> error(@RequestBody String chatId) {
        replyService.sendSticker(chatId, errorSticker);
        eventService.sendError(chatId);
        return ResponseEntity.ok(chatId);
    }

    @GetMapping("/event/dontsleep")
    public ResponseEntity<String> dontSleep() {
        return ResponseEntity.ok("I'm Fine!");
    }
}
