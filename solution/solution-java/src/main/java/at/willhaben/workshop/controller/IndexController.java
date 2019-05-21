package at.willhaben.workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ModelAndView index() {
        String s = UriComponentsBuilder.fromHttpUrl("https://devjourfix-willhaben-test.auth.eu-central-1.amazoncognito.com/login")
                .queryParam("client_id", "5vkkniseds4i1s4pst0uoe280u")
                .queryParam("redirect_uri", "http://localhost:8080/callback")
                .queryParam("scope", "openid devjourfix.willhaben.test/devjourfix")
                .queryParam("response_type", "code")
                .build().toString();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("link",s);
        return modelAndView;
    }

}
