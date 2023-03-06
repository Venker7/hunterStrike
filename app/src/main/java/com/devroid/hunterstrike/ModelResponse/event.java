package com.devroid.hunterstrike.ModelResponse;

public class event {
    String event,message;

    public event(String event, String message) {
        this.event = event;
        this.message = message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
