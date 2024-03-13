package com.anubhavb1.mostactivecookie.cookiefilter;

import com.anubhavb1.mostactivecookie.exception.LogParsingException;
import com.anubhavb1.mostactivecookie.logparser.CommandInput;

/** Defines the contract for implementing cookie filtering logic. */
public interface CookieFilter {
    void filterMostActiveCookies(CommandInput commandInput) throws LogParsingException;
}
