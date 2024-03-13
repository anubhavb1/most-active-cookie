package com.anubhavb1.mostactivecookie.logparser;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class CookieLogRecord {
    @CsvBindByPosition(position = 0)
    private String cookie;

    @CsvBindByPosition(position = 1)
    @CsvDate(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime timestamp;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /** String representation of a cookie log */
    @Override
    public String toString() {
        return "Log Entry: Cookie=" + cookie + ", Timestamp=" + timestamp;
    }
}
