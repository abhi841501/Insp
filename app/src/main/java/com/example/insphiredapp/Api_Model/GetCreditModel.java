package com.example.insphiredapp.Api_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCreditModel {


    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("credit")
    @Expose
    public List<GetCreditModelData> credit;
    @SerializedName("total_amount")
    @Expose
    public Integer totalAmount;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<GetCreditModelData> getCredit() {
        return credit;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }
}
