package com.devroid.hunterstrike.ModelResponse;

import java.util.List;

public class fetchEventResponse {
    List<event> users;
    String status;

    public fetchEventResponse(List<event> users, String status) {
        this.users = users;
        this.status = status;
    }

    public List<event> getUsers() {
        return users;
    }

    public void setUsers(List<event> users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
