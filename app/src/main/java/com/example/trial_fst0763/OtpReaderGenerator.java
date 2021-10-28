package com.example.trial_fst0763;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fst_t0763.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class OtpReaderGenerator extends Fragment {
    private static final int NotificationID = 100;
    private static final String ChannleName = "FST_Notification_channel";
    TextInputEditText otpTextBox;
    Button generate_otp, verify_otp;
    String generatedOtp, checkString, newgeneratedOTP, timestamp, test;
    Random random = new Random();

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_otp_reader_generator, container, false);
        otpTextBox = v.findViewById(R.id.txtBoxOtpReader);
        generate_otp = v.findViewById(R.id.generate_otp);
        verify_otp = v.findViewById(R.id.verify_otp);


        generate_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenerateOtp();
                createNotification();
                checkString = newgeneratedOTP;
                Log.i("notification", checkString);

                otpTextBox.setText(checkString);


            }
        });

        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp();

            }
        });


        return v;
    }

    public void verifyOtp() {
        test = otpTextBox.getText().toString();
        if (test.isEmpty() && newgeneratedOTP==null) {
            Toast.makeText(getContext(), "No OTP was generated", Toast.LENGTH_SHORT).show();
        } else if (newgeneratedOTP.equals(test)) {
            Toast.makeText(getContext(), "OTP matched", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
        }

    }


    public void createNotification() {
        newgeneratedOTP = generatedOtp.trim();
        timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), ChannleName)
                .setSmallIcon(R.drawable.otp_icon).setContentTitle("FST_OTP")
                .setContentText("Something that wasnt visible")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Your otp is" + newgeneratedOTP + " on time " + timestamp)
                ).setPriority(NotificationCompat.PRIORITY_DEFAULT);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = generatedOtp;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(ChannleName, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

          */
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.
                from(getActivity()  );

        notificationManagerCompat.notify(NotificationID, builder.build());


    }


    private void GenerateOtp() {
        generatedOtp = String.valueOf(random.nextInt(1000000));
        if (generatedOtp.length() < 6) {
            generatedOtp = String.valueOf(random.nextInt(1000000));
        }


    }


}
