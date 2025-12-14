package com.github.novabank.presentation.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/finance")
public class FinanceController {


@GetMapping("/summary")
public String summary() {
return "Financial summary";
}
}