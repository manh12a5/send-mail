package com.example.sendmail.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //Pattern email
        Pattern pattern = Pattern.compile("^\\w+[A-Za-z0-9.]+@\\w+.\\w{2,6}$");
        if (!pattern.matcher(s).matches()) {
            return false;
        } else {
            return true;
        }
    }
}
