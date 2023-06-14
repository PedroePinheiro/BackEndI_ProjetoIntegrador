package com.example.consultorio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
}
@Controller
class AuthController {
    @GetMapping("/") public String home() { return "index"; }
}