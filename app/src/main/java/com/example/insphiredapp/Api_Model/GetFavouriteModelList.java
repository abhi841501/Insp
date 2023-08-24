package com.example.insphiredapp.Api_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFavouriteModelList {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("id_number")
    @Expose
    public String idNumber;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("emp_image")
    @Expose
    public String empImage;
    @SerializedName("hourly_rate")
    @Expose
    public String hourlyRate;
    @SerializedName("daily_rate")
    @Expose
    public String dailyRate;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("emp_skills")
    @Expose
    public String empSkills;
    @SerializedName("start_date")
    @Expose
    public String startDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("cat_id")
    @Expose
    public Integer catId;
    @SerializedName("cat_name")
    @Expose
    public String catName;
    @SerializedName("rating")
    @Expose
    public Double rating;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpImage() {
        return empImage;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public String getDailyRate() {
        return dailyRate;
    }

    public String getAddress() {
        return address;
    }

    public String getEmpSkills() {
        return empSkills;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getCatId() {
        return catId;
    }

    public String getCatName() {
        return catName;
    }

    public float getRating() {
        return Float.parseFloat(String.valueOf(rating));
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
