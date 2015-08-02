package com.abc.money.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.*;

/**
 * Created by shrishty on 8/2/15.
 */
public class ListOnClickFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    TextView name;
    TextView amount;
    SeekBar seekBar;
    Button btnUpdate;
    RadioButton rbtnTake, rbtnGive, rbtnDone;
    Bundle bundle;
    EditText etAddSub;
    int take = 1;
    int give = 2;
    int done = 3;
    int selected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_edit, container, false);

        name = (TextView) view.findViewById(R.id.tvName);
        amount = (TextView) view.findViewById(R.id.tvAmount);
        seekBar = (SeekBar) view.findViewById(R.id.sbChooseAmount);
        rbtnTake = (RadioButton) view.findViewById(R.id.rbTake);
        rbtnGive = (RadioButton) view.findViewById(R.id.rbGive);
        rbtnDone = (RadioButton) view.findViewById(R.id.rbDone);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        etAddSub = (EditText) view.findViewById(R.id.etAmountToBeAddedOrSubtracted);

        bundle = this.getArguments();
        if (bundle != null) {
            name.setText(bundle.getString("name"));
            amount.setText(bundle.getString("amount"));
        }

        seekBar.setOnSeekBarChangeListener(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rbtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = done;
            }
        });

        rbtnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = take;
            }
        });

        rbtnGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = give;
            }
        });

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e("abc", "helloo");
        etAddSub.setText("" + progress);

        if (selected == give) {
            amount.setText("" + (parseInt(amount.getText().toString()) -
                    parseInt(etAddSub.getText().toString())));
        } else if (selected == done) {
            amount.setText("0");
        } else {
            amount.setText("" + (parseInt(amount.getText().toString()) +
                    parseInt(etAddSub.getText().toString())));
            }
        }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
