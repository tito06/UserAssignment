package com.example.userassignment.model;

public class LoginResponse {

    private boolean error;

    public LoginResponse(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
