package com.example.trial_fst0763;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fst_t0763.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class registerUser extends AppCompatActivity {
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private static int SELECT_PICTURE = 1;
    TextInputEditText fname, lname, uname, email, pass, phone;
    Button regUser;
    String strmail, strpass, struname, strfname, strlname, strphone;
    CircleImageView profilepic;
    Uri SelectedImage;
    TextView registerr;
    byte[] img;
    String trial_for_validation = "!@#$%^&*()";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        fname = findViewById(R.id.regFirstName);
        lname = findViewById(R.id.regLastName);
        uname = findViewById(R.id.regUserName);
        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        phone = findViewById(R.id.regPhone);
        registerr = findViewById(R.id.regText);
        profilepic = findViewById(R.id.profile_image);
        DBHelper helper = new DBHelper(this);
        regUser = findViewById(R.id.regButton);
        regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!checkmail() | !checkpass() | !checkphone() | !uname() | !lname() | !fname()) {
                    Toast.makeText(registerUser.this, "check the fields again", Toast.LENGTH_SHORT).show();

                } else {


                    Boolean checkUname = helper.checkUname(struname);
                    Boolean checkUphone = helper.checkUphone(strphone);

                    if (checkUname & checkUphone) {
                        Toast.makeText(registerUser.this, "user exist, please login", Toast.LENGTH_SHORT).show();
                        return;
                    } else {

                        Boolean insert = helper.regInsertUser(strfname, strlname, struname, strpass, strmail, strphone, img);
                        if (insert) {
                            try {


                                Log.i("data", strfname + strlname + struname + strmail + strpass + strphone);
                                if (insert) {
                                    Log.i("data", strfname + strlname + struname + strmail + strpass + strphone);
                                    Toast.makeText(registerUser.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(registerUser.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(registerUser.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Log.i("db error", e.getLocalizedMessage());
                            }
                        } else {
                            Toast.makeText(registerUser.this, "Inserting data failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

        });

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(registerUser.this, new String[]
                                {
                                        Manifest.permission.READ_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_PERMISSION_CODE);
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String filename = getDrawable(R.drawable.def_profle).toString();
        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                SelectedImage = data.getData();
                if (null != SelectedImage) {

                    profilepic.setImageURI(SelectedImage);

                    try {

                        Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), SelectedImage);
                        convertToByte ctb = new convertToByte();
                        img = ctb.getBytes(bmp);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//
//

                } else {
                    profilepic.setImageBitmap(BitmapFactory.decodeFile(filename));
                }
            }
        }
    }


    public void tologin(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean checkmail() {
        strmail = email.getText().toString().replaceAll("//s", "");
        email.setText(strmail);
        if (Patterns.EMAIL_ADDRESS.matcher(strmail).matches() & strmail != null) {
            email.setError(null);
            return true;
        } else {
            email.setError("Invalid mail id");
            email.requestFocus();

            return false;
        }
    }

    public boolean checkpass() {
        strpass = pass.getText().toString().replaceAll("//s", "").trim();
        pass.setText(strpass);
        if (strpass.length() >= 8 & strpass != null) {
            pass.setError(null);

            return true;
        } else {
            pass.setError("password is too short");
            pass.requestFocus();
            return false;
        }
    }

    public boolean uname() {
        struname = uname.getText().toString().replaceAll(trial_for_validation, "").trim();
        uname.setText(struname);
        if (!struname.isEmpty() | struname.length() > 2 & struname.length() < 10) {
            uname.setError(null);
            return true;
        } else {
            uname.setError("username should be longer than 2 chars");
            pass.requestFocus();
            return false;
        }
    }

    public boolean fname() {


        strfname = fname.getText().toString().replaceAll(trial_for_validation, "").trim();
        fname.setText(strfname);
        if (!strfname.isEmpty() | strfname.length() > 2 & strfname.length() < 10) {
            fname.setError(null);
            return true;
        } else {
            fname.setError("First name should be longer than 2 chars");
            fname.requestFocus();
            return false;
        }
    }

    public boolean lname() {
        strlname = lname.getText().toString().replaceAll(trial_for_validation, "").trim();
        lname.setText(strlname);
        if (!strlname.isEmpty() | strlname.length() > 2 & strlname.length() < 10) {
            lname.setError(null);
            return true;
        } else {
            lname.setError("username should be longer than 2 chars");
            lname.requestFocus();

            return false;
        }
    }


    public boolean checkphone() {
        strphone = phone.getText().toString();
        Pattern pattern = Pattern.compile("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$");
        Matcher match = pattern.matcher(strphone);

        if (match.find() && match.group().equals(strphone) && strphone != null) {
            phone.setError(null);
            return true;
        } else {
            phone.setError("please check phone number");
            pass.requestFocus();
            return false;
        }
    }


}





