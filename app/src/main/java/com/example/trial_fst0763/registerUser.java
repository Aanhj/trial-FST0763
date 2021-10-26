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
        registerr=findViewById(R.id.regText);
        profilepic = findViewById(R.id.profile_image);
        DBHelper helper = new DBHelper(this);
        regUser = findViewById(R.id.regButton);
        regUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strphone = phone.getText().toString();


                if (checkmail() & checkpass() & checkphone(strphone) & names()) {
                    Toast.makeText(getApplicationContext(), "all succeeded", Toast.LENGTH_SHORT).show();

                }
                Boolean checkUser=helper.checkUser(struname,strphone);
                    if (!checkUser){
                        try {


                        Boolean insert=helper.regInsertUser(strfname,strlname,struname,strpass,strmail,strphone,img);
                            Log.i("data",strfname+strlname+struname+strmail+strpass+strphone);
                        if (insert){
                            Log.i("data",strfname+strlname+struname+strmail+strpass+strphone);
                            Toast.makeText(registerUser.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(registerUser.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                        }catch (Exception e)
                        {
                            Log.i("db error",e.getLocalizedMessage());
                        }

                    }else {
                        Boolean chkusername=helper.checkUname(struname);
                        Boolean chkuserphone=helper.checkUphone(strphone);
                        if (!chkusername || !chkuserphone){
                            Toast.makeText(registerUser.this, "User exist, please try logging in", Toast.LENGTH_SHORT).show();
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
        String filename=getDrawable(R.drawable.def_profle).toString();
        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                 SelectedImage = data.getData();
                if (null != SelectedImage) {

                    profilepic.setImageURI(SelectedImage);

                    try {

                        Bitmap bmp=MediaStore.Images.Media.getBitmap(this.getContentResolver(),SelectedImage);
                        convertToByte ctb=new convertToByte();
                        img= ctb.getBytes(bmp);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//
//

                }
                else {
                    profilepic.setImageBitmap(BitmapFactory.decodeFile(filename));
                    }
                }
            }
        }


    public void tologin(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public boolean checkmail() {
        strmail = email.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(strmail).matches()) {
            email.setError(null);
            return true;
        } else {
            email.setError("enter email id");
            return false;
        }
    }

    public boolean checkpass() {
        strpass = pass.getText().toString();
        if (strpass.length() >= 8 && strpass!=null) {
            pass.setError(null);
            return true;
        } else {
            pass.setError("password is too short");
            return false;
        }
    }

    public boolean names() {
        struname = uname.getText().toString();
        strfname = fname.getText().toString();
        strlname = lname.getText().toString();

        if (struname.isEmpty() | struname.length() < 2) {
            uname.setError("username should be longer than 2 chars");

        } else {
            uname.setError(null);
            return false;
        }


        if (strfname.isEmpty() | strfname.length() < 2) {
            fname.setError("First name should be longer than 2 chars");
        } else {
            fname.setError(null);

            return false;
        }


        if (strlname.isEmpty() | strlname.length() < 2) {
            lname.setError("username should be longer than 2 chars");

        } else {
            lname.setError(null);
            return false;
        }


        return false;
    }

    public boolean checkphone(String checknumber) {

        Pattern pattern = Pattern.compile("^[+]?[0-9]{10,13}$");
        Matcher match = pattern.matcher(checknumber);

        if (match.find() && match.group().equals(strphone) && strphone !=null) {
            phone.setError(null);
            return true;
        } else
            return false;
    }


}





