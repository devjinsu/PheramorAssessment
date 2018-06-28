package com.jinsukim.pheramor;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jinsukim.pheramor.model.UserRegInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.jinsukim.pheramor.SelectProfileActivity.PROFILE;

public class ProfileActivity extends Activity {
    public static final String EXTRA_USER_INFO = "EXTRA_USER_INFO";
    private UserRegInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(getIntent() != null){
            if(getIntent().getParcelableExtra(EXTRA_USER_INFO) != null){
                mUserInfo = getIntent().getParcelableExtra(EXTRA_USER_INFO);
            }
        }
        initUI();
    }

    private void initUI(){

        ((ImageView) findViewById(R.id.profile)).setImageBitmap(PROFILE);
        ((TextView) findViewById(R.id.email)).setText(mUserInfo.getEmail());
        ((TextView) findViewById(R.id.name)).setText(mUserInfo.getLastName() + ", " +mUserInfo.getFirstName());
        ((TextView) findViewById(R.id.zipcode)).setText(mUserInfo.getZipcode());
        ((TextView) findViewById(R.id.height)).setText(mUserInfo.getHeight()+"cm");
        ((TextView) findViewById(R.id.gender)).setText(mUserInfo.isMan() == true? "Man" : "Woman");
        String interest = "";
        if(mUserInfo.getInterest() == 3){
            interest = "Men and Women";
        } else if(mUserInfo.getInterest() == 1){
            interest = "Men";
        } else if(mUserInfo.getInterest() == 2){
            interest = "Women";
        }
        ((TextView) findViewById(R.id.interestin)).setText(interest);
        ((TextView) findViewById(R.id.dob)).setText(mUserInfo.getDOB());
        ((TextView) findViewById(R.id.age)).setText(mUserInfo.getAgeMin() + "-" + mUserInfo.getAgeMax());
        ((TextView) findViewById(R.id.race)).setText(mUserInfo.getRace().length() < 1 ? "No specify": mUserInfo.getRace());
        ((TextView) findViewById(R.id.religion)).setText(mUserInfo.getReligion().length() < 1 ? "No specify": mUserInfo.getReligion());

        ((Button) findViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("firstname", mUserInfo.getFirstName());
                    postData.put("lastname", mUserInfo.getLastName());
                    postData.put("password", mUserInfo.getPassword());
                    postData.put("email", mUserInfo.getEmail());
                    postData.put("zipcode", mUserInfo.getZipcode());
                    postData.put("height", mUserInfo.getHeight());
                    postData.put("gender", mUserInfo.isMan());
                    postData.put("dob", mUserInfo.getDOB());
                    postData.put("interestin", mUserInfo.getInterest());
                    postData.put("minAge", mUserInfo.getAgeMin());
                    postData.put("maxAge", mUserInfo.getAgeMax());
                    postData.put("race", mUserInfo.getRace());
                    postData.put("religion", mUserInfo.getReligion());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    PROFILE.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    postData.put("profile", stream.toByteArray());
                    postData.put("profileHeight", PROFILE.getHeight());
                    postData.put("profileWidth", PROFILE.getWidth());

                    SendDeviceDetails task = new SendDeviceDetails();
                    task.execute("https://external.dev.pheramor.com/", postData.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class SendDeviceDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String data = "";

            HttpURLConnection httpURLConnection = null;
            try {

                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes("PostData=" + params[1]);
                wr.flush();
                wr.close();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("OK")){
                Toast.makeText(ProfileActivity.this, "Success!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
