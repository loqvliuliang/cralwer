package com.example.demo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by 刘亮 on 2017/11/13.
 */
@Service
public class ExceptionService {
    @Autowired
    MessageSource exceptionSource;


    public String getMessageDetailByCode(String errorCode, Object... objs) {

        try {
            return exceptionSource.getMessage(errorCode, objs, getLocale());
        } catch (NoSuchMessageException e) {
            return "";
        } catch (Exception e) {
            throw e;
        }
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}