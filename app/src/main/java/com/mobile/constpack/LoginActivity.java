package com.mobile.constpack;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Toast;

import com.mobile.constpack.helpers.StringUtils;
import com.mobile.constpack.network.BaseCallback;
import com.mobile.constpack.network.RestManager;
import com.mobile.constpack.network.request.LoginRequest;
import com.mobile.constpack.network.response.LoginResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById(R.id.txtinp_user_name)
    TextInputLayout txt_inp_username;

    @ViewById(R.id.txtinp_pass)
    TextInputLayout txt_inp_pass;

    @ViewById(R.id.appedt_user_name)
    AppCompatEditText appet_username;

    @ViewById(R.id.appedt_pass)
    AppCompatEditText appet_pass;

    @AfterViews
    void initViews(){

    }

    @Click(R.id.appbtn_login)
    void login(){
        if(!isValid()) return;

        RestManager.getInstance().requestLogin(new LoginRequest(appet_username.getText().toString(),appet_pass.getText().toString())).enqueue(new BaseCallback<LoginResponse>(this) {
            @Override
            public void onSuccess(LoginResponse response) {
                Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int errorId,String error) {
                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isValid(){
        boolean valid = true;
        if(StringUtils.isEmpty(appet_username.getText().toString())){
            valid =false;
            txt_inp_username.setErrorEnabled(true);
            txt_inp_username.setError(getString(R.string.txt_err_empty_username));
        }

        if(StringUtils.isEmpty(appet_pass.getText().toString())){
            valid = false;
            txt_inp_pass.setErrorEnabled(true);
            txt_inp_pass.setError(getString(R.string.txt_err_empty_pass));
        }

        return valid;
    }

    @TextChange(R.id.appedt_user_name)
    void onUserNameTextChanged(){
        txt_inp_username.setError(null);
        txt_inp_username.setErrorEnabled(true);
    }

    @TextChange(R.id.appedt_pass)
    void onPassTextChanged(){
        txt_inp_pass.setError(null);
        txt_inp_pass.setErrorEnabled(true);
    }
}
