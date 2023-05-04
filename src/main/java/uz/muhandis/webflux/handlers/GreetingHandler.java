package uz.muhandis.webflux.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.muhandis.webflux.domain.Message;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request){
        Flux<Message> data = Flux
                .just("Hello1",
                        "Hello2",
                        "Hello3",
                        "Hello4",
                        "Hello5"
                )
                .map(Message::new);
        Long start = request.queryParam("start").map(Long::valueOf).orElse(0L);
        Long count = request.queryParam("count").map(Long::valueOf).orElse(0L);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(data.skip(start).take(count), Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest request){
            String user = request.queryParam("user").orElse("No body");
            return ServerResponse.ok().render("index", Map.of("user", user));
    }
}
