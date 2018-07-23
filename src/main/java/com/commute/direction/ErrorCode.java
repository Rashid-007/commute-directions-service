package com.commute.direction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ErrorCode {

    String ERROR_CODE_FORMAT = "%d-%d %s";
    String ERROR_CODE_REGEX = "(\\d+-\\d+) .*";
    Pattern PATTERN = Pattern.compile(ERROR_CODE_REGEX);

    int getCodePrefix();

    int getCodeNumber();

    String getErrorMessage();

    default String build() {
        return String.format(ERROR_CODE_FORMAT, getCodePrefix(), getCodeNumber(), getErrorMessage());
    }

    static boolean isBusinessError(Throwable t) {
        if (t != null && t.getMessage() != null) {
            return isBusinessError(t.getMessage());
        }
        return false;
    }

    static String extractCode(Throwable t) {
        if (isBusinessError(t)) {
            return extractCode(t.getMessage());
        }
        return "";
    }

    static boolean isBusinessError(String message) {
        if (message != null) {
            return PATTERN.matcher(message).matches();
        }
        return false;
    }

    static String extractCode(String message) {
        if (isBusinessError(message)) {
            Matcher m = PATTERN.matcher(message);
            m.matches();
            return m.group(1);
        }
        return "";
    }

}
