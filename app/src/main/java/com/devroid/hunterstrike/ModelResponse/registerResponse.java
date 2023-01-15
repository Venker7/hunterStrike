package com.devroid.hunterstrike.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class registerResponse {
    String error;
    String message;

    public registerResponse(String error, String msg) {
        this.error = error;
        this.message = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}
