package com.esp.bookmarket.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.esp.bookmarket.R;
import com.esp.bookmarket.data.Auth;
import com.esp.bookmarket.dialog.Alert;
import com.esp.bookmarket.model.Profile;
import com.esp.bookmarket.model.User;
import com.esp.bookmarket.network.API;
import com.esp.bookmarket.network.model.EditProfileRequest;
import com.esp.bookmarket.network.model.EditProfileResponse;
import com.esp.bookmarket.network.model.GetProfileResponse;
import com.esp.bookmarket.network.model.ProfileData;
import com.esp.bookmarket.network.service.EditProfileService;
import com.esp.bookmarket.network.service.GetProfileService;
import com.esp.bookmarket.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private CircleImageView circleImageView;
    private EditText usernameView;
    private EditText addressView;
    private EditText phoneView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initViews();
        fetchData();
    }

    private void initViews() {
        circleImageView = findViewById(R.id.avatar);
        usernameView = findViewById(R.id.ep_username);
        addressView = findViewById(R.id.ep_address);
        phoneView = findViewById(R.id.ep_phone);

        progressDialog = new ProgressDialog(this);
    }

    private void fetchData() {
        progressDialog.show();
        String uid = Auth.getInstance(this).getUserId();
        API.createService(GetProfileService.class).getProfile(uid)
                .enqueue(new Callback<GetProfileResponse>() {
                    @Override
                    public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                        progressDialog.cancel();
                        GetProfileResponse body = response.body();
                        if (body == null) {
                            return;
                        }
                        ProfileData data = body.data;
                        if (data == null) {
                            return;
                        }
                        User.init(uid, data.profile);
                        fillData(data.profile);
                    }

                    @Override
                    public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                        progressDialog.cancel();
                    }
                });
    }

    private void fillData(Profile profile) {
        usernameView.setText(profile.username);
        addressView.setText(profile.address);
        phoneView.setText(profile.phone);
    }

    public void submit(View view) {
        String username = usernameView.getText().toString();
        String address = addressView.getText().toString();
        String phone = phoneView.getText().toString();
        boolean isValidUsername = AppUtils.checkValidString(username);
        boolean isValidAddress = AppUtils.checkValidString(address);
        boolean isValidPhone = AppUtils.checkValidString(phone);
        if (!isValidUsername) {
            return;
        } else if (!isValidAddress) {
            return;
        } else if (!isValidPhone) {
            return;
        }
        Profile profile = new Profile();
        profile.phone = phone;
        profile.address = address;
        profile.username = username;
//        profile.avatar =
        EditProfileRequest request = new EditProfileRequest();
        request.accessToken = Auth.getInstance(this).getAccessToken();
        request.profile = profile;
        API.createService(EditProfileService.class)
                .editProfile(request)
                .enqueue(new Callback<EditProfileResponse>() {
                    @Override
                    public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                        EditProfileResponse body = response.body();
                        if (body == null || body.code != 1000) {
                            Alert.alert(EditProfileActivity.this, "Edit failed");
                            return;
                        }
                        finish();
                    }

                    @Override
                    public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                        Alert.alert(EditProfileActivity.this, "Edit failed");
                    }
                });
    }


}