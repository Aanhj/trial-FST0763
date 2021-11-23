/*
package com.example.trial_fst0763;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.APIClient_Classes.AuthHeaderClient;
import com.example.Interface.API_Interface;
import com.example.ModelClasses.AuthApiResponseModel;
import com.example.fst_t0763.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class AuthHeaderAPI extends Fragment {

    Button AuthApiButton;
    TextView AuthText_ImagePicker;
    private static int SELECT_PICTURE = 2;
    Uri selected_Image;
    AppCompatActivity appCompatActivity = new AppCompatActivity();
    String filename;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_auth_header_api, container, false);
        AuthApiButton = v.findViewById(R.id.auth_header_button);
        AuthText_ImagePicker = v.findViewById(R.id.auth_header_pick_image);

        AuthText_ImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();


            }
        });

*/
/*
        AuthApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });*//*



        return v;
    }


    private void pickImage() {
        final CharSequence[] options = {"Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Image");
        builder.setItems(options, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int option) {
                if (options[option].equals("Choose from Gallery")) {
                    Dexter.withContext(getContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    getActivity().startActivityForResult(intent, SELECT_PICTURE);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                    Toast.makeText(getContext(), "No image select", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();

                } else if (options[option].equals("Cancel")) {
                    dialog.dismiss();
                }

            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        for (Fragment fragment :getChildFragmentManager().getFragments())
        {
            fragment.onActivityResult(requestCode, resultCode, data);
            if (requestCode == SELECT_PICTURE) {
                selected_Image = data.getData();
                getFilePath(selected_Image);

            }
        }

        }



    private void getFilePath(Uri selected_image) {
        File file =new File("/storage/emulated/0/sample/images.jpeg");
        String[] filepathcolumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(selected_image, filepathcolumn, null, null, null);
        cursor.moveToFirst();
        int columnImdex = cursor.getColumnIndex(filepathcolumn[0]);
        cursor.close();
        Toast.makeText(getContext(), cursor.getString(columnImdex), Toast.LENGTH_SHORT).show();
        AuthText_ImagePicker.setText(cursor.getColumnIndex(String.valueOf(columnImdex)));

    }


}*/
