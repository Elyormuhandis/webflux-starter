package uz.muhandis.webflux.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import uz.muhandis.webflux.domain.Message;

@RestController
@RequestMapping("/main")
public class MainController {
    @GetMapping
    public Flux<Message> list(@RequestParam(defaultValue = "0L") Long start, @RequestParam(defaultValue = "5L") Long count) {
        return Flux
                .just(  "Hello1",
                        "Hello2",
                        "Hello3",
                        "Hello4",
                        "Hello5"
                )
                .skip(start).take(count).map(Message::new);

    }
}
