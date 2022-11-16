package com.example.sendmail.controller;

import com.example.sendmail.form.RegistrationRequest;
import com.example.sendmail.service.AppUserService;
import com.example.sendmail.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest request) {
        return registrationService.register(registrationRequest, request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
