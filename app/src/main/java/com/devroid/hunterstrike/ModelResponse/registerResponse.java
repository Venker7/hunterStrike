package com.devroid.hunterstrike.ModelResponse;


public class registerResponse {
    String status,success,error;

    public registerResponse(String status, String success, String error) {
        this.status = status;
        this.success = success;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
