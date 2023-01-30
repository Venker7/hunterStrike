package com.devroid.hunterstrike.ModelResponse;

import java.util.List;

public class fetchReceiveUserResponse {
    List<ReceiverUser> success;
    String status;

    public fetchReceiveUserResponse(List<ReceiverUser> success, String status) {
        this.success = success;
        this.status = status;
    }
    public List<ReceiverUser> getReceiverUserList(){
        return success;
    }


    public List<ReceiverUser> getSuccess() {
        return success;
    }

    public void setSuccess(List<ReceiverUser> success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
