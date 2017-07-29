package com.ardic.android.iotignite.greenhouse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.R;
import com.ardic.android.iotignite.greenhouse.controllers.SignUpController;
import com.ardic.android.iotignite.greenhouse.utils.ValidationResult;
import com.ardic.android.iotignite.greenhouse.utils.ValidationUtils;
import com.ardic.android.iotignite.lib.restclient.model.CreateRestrictedUser;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SignUpActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener, View.OnClickListener {

    private String TAG = SignUpActivity.class.getSimpleName();
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextMail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    private ImageView imgMailValidate;
    private ToggleButton tbShowHidePassword;
    private ToggleButton tbShowHideConfirmPassword;
    private CheckBox cbAcceptTermsOfUse;
    private Button btnSignUp;
    private Toolbar toolbar;
    private AVLoadingIndicatorView avi;

    private SignUpController mSignUpController;

    // sign up credentials

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    private CreateRestrictedUser mCreatedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {

        setContentView(R.layout.activity_sign_up);
        toolbar = (Toolbar) findViewById(R.id.activity_sign_up_tool_bar);
        editTextFirstName = (EditText) findViewById(R.id.activity_sign_up_edt_first_name);
        editTextLastName = (EditText) findViewById(R.id.activity_sign_up_edt_last_name);
        editTextMail = (EditText) findViewById(R.id.activity_sign_up_edt_email);
        editTextPassword = (EditText) findViewById(R.id.activity_sign_up_edt_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.activity_sign_up_edt_confirm_password);
        imgMailValidate = (ImageView) findViewById(R.id.activity_sign_up_img_mail_validation);
        tbShowHidePassword = (ToggleButton) findViewById(R.id.activity_sign_up_tb_show_hide_password);
        tbShowHideConfirmPassword = (ToggleButton) findViewById(R.id.activity_sign_up_tb_show_hide_confirm_password);
        cbAcceptTermsOfUse = (CheckBox) findViewById(R.id.activity_sign_up_cb_accept_terms_of_use);
        btnSignUp = (Button) findViewById(R.id.activity_sign_up_btn_sign_up);


        cbAcceptTermsOfUse.setMovementMethod(LinkMovementMethod.getInstance());

        imgMailValidate.setVisibility(View.INVISIBLE);

        //Set image 'tick' or 'cancel' according to validation of mail input
        editTextMail.setOnFocusChangeListener(this);

        //Show or hide 'password' characters.
        tbShowHidePassword.setOnCheckedChangeListener(this);

        //Show or hide 'confirm password' characters
        tbShowHideConfirmPassword.setOnCheckedChangeListener(this);

        //Click on SIGN UP button, check the inputs
        btnSignUp.setOnClickListener(this);

        setSupportActionBar(toolbar);

    }

    /**
     * @param menu adds items to the action bar if it is present
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_main_about) {
            Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_main_buy_device) {
            Toast.makeText(this, "Buying Device Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_main_faq) {
            Toast.makeText(this, "FAQ Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        if (view.equals(editTextMail)) {
            email = editTextMail.getText().toString().trim();
            if (!hasFocus) {
                imgMailValidate.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(email) && email.matches(Constants.PATTERN_EMAIL)) {
                    imgMailValidate.setImageResource(R.drawable.tick);
                } else {
                    imgMailValidate.setVisibility(View.VISIBLE);
                    imgMailValidate.setImageResource(R.drawable.cancel);
                }
            }
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.equals(tbShowHideConfirmPassword)) {
            if (isChecked) {
                editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());

        } else if (compoundButton.equals(tbShowHidePassword)) {
            if (isChecked) {
                editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            editTextPassword.setSelection(editTextPassword.getText().length());
        }
    }


    @Override
    public void onClick(View view) {

        if (view.equals(btnSignUp) && assignEditTextValues()) {

            ValidationResult result = ValidationUtils.checkSignUpCredentials(email,
                    firstName, lastName, password, confirmPassword, cbAcceptTermsOfUse.isChecked());
            if (ValidationResult.OK == result) {

                if (createAccount()) {
                    startLoginActivity();
                } else {
                    Toast.makeText(SignUpActivity.this, "An error occured please try again.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(SignUpActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    private boolean assignEditTextValues() {


        if (editTextFirstName != null && !TextUtils.isEmpty(editTextFirstName.getText().toString())) {
            firstName = editTextFirstName.getText().toString().trim();
        } else {
            Toast.makeText(SignUpActivity.this, "First name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextLastName != null && !TextUtils.isEmpty(editTextLastName.getText().toString())) {
            lastName = editTextLastName.getText().toString().trim();
        } else {
            Toast.makeText(SignUpActivity.this, "Last name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextMail != null && !TextUtils.isEmpty(editTextMail.getText().toString())) {
            email = editTextMail.getText().toString().trim();
        } else {
            Toast.makeText(SignUpActivity.this, "Mail cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextPassword != null && !TextUtils.isEmpty(editTextPassword.getText().toString())) {
            password = editTextPassword.getText().toString();
        } else {
            Toast.makeText(SignUpActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextConfirmPassword != null && !TextUtils.isEmpty(editTextConfirmPassword.getText().toString())) {
            confirmPassword = editTextConfirmPassword.getText().toString();
        } else {
            Toast.makeText(SignUpActivity.this, "Confirm password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean createAccount() {

        boolean result = false;
        mSignUpController = new SignUpController(SignUpActivity.this, firstName, lastName, email, password);

        mSignUpController.execute();

        // TODO : Creating account LOADING... start
        try {
            mCreatedUser = mSignUpController.get(Constants.ASYNC_GET_TIMEOUT, TimeUnit.MILLISECONDS);
            if (mCreatedUser != null) {
                result = true;
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "createAccount(): " + e);
            return result;
        } catch (ExecutionException e) {
            Log.e(TAG, "createAccount(): " + e);
            return result;
        } catch (TimeoutException e) {
            Log.e(TAG, "createAccount(): " + e);
            return result;
        }
        // TODO : Creating account LOADING end
        return result;
    }

    private void startLoginActivity() {
        avi = (AVLoadingIndicatorView) findViewById(R.id.avisign);
        avi.show();
        Toast.makeText(SignUpActivity.this, "Sign Up Successful !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Constants.Actions.ACTION_SIGN_UP_SUCCESS);
        intent.putExtra(Constants.Extra.EXTRA_USERNAME, email);
        intent.putExtra(Constants.Extra.EXTRA_PASSWORD, password);
        setResult(RESULT_OK, intent);
        avi.hide();
        finish();
    }


}
