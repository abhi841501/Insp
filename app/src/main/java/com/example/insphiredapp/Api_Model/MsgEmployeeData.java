package com.example.insphiredapp.Api_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MsgEmployeeData {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("employee_id")
    @Expose
    public String employeeId;
    @SerializedName("employer_id")
    @Expose
    public String employerId;
    @SerializedName("chat_message")
    @Expose
    public String chatMessage;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;

    public Integer getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
