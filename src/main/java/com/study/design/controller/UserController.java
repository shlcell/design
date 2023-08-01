package com.study.design.controller;

import com.study.design.pojo.TicketParam;
import com.study.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/suggest")
    public List<String> suggestRequirement(@RequestParam String username) {
        return userService.suggestRequirement(username);
    }

    @PostMapping("/ticket")
    public Object getTicket(@RequestBody TicketParam ticketParam) {
        return userService.getTicket(ticketParam);
    }

    @PostMapping("/login")
    public Boolean login(@RequestParam String name, @RequestParam String pwd, @RequestParam String type) {
        return userService.login(name, pwd, type);
    }
}
