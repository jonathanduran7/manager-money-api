package com.managermoneyapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping
    @ResponseBody
    public String list() {
        return "account list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String show() {
        return "account show";
    }
}
