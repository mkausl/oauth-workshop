package at.willhaben.workshop.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Controller
@RequestMapping("/callback")
public class CallbackController {

    @GetMapping
    public String get(@RequestParam("code") String code) throws IOException, InterruptedException {
        String exchange ="https://devjourfix-willhaben-test.auth.eu-central-1.amazoncognito.com/oauth2/token";


        String header = Base64.getEncoder().encodeToString("5vkkniseds4i1s4pst0uoe280u:1r1lkqea5tuh4scm7lkd42mcl32pgrbpkim5gvsp6bvgne7e55f7".getBytes());
        String body = "code=" + code + "&grant_type=authorization_code&client_id=5vkkniseds4i1s4pst0uoe280u&redirect_uri=http://localhost:8080/callback";
        HttpRequest authorization = HttpRequest.newBuilder().uri(URI.create(exchange))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic "+header)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> send = HttpClient.newHttpClient().send(authorization, HttpResponse.BodyHandlers.ofString());
        String result = send.body();
        return "callback-1";
    }

}
