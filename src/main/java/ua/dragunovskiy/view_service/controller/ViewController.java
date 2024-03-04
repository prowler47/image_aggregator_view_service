package ua.dragunovskiy.view_service.controller;


import lombok.Getter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@Getter
public class ViewController {

    @GetMapping("/test")
    public String test() {
        return "index";
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/submitUrl")
    public String getUrl(@RequestParam("url") String url, Model model) {
        model.addAttribute("url", url);
        rabbitTemplate.convertAndSend("Test-exchange", "url", url);
        System.out.println(url);
        return "redirect:/test";
    }
}