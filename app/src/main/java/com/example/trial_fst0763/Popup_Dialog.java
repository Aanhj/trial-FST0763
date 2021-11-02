package com.example.trial_fst0763;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.fst_t0763.R;

public class Popup_Dialog extends AppCompatDialogFragment {

    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
    View v=getLayoutInflater().inflate(R.layout.popup_ticketbook,null);


}
