package com.managermoneyapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseBody
    public String create() {
        return "account create";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String update(@PathVariable Long id) {
        return "account update" + id;
    }
}
