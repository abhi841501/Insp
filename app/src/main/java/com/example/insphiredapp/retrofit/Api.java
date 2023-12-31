package com.example.insphiredapp.retrofit;

import com.example.insphiredapp.ApiModelEmployer.EmployerProfileModel;
import com.example.insphiredapp.ApiModelEmployer.ForgotPassword;
import com.example.insphiredapp.Api_Model.AllEmployeeListModel;
import com.example.insphiredapp.Api_Model.BookingModel;
import com.example.insphiredapp.Api_Model.CancelEmployeeHistoryModel;
import com.example.insphiredapp.Api_Model.CancelModel;
import com.example.insphiredapp.Api_Model.ChangePasswordModel;
import com.example.insphiredapp.Api_Model.ChatCompanyModel;
import com.example.insphiredapp.Api_Model.CreateJobModel;
import com.example.insphiredapp.Api_Model.CreateSlotModel;
import com.example.insphiredapp.Api_Model.EarningModel;
import com.example.insphiredapp.Api_Model.EditEmployerProfileModel;
import com.example.insphiredapp.Api_Model.EmployeeBookedListModel;
import com.example.insphiredapp.Api_Model.EmployeeEditProfileModel;
import com.example.insphiredapp.Api_Model.EmployeeHistoryModel;
import com.example.insphiredapp.Api_Model.EmployeeProfileModelFirst;
import com.example.insphiredapp.Api_Model.Favourite_employee_model;
import com.example.insphiredapp.Api_Model.FilterModel;
import com.example.insphiredapp.Api_Model.GetBookingDetailModel;
import com.example.insphiredapp.Api_Model.GetCategoryModel;
import com.example.insphiredapp.Api_Model.GetCreateSlotsModel;
import com.example.insphiredapp.Api_Model.GetCreditModel;
import com.example.insphiredapp.Api_Model.GetEditEmployeeProfileModel;
import com.example.insphiredapp.Api_Model.GetEmployeeHistoryModel;
import com.example.insphiredapp.Api_Model.GetFavouriteModel;
import com.example.insphiredapp.Api_Model.GetProfileDetailsModel;
import com.example.insphiredapp.Api_Model.GetReviewModel;
import com.example.insphiredapp.Api_Model.GetWithDrawModel;
import com.example.insphiredapp.Api_Model.GiveRatingModel;
import com.example.insphiredapp.Api_Model.LoginModel;
import com.example.insphiredapp.Api_Model.MsgEmployee;
import com.example.insphiredapp.Api_Model.MyJobModel;
import com.example.insphiredapp.Api_Model.PaymentHistoryModel;
import com.example.insphiredapp.Api_Model.PostProfieEmpApi;
import com.example.insphiredapp.Api_Model.RegisterModel;
import com.example.insphiredapp.Api_Model.ResetPasswordModel;
import com.example.insphiredapp.Api_Model.ShowCvModel;
import com.example.insphiredapp.Api_Model.UpComingJobModel;
import com.example.insphiredapp.Api_Model.UserChatModel;
import com.example.insphiredapp.Api_Model.WithdrawModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface Api {



    @FormUrlEncoded
    @POST("user_register")
    Call<RegisterModel> REGISTER_MODEL_CALL(
            @Field("first_name") String firstname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("Mobile") String mobile,
            @Field("user_type") String userType);

    @FormUrlEncoded
    @POST("user_login")
    Call<LoginModel> LOGIN_MODEL_CALL(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("device_token") String token,
                                      @Field("user_type") String userType);

    @FormUrlEncoded
    @POST("user_change_password")
    Call<ChangePasswordModel> CHANGE_PASSWORD_MODEL_CALL(@Field("user_id") String userId,
                                                         @Field("old_password") String oldPassword,
                                                         @Field("new_password") String newPassword,
                                                         @Field("user_type") String userType);
    @Multipart
    @POST("employer_update_profile")
    Call<EmployerProfileModel> EMPLOYER_PROFILE_MODEL_CALL(@Part("user_id") RequestBody user_id,
                                                           @Part MultipartBody.Part image,
                                                           @Part("company_email") RequestBody company_email,
                                                           @Part("company_name") RequestBody company_name,
                                                           @Part("address")  RequestBody companyAddress,
                                                           @Part("mobile") RequestBody mobile,
                                                           @Part("term_condition")RequestBody term_condition);


    @GET()
    Call<GetProfileDetailsModel> GET_PROFILE_DETAILS_MODEL_CALL(@Url String str);


    @Multipart
    @POST("edit_profile")
    Call<EditEmployerProfileModel> EDIT_EMPLOYER_PROFILE_MODEL_CALL(@Part("user_id") RequestBody user_id,
                                                               @Part MultipartBody.Part image,
                                                               @Part("user_name") RequestBody user_name,
                                                                    @Part("email") RequestBody company_email,
                                                               @Part("mobile") RequestBody mobile,
                                                                    @Part("company_name")  RequestBody companyAddress,
                                                                    @Part("location") RequestBody company_name,
                                                                    @Part("user_type")  RequestBody user_type);


    @GET("all_category")
    Call<GetCategoryModel>GET_CATEGORY_MODEL_CALL();


    @GET()
    Call<EmployeeProfileModelFirst>EMPLOYEE_PROFILE_MODEL_FIRST_CALL(@Url String str);

    @Multipart
    @POST("user_update_profile")
    Call<PostProfieEmpApi> POST_PROFIE_EMP_API_CALL(@Part("user_id") RequestBody user_id,
                                                    @Part("emp_id") RequestBody emp_id,
                                                    @Part("daily_rate") RequestBody daily_rate,
                                                    @Part("emp_history") RequestBody emp_history,
                                                    @Part("location")  RequestBody location,
                                                    @Part("term_condition")  RequestBody term_condition,
                                                    @Part MultipartBody.Part emp_cv,
                                                    @Part MultipartBody.Part emp_image,
                                                    @Part("cat_id") RequestBody cat_id);
/*
    @GET()
    Call<GetProfieEmpApi>GET_PROFIE_EMP_API_CALL(@Url String str);*/




    @Multipart
    @POST("edit_profile")
    Call<EmployeeEditProfileModel> EMPLOYEE_EDIT_PROFILE_MODEL_CALL(@Part("user_id") RequestBody user_id,
                                                               @Part MultipartBody.Part image,
                                                               @Part("user_name") RequestBody user_name,
                                                               @Part("email") RequestBody email,
                                                               @Part("mobile") RequestBody mobile,
                                                               @Part("daily_rate")  RequestBody daily_rate,
                                                               @Part("location")RequestBody location,
                                                               @Part("user_type")RequestBody user_type);




    @GET()
    Call<GetEditEmployeeProfileModel>GET_EDIT_EMPLOYEE_PROFILE_MODEL_CALL(@Url String str);





    @FormUrlEncoded
    @POST("user_forgot_password")
    Call<ForgotPassword> FORGOT_PASSWORD_CALL(@Field("mobile_no") String mobile_no,
                                              @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("create_jobs")
    Call<CreateJobModel> CREATE_JOB_MODEL_CALL(@Field("employer_id") String employeeId,
                                               @Field("cat_id") String catId,
                                               @Field("job_title") String jobTitle,
                                               @Field("job_summary") String jobSummary,
                                               @Field("hourly_rate") String hourlyRate,
                                               @Field("daily_rate") String dailyRate);


    @FormUrlEncoded
    @POST("user_password_save")
    Call<ResetPasswordModel> RESET_PASSWORD_MODEL_CALL(@Field("new_password") String new_password,
                                                       @Field("confirm_password") String C_password,
                                                       @Field("user_id") String user,
                                                       @Field("user_type") String user_type);




    @FormUrlEncoded
    @POST("employer_id")
    Call<CreateJobModel> CREATE_JOB_MODEL_CALL(@Field("employer_id") String employer_Id,
                                               @Field("cat_id") String catId,
                                               @Field("job_title") String jobTitle,
                                               @Field("job_summary") String jobSummary,
                                               @Field("hourly_rate") String HourlyRate,
                                               @Field("daily_rate") String DailyRate,
                                               @Field("start_date") String start_date,
                                               @Field("end_date") String end_date);


    @GET()
    Call<AllEmployeeListModel> ALL_EMPLOYEE_LIST_MODEL_CALL(@Url String str);

    @FormUrlEncoded
    @POST("add_to_fav")
    Call<Favourite_employee_model> FAVOURITE_EMPLOYEE_MODEL_CALL(@Field("employee_id") String employee_id,
                                                                 @Field("employer_id") String employer_id);


    @GET()
    Call<GetFavouriteModel> GET_FAVOURITE_MODEL_CALL(@Url String str);


    @FormUrlEncoded
    @POST("save_rating")
    Call<GiveRatingModel> GIVE_RATING_MODEL_CALL(@Field("user_id") String user_id,
                                                 @Field("employee_id") String employee_id,
                                                 @Field("rating") String rating,
                                                 @Field("comment") String comment);

    @FormUrlEncoded
    @POST("filter_data")
    Call<FilterModel> FILTER_MODEL_CALL(@Field("cat_id") String cat_id,
                                        @Field("location") String location,
                                        @Field("per_day") String per_day,
                                        @Field("price_range") String price_range);


    @GET()
    Call<GetBookingDetailModel>GET_BOOKING_DETAIL_MODEL_CALL(@Url String str);


    @FormUrlEncoded
    @POST("employee_book")
    Call<BookingModel> BOOKING_MODEL_CALL(@Field("employee_id") String employee_Id,
                                          @Field("user_id") String user_id,
                                          @Field("amount") String amount,
                                          @Field("time_slot_id") String time_slot_id);


    @GET()
    Call<EmployeeBookedListModel> EMPLOYEE_BOOKED_LIST_MODEL_CALL(@Url String str);


    @FormUrlEncoded
    @POST("employee_cancel")
    Call<CancelModel> CANCEL_MODEL_CALL(@Field("id") String id);


    @GET()
    Call<GetEmployeeHistoryModel> GET_EMPLOYEE_HISTORY_MODEL_CALL(@Url String str);

    @GET()
    Call<GetReviewModel> GET_REVIEW_MODEL_CALL(@Url String str);

    @GET()
    Call<PaymentHistoryModel> PAYMENT_HISTORY_MODEL_CALL(@Url String str);

    @GET()
    Call<MyJobModel>MY_JOB_MODEL_CALL(@Url String str);


    @GET()
    Call<UpComingJobModel> UP_COMING_JOB_MODEL_CALL(@Url String str);




    @GET()
    Call<ChatCompanyModel> CHAT_COMPANY_MODEL_CALL(@Url String str);

    @GET()
    Call<MsgEmployee> MSG_EMPLOYEE_CALL(@Url String str);


    @FormUrlEncoded
    @POST("user_chat")
    Call<UserChatModel> USER_CHAT_MODEL_CALL(@Field("employer_id") String employer_id,
                                             @Field("employee_id") String employee_id,
                                             @Field("chat_message") String chat_message);


    @FormUrlEncoded
    @POST("withdraw_amount")
    Call<WithdrawModel> WITHDRAW_MODEL_CALL(@Field("user_id") String user_id,
                                            @Field("amount") String amount);

    @GET()
    Call<GetWithDrawModel> GET_WITH_DRAW_MODEL_CALL(@Url String Str);

    @GET()
    Call<GetCreditModel>GET_CREDIT_MODEL_CALL(@Url String str);

    @FormUrlEncoded
    @POST("create_timeslot")
    Call<CreateSlotModel> CREATE_SLOT_MODEL_CALL(@Field("user_id") String user_id,
                                                 @Field("start_date") String start_date,
                                                 @Field("end_date") String end_date,
                                                 @Field("start_time") String start_time,
                                                 @Field("end_time") String end_time);

    @GET()
    Call<GetCreateSlotsModel>GET_CREATE_SLOTS_MODEL_CALL(@Url String str);



    @GET()
    Call<EarningModel>EARNING_MODEL_CALL(@Url String Str);


    @GET()
    Call<EmployeeHistoryModel>EMPLOYEE_HISTORY_MODEL_CALL(@Url String Str);


    @GET()
    Call<CancelEmployeeHistoryModel>CANCEL_EMPLOYEE_HISTORY_MODEL_CALL(@Url String Str);

    @GET()
    Call<ShowCvModel>SHOW_CV_MODEL_CALL(@Url String str);










}
