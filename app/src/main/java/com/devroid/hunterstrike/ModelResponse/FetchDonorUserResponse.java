package com.devroid.hunterstrike.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchDonorUserResponse {

    @SerializedName("users ")
    List<DonorUser> users;
    String status;

    public FetchDonorUserResponse(List<DonorUser> users, String status) {
        this.users = users;
        this.status = status;
    }
    public List<DonorUser> getDonorUserList() {
        return users;
    }

    public List<DonorUser> getUsers() {
        return users;
    }

    public void setUsers(List<DonorUser> users) {
        this.users = users;
    }

    public void setDonorUserList(List<DonorUser> donorUserList) {
        this.users = donorUserList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
