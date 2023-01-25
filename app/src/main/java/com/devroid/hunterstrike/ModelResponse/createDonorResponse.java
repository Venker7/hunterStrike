package com.devroid.hunterstrike.ModelResponse;

public class createDonorResponse {
String status,success;

    public createDonorResponse(String status, String success) {
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
