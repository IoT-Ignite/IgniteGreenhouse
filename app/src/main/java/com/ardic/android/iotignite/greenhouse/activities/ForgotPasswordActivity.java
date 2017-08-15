package com.ardic.android.iotignite.greenhouse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ardic.android.iotignite.greenhouse.Constants;
import com.ardic.android.iotignite.greenhouse.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {

    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();
    public Toolbar toolbar;
    private String email;
    private EditText edtMail;
    private ImageView imgMailValidate;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initUI();
    }

    private void initUI() {

        toolbar = (Toolbar) findViewById(R.id.activity_forgot_password_tool_bar);
        edtMail = (EditText) findViewById(R.id.activity_forgot_password_edt_email);
        imgMailValidate = (ImageView) findViewById(R.id.activity_forgot_password_img_mail_validation);
        btnSubmit = (Button) findViewById(R.id.activity_forgot_password_btn_submit);

        setSupportActionBar(toolbar);

        imgMailValidate.setVisibility(View.INVISIBLE);

        //Set image 'tick' or 'cancel' according to validation of mail input.
        edtMail.setOnFocusChangeListener(this);

        //Control if mail address registered before
        btnSubmit.setOnClickListener(this);

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

        if (view.equals(edtMail)) {
            email = edtMail.getText().toString().trim();
            if (!hasFocus) {
                imgMailValidate.setVisibility(View.VISIBLE);
                if (email.matches(Constants.PATTERN_EMAIL) && email.length() > 0) {
                    imgMailValidate.setImageResource(R.drawable.tick);
                } else {
                    imgMailValidate.setVisibility(View.VISIBLE);
                    imgMailValidate.setImageResource(R.drawable.cancel);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

        if (view.equals(btnSubmit)) {
            email = edtMail.getText().toString().trim();

            if (!TextUtils.isEmpty(email) && email.matches(Constants.PATTERN_EMAIL)) {

                //if (is email address registered before){
                if (true) {
                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this,
                            "User " + email + " does not exist!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ForgotPasswordActivity.this,
                        "Enter a valid email address!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
