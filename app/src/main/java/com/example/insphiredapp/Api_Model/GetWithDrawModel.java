package com.example.insphiredapp.Api_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWithDrawModel {

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("debit")
    @Expose
    public List<GetWithDrawDebitData> debit;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<GetWithDrawDebitData> getDebit() {
        return debit;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    @SerializedName("total_amount")
    @Expose
    public Integer totalAmount;
}
