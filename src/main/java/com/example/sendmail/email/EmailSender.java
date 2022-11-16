package com.example.sendmail.email;

import java.util.Map;

public interface EmailSender {
    void send(String to, Map<String, String> templateAttributes);
}
