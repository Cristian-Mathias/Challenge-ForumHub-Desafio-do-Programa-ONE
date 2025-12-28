package br.com.challenge.forumhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public String status(){
        return "API FórumHub está rodando e o acesso público está liberado.";
    }
}
