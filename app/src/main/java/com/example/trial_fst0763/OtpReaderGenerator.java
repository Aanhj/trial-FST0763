package com.example.trial_fst0763;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fst_t0763.R;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.material.textfield.TextInputEditText;

import java.net.PasswordAuthentication;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;


public class OtpReaderGenerator extends Fragment {
    private static final int NotificationID = 100;
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadCastReceiver smsBroadCastReceiver;

    private static final String ChannleName = "FST_Notification_channel";

    TextInputEditText otpTextBox;
    Button generate_otp, verify_otp;
    String generatedOtp, checkString, newgeneratedOTP, timestamp, test, phone;
    Random random = new Random();
    /* SmsBroadCastReceiver smsBroadcastReceiver;*/


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_otp_reader_generator, container, false);
        otpTextBox = v.findViewById(R.id.txtBoxOtpReader);
        generate_otp = v.findViewById(R.id.generate_otp);
        verify_otp = v.findViewById(R.id.verify_otp);
        smartUserConsent();


        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;


    }




    public void verifyOtp() {
        test = otpTextBox.getText().toString();
        if (test.isEmpty() && newgeneratedOTP == null) {
            Toast.makeText(getContext(), "No OTP was generated", Toast.LENGTH_SHORT).show();
        } else if (newgeneratedOTP.equals(test)) {
            Toast.makeText(getContext(), "OTP matched", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void smartUserConsent() {
        SmsRetrieverClient client= SmsRetriever.getClient(getContext());
        client.startSmsUserConsent(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode==REQ_USER_CONSENT){
            if ((resultCode==RESULT_OK) && (data !=null)){
                String message=data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                getOTP(message);
            }
         }

    }

    private void getOTP(String message) {
        Pattern patternOfOtp= Pattern.compile("(|^)\\d{6}");
        Matcher matcher = patternOfOtp.matcher(message);
        if (matcher.find()){
            otpTextBox.setText(matcher.group(0));
        }
    }

   /* private void GenerateOtp() {
        generatedOtp = String.valueOf(random.nextInt(1000000));

        if (generatedOtp.length() < 6) {
            generatedOtp = String.valueOf(random.nextInt(1000000));
        }
    }
*/
    private void registerBroadcast(){
        smsBroadCastReceiver = new SmsBroadCastReceiver();
        smsBroadCastReceiver.smsBroadcastinterface= new SmsBroadCastReceiver.SmsBroadcastinterface() {
            @Override
            public void onSuccess(Intent intent) {
            startActivityForResult(intent,REQ_USER_CONSENT);
            }

            @Override
            public void onFailure() {

            }

        };

        IntentFilter intentFilter=new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        getContext().registerReceiver(smsBroadCastReceiver,intentFilter);

    }

    @Override
    public void onStart() {
        super.onStart();
        registerBroadcast();
    }

    @Override
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(smsBroadCastReceiver);
    }
}
