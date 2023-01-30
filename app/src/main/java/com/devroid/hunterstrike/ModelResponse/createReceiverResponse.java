package com.devroid.hunterstrike.ModelResponse;

public class createReceiverResponse {
    String status,success;

    public createReceiverResponse(String status, String success) {
        this.status = status;
        this.success = success;
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
}
