package com.example.sendmail.service;

import com.example.sendmail.appuser.AppUser;
import com.example.sendmail.appuser.AppUserRole;
import com.example.sendmail.appuser.ConfirmationToken;
import com.example.sendmail.form.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class RegistrationService {

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest registrationRequest, HttpServletRequest request) {
        boolean inValidEmail = emailValidator.test(registrationRequest.getEmail());
        if (!inValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.signupUser(new AppUser(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getPassword(),
                registrationRequest.getEmail(),
                AppUserRole.USER
        ), request);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirm");
        }

        LocalDateTime presentTime = LocalDateTime.now();
        LocalDateTime expiredTime = confirmationToken.getExpiredTime();

        if (expiredTime.isBefore(presentTime)) {
            throw new IllegalStateException("Token expired");
        }

        confirmationToken.setConfirmedAt(presentTime);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Confirmed";
    }
}
