package com.is1di.remotecoffeemachine.service;

import com.is1di.remotecoffeemachine.message.MessageBase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String getMessage(MessageBase message) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message.method().getMessage(), message.args(), locale);
    }
}
