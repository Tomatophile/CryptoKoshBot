package org.telegram.tomatophile.cryptokoshbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.tomatophile.cryptokoshbot.pojo.Event;
import org.telegram.tomatophile.cryptokoshbot.service.EventService;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/event/update")
    public ResponseEntity<Event> update(@RequestBody Event event) {
        eventService.sendUpdate(event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/event/fall")
    public ResponseEntity<Event> fall(@RequestBody Event event) {
        eventService.sendFall(event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/event/error")
    public ResponseEntity<String> error(@RequestBody String chatId) {
        eventService.sendError(chatId);
        return ResponseEntity.ok(chatId);
    }

    @GetMapping("/event/dontsleep")
    public ResponseEntity<String> dontSleep() {
        return ResponseEntity.ok("I'm Fine!");
    }
}
