package com.digitalbooking.backend.Exceptions;

public class ErrorMessage {
    private String uri;
    private String errorMessage;


    public ErrorMessage(String uri, String errorMessage) {
        this.uri = uri;
        this.errorMessage = errorMessage;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMessage() {
        return errorMessage;
    }

    public void setMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error {" +
                "uri = '" + uri + '\'' +
                ", errorMessage = '" + errorMessage + '\'' +
                '}';
    }
}
