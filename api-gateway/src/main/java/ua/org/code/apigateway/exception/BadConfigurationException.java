package ua.org.code.apigateway.exception;

public class BadConfigurationException extends RuntimeException {
    public BadConfigurationException(String message) {
        super(message);
    }
}
