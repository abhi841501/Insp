package com.example.insphiredapp.EmployerActivity;

import static com.example.insphiredapp.Util.Permission.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.insphiredapp.Adapter.NonNull;
import com.example.insphiredapp.ApiModelEmployer.EmployerProfileModel;
import com.example.insphiredapp.Api_Model.GetProfileDetailsData;
import com.example.insphiredapp.Api_Model.GetProfileDetailsModel;
import com.example.insphiredapp.R;
import com.example.insphiredapp.retrofit.Api;
import com.example.insphiredapp.retrofit.Api_Client;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEmployerFirstActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 200;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 101;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    ImageView backArowwwww;
    CircleImageView userProfileFirst;
    ImageView addImageFirst;
    TextView empNameFirst, empNoFirst, empEmailFirst, termConditionEmployerr;
    EditText CompNameee, CompEmail, CompPhone, CompAddress;
    AppCompatButton ProfileFirstSubButton;
    CheckBox checkboxEmployerrrr;
    ImageView imageView;
    Button cameraButton, galleryButton;
    GetProfileDetailsData getProfileDetailsData;
    File profile_fileImage;
    private String strCheck = "0", strUserId, strFirstname, strEmail, strPhone,StringName,StringEmail,StringPhone,StringAddress;
    private Uri imageUri6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_employer_first);
        inits();
        backArowwwww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        addImageFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog addProfileUpdate = new Dialog(ProfileEmployerFirstActivity.this);
                addProfileUpdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
                addProfileUpdate.setContentView(R.layout.gallary_camera_popup);

                imageView = addProfileUpdate.findViewById(R.id.imageView);
                cameraButton = addProfileUpdate.findViewById(R.id.cameraButton);
                galleryButton = addProfileUpdate.findViewById(R.id.galleryButton);

                addProfileUpdate.show();
                Window window = addProfileUpdate.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                galleryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  openGallery(PICK_IMAGE);
                        openGallery();
                        addProfileUpdate.dismiss();
                    }
                });

                cameraButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        requestCameraPermission();
                        addProfileUpdate.dismiss();
                    }
                });
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            // Permission already granted, you can perform file operations here
        }


        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        strUserId = getUserIdData.getString("Id", "");
        Log.e("changePassword", "change" + strUserId);

        getProfileDetailsApi();

        ProfileFirstSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxEmployerrrr.isChecked()) {
                    strCheck = "1";
                }
                StringName = CompNameee.getText().toString();
                StringEmail = CompEmail.getText().toString();
                StringPhone = CompPhone.getText().toString();
                StringAddress = CompAddress.getText().toString();
                if (validation()) {

                    addProfileEmployerApi();
                }

            }
        });

        termConditionEmployerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileEmployerFirstActivity.this, WebViewEmployerActivity.class);
                startActivity(intent);
            }
        });


    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            openCamera();
        }
    }

    private void inits() {
        backArowwwww = findViewById(R.id.backArowwwww);
        userProfileFirst = findViewById(R.id.userProfileFirst);
        addImageFirst = findViewById(R.id.addImageFirst);
        empNameFirst = findViewById(R.id.empNameFirst);
        empNoFirst = findViewById(R.id.empNoFirst);
        empEmailFirst = findViewById(R.id.empEmailFirst);
        CompNameee = findViewById(R.id.CompNameee);
        CompEmail = findViewById(R.id.CompEmail);
        CompPhone = findViewById(R.id.CompPhone);
        CompAddress = findViewById(R.id.CompAddress);
        checkboxEmployerrrr = findViewById(R.id.checkboxEmployerrrr);
        ProfileFirstSubButton = findViewById(R.id.ProfileFirstSubButton);
        termConditionEmployerr = findViewById(R.id.termConditionEmployerr);
    }

    private void getProfileDetailsApi() {

        final ProgressDialog pd = new ProgressDialog(ProfileEmployerFirstActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<GetProfileDetailsModel> call = service.GET_PROFILE_DETAILS_MODEL_CALL("employer_details?user_id="+strUserId);

        call.enqueue(new Callback<GetProfileDetailsModel>() {
            @Override
            public void onResponse(Call<GetProfileDetailsModel> call, Response<GetProfileDetailsModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        //String message = response.body().getMessage();
                        String success = (response.body().getSuccess());
                        String message = (response.body().getMessage());
                        Log.e("hello", "success: " + success);

                        if (success.equals("True") || success.equals("true")) {
                            GetProfileDetailsModel getProfileDetailsModel = response.body();
                            getProfileDetailsData = getProfileDetailsModel.getData();

                            Log.e("hello", "getData: ");
                            // Id  = profileGetData.getId();
                            strFirstname = getProfileDetailsData.getFirstName();
                            strEmail = getProfileDetailsData.getEmail();
                            strPhone = getProfileDetailsData.getMobile();

                            empNameFirst.setText(strFirstname);
                            empEmailFirst.setText(strEmail);
                            empNoFirst.setText(strPhone);
                            Glide.with(getApplicationContext()).load(Api_Client.BASE_URL_IMAGES1 + getProfileDetailsData.getImage()).placeholder(R.drawable.ic_launcher_foreground).into(userProfileFirst);
                            //    Toast.makeText(ProfileEmployerFirstActivity.this, message, Toast.LENGTH_SHORT).show();


                            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(getApplicationContext(), "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(getApplicationContext(), "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(getApplicationContext(), "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(getApplicationContext(), "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(getApplicationContext(), "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(getApplicationContext(), "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(getApplicationContext(), "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetProfileDetailsModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

    private void addProfileEmployerApi() {

        final ProgressDialog pd = new ProgressDialog(ProfileEmployerFirstActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();

        MultipartBody.Part profile = null;

        try {
            if (profile_fileImage == null) {
                profile = MultipartBody.Part.createFormData("image", "", RequestBody.create(MediaType.parse("image/*"), ""));
                Log.e("conversionException", "error1");

            } else {
                profile = MultipartBody.Part.createFormData("image", profile_fileImage.getName(), RequestBody.create(MediaType.parse("image/*"), profile_fileImage));
                Log.e("conversionException", "error2");
            }

        } catch (Exception e) {
            Log.e("conversionException", "error" + e.getMessage());
        }
        RequestBody UserId = RequestBody.create(MediaType.parse("string/plain"), strUserId);
        RequestBody CompanyName = RequestBody.create(MediaType.parse("text/plain"), StringName);
        RequestBody CompanyEmail = RequestBody.create(MediaType.parse("text/plain"), StringEmail);
        RequestBody CompanyPhone = RequestBody.create(MediaType.parse("text/plain"), StringPhone);
        RequestBody CompanyAddress = RequestBody.create(MediaType.parse("text/plain"), StringAddress);
        RequestBody TermCondition = RequestBody.create(MediaType.parse("string/plain"), strCheck);


        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<EmployerProfileModel> call = service.EMPLOYER_PROFILE_MODEL_CALL(UserId,profile,CompanyEmail, CompanyName,  CompanyAddress,CompanyPhone, TermCondition);

        Log.e("check", "UserId" + UserId);
        Log.e("check", "profileImage" + profile);
        Log.e("check", "CompanyName" + CompanyName);
        Log.e("check", "CompanyEmail" + CompanyEmail);
        Log.e("check", "CompanyAddress" + CompanyAddress);
        Log.e("check", "TermCondition" + TermCondition);

        call.enqueue(new Callback<EmployerProfileModel>() {
            @Override
            public void onResponse(Call<EmployerProfileModel> call, Response<EmployerProfileModel> response) {
                pd.dismiss();

                try {
                    if (response.isSuccessful()) {
                        String success = response.body().getSuccess();
                        String msg = response.body().getMessage();

                        if (success.equals("True") || success.equals("true")) {
                            Log.e("myprofile", "success " + success);
                            Toast.makeText(ProfileEmployerFirstActivity.this, msg, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ProfileEmployerFirstActivity.this, DashboardActivity.class);
                            startActivity(intent);


                        } else {
                            pd.dismiss();
                            Toast.makeText(ProfileEmployerFirstActivity.this, msg, Toast.LENGTH_LONG).show();

                        }

                    } else {

                        try {

                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(ProfileEmployerFirstActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } catch (Exception e) {
                        }
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (JsonSyntaxException exception) {
                    exception.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<EmployerProfileModel> call, Throwable t) {

                if (t instanceof IOException) {

                    Toast.makeText(ProfileEmployerFirstActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                } else {

                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(ProfileEmployerFirstActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validation() {

        if (userProfileFirst.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "Please update your profile", Toast.LENGTH_SHORT).show();
            return false;
        } else if (empNameFirst.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (empNoFirst.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your phone no ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (empEmailFirst.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (CompNameee.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your company name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (CompEmail.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your company email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (CompPhone.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (CompAddress.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter your company address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        }
    }
    // Handle permission request results

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                // Get the captured image as a Bitmap
                Bitmap capturedImage = (Bitmap) extras.get("data");
                Log.e("camera", "cam " + capturedImage);

                // Display the captured image in the ImageView
                userProfileFirst.setImageBitmap(capturedImage);

                // Save the captured image as a file
                profile_fileImage = saveBitmapToFile(capturedImage);
            }
        }
        else if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Gallery selection successful, get the selected image URI

            Uri selectedImageUri = data.getData();
            Log.e("camera", "gall " + selectedImageUri);
            userProfileFirst.setImageURI(selectedImageUri);

            // Get the file path from the URI
            String selectedImagePath = getPath(selectedImageUri);
            if (selectedImagePath != null) {
                profile_fileImage = new File(selectedImagePath);
            } else {
                Log.e("ImagePath", "Selected image path is null"+profile_fileImage);
            }

            // Load the image using Glide
            Glide.with(ProfileEmployerFirstActivity.this)
                    .load(selectedImageUri) // Load the Uri directly
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(userProfileFirst);
        }
    }
    public String getPath(Uri uri) {
        String filePath = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_FILE)) {
            filePath = uri.getPath();
        } else if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    filePath = cursor.getString(index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    private File saveBitmapToFile(Bitmap bitmap) {
        File imageFile = null;
        try {
            // Create a new file to save the image
            imageFile = new File(getExternalFilesDir(null), "captured_image.jpg");

            // Convert the Bitmap to a JPEG and save it to the file
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    private void requestPermission() {
        // Request permission to read external storage
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                GALLERY_REQUEST_CODE);
    }

}
