package com.watlas.projetoessentials.util;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtil {
    public String formatLocalDatetime(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDateTime);
    }
}
