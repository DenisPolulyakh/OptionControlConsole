package com.traderobot.www.exception;

public class SocketException extends Exception {
    public SocketException(String message, Throwable e) {
        super(message,e);
    }
}
