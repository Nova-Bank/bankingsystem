package com.github.novabank.presentation.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {


@GetMapping("/{id}")
public String getAccount(@PathVariable String id) {
return "Account info for: " + id;
}
}