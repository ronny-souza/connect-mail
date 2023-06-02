package br.com.connectmail.exception;

public class SendMailException extends RuntimeException {

    public SendMailException() {
        super();
    }

    public SendMailException(String message) {
        super(message);
    }
}
