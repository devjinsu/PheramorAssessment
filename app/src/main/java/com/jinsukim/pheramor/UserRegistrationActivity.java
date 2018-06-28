package com.jinsukim.pheramor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.jinsukim.pheramor.model.UserRegInfo;
import com.jinsukim.pheramor.utils.Validator;

import java.util.ArrayList;
import java.util.List;

import static com.jinsukim.pheramor.SelectProfileActivity.EXTRA_USER_INFO;

public class UserRegistrationActivity extends Activity
        implements DatePickerDialog.OnDateSetListener{
    private int mStage = 0;
    private List<LinearLayout> mLayoutList;
    private UserRegInfo mUserInfo;
    private boolean mUserPickDOB;
    DatePickerDialog mDatePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        initData();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        if(mStage != 0){
            changeStage(false);
        }else {
            super.onBackPressed();
        }
    }

    private void initData(){
        mUserInfo = new UserRegInfo();
        mLayoutList = new ArrayList<>();
        mUserPickDOB = false;
        mDatePickerDialog = new DatePickerDialog(
                UserRegistrationActivity.this, UserRegistrationActivity.this, 1999, 1, 1);

    }

    private void initUI(){
        ((Button) findViewById(R.id.btn_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStage(true);
            }
        });

        ((Button) findViewById(R.id.btn_dob))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatePickerDialog.show();
                    }
                });

        mLayoutList.add((LinearLayout) findViewById(R.id.ll_email));
        mLayoutList.add((LinearLayout) findViewById(R.id.ll_password));
        mLayoutList.add((LinearLayout) findViewById(R.id.ll_name));
        mLayoutList.add((LinearLayout) findViewById(R.id.ll_gender));
        mLayoutList.add((LinearLayout) findViewById(R.id.ll_interest));
        mLayoutList.add((LinearLayout) findViewById(R.id.ll_race));

        Spinner raceSpinner = (Spinner) findViewById(R.id.race);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this,
                R.array.race_arrays, android.R.layout.simple_spinner_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                mUserInfo.setRace(parent.getItemAtPosition(pos).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner religionSpinner = (Spinner) findViewById(R.id.religion);
        ArrayAdapter<CharSequence> religionAdapter = ArrayAdapter.createFromResource(this,
                R.array.religion_arrays, android.R.layout.simple_spinner_item);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religionSpinner.setAdapter(religionAdapter);
        religionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                mUserInfo.setReligion(parent.getItemAtPosition(pos).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void changeStage(boolean next){
        boolean valid = true;
        if (next) {
            switch (mStage) {
                case 0:
                    valid = Validator.isValidEmail(((EditText) findViewById(R.id.email)).getText());
                    mUserInfo.setEmail(((EditText) findViewById(R.id.email)).getText().toString());
                    break;
                case 1:
                    String password = ((EditText) findViewById(R.id.et_password)).getText().toString();
                    String passwordVali = ((EditText) findViewById(R.id.et_password_cofirm)).getText().toString();
                    valid = password.equals(passwordVali);
                    valid = passwordVali.length() > 3 && valid;
                    mUserInfo.setPassword(((EditText) findViewById(R.id.et_password)).getText().toString());
                    break;
                case 2:
                    valid = ((EditText) findViewById(R.id.et_first)).getText().length() > 1;
                    valid = ((EditText) findViewById(R.id.et_last)).getText().length() > 1 && valid;
                    valid = ((EditText) findViewById(R.id.et_zipcode)).getText().length() == 5 && valid;
                    valid = ((EditText) findViewById(R.id.et_height)).getText().length() > 2 && valid;
                    valid = ((EditText) findViewById(R.id.et_height)).getText().length() < 4 && valid;

                    if(valid){
                        mUserInfo.setFirstName(((EditText) findViewById(R.id.et_first)).getText().toString());
                        mUserInfo.setLastName(((EditText) findViewById(R.id.et_last)).getText().toString());
                        mUserInfo.setZipcode(((EditText) findViewById(R.id.et_zipcode)).getText().toString());
                        mUserInfo.setHeight(Integer.parseInt(((EditText) findViewById(R.id.et_height)).getText().toString()));

                    }
                    break;
                case 3:
                    valid = ((RadioButton)findViewById(R.id.man)).isChecked() || ((RadioButton)findViewById(R.id.woman)).isChecked();
                    valid = mUserPickDOB && valid;

                    mUserInfo.setMan(((RadioButton)findViewById(R.id.man)).isChecked());
                    mUserInfo.setDOB(((Button)findViewById(R.id.btn_dob)).getText().toString());
                    break;
                case 4:
                    int interest = 0;
                    if(((CheckBox) findViewById(R.id.men)).isChecked() && ((CheckBox) findViewById(R.id.women)).isChecked()){
                        interest = 3;
                        valid = true;
                    }else if(((CheckBox) findViewById(R.id.men)).isChecked()){
                        interest = 1;
                        valid = true;
                    }else if(((CheckBox) findViewById(R.id.women)).isChecked()){
                        interest = 2;
                        valid = true;
                    }
                    int min = 18;
                    int max = 60;
                    valid = ((EditText) findViewById(R.id.min_age)).getText().length() > 1 && valid;
                    valid = ((EditText) findViewById(R.id.max_age)).getText().length() > 1 && valid;
                    try {
                        min = Integer.parseInt(((EditText) findViewById(R.id.min_age)).getText().toString());
                        max = Integer.parseInt(((EditText) findViewById(R.id.max_age)).getText().toString());
                        if(min > max || min < 18){
                            valid = false;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    mUserInfo.setInterest(interest);
                    mUserInfo.setAgeMin(min);
                    mUserInfo.setAgeMax(max);
                    break;

            }
        }

        if (!valid){
            Toast.makeText(UserRegistrationActivity.this, "Validate your input!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mStage == mLayoutList.size()-1){
            Intent intent = new Intent(this, SelectProfileActivity.class);
            intent.putExtra(EXTRA_USER_INFO, mUserInfo);
            startActivity(intent);
            return;
        }

        if(next){
            viewChange(mLayoutList.get(mStage), mLayoutList.get(mStage+1));
            mStage++;
        }else{
            viewChange(mLayoutList.get(mStage), mLayoutList.get(mStage-1));
            mStage--;
        }
    }

    private void viewChange(final LinearLayout out, final LinearLayout in){
        out.animate()
                .translationY(out.getHeight())
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        out.setVisibility(View.GONE);
                    }
                });
        in.animate()
                .translationY(0)
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        in.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        ((Button) findViewById(R.id.btn_dob)).setText(monthOfYear+"/"+dayOfMonth+"/"+year);
        mUserPickDOB = true;
    }
}
