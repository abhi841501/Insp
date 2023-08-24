package com.example.insphiredapp.Api_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatCompanyModelData {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("booking_address_id")
    @Expose
    public Integer bookingAddressId;
    @SerializedName("employer_id")
    @Expose
    public String employerId;
    @SerializedName("employee_id")
    @Expose
    public String employeeId;
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("booking_id")
    @Expose
    public Integer bookingId;
    @SerializedName("booker_id")
    @Expose
    public String bookerId;
    @SerializedName("booked_id")
    @Expose
    public String bookedId;
    @SerializedName("joining_date")
    @Expose
    public String joiningDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("company_id")
    @Expose
    public Integer companyId;
    @SerializedName("company_image")
    @Expose
    public String companyImage;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("company_address")
    @Expose
    public String companyAddress;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("daily_rate")
    @Expose
    public String dailyRate;
    @SerializedName("emp_skills")
    @Expose
    public String empSkills;
    @SerializedName("cat_id")
    @Expose
    public Integer catId;
    @SerializedName("cat_name")
    @Expose
    public String catName;

    public Integer getId() {
        return id;
    }

    public Integer getBookingAddressId() {
        return bookingAddressId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getAmount() {
        return amount;
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

    public Integer getBookingId() {
        return bookingId;
    }

    public String getBookerId() {
        return bookerId;
    }

    public String getBookedId() {
        return bookedId;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getDailyRate() {
        return dailyRate;
    }

    public String getEmpSkills() {
        return empSkills;
    }

    public Integer getCatId() {
        return catId;
    }

    public String getCatName() {
        return catName;
    }
}
