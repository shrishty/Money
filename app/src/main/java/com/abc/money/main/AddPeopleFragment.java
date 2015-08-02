package com.abc.money.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by shrishty on 7/25/15.
 */
public class AddPeopleFragment extends Fragment {

    EditText etName;
    EditText etAmount;
    RadioButton rbtntake, rbtnGive;
    Button btnadd;
    int take = 1;
    int give = 2;
    int selected;
    UserMoneyDbHelper userMoneyDbHelper;
    SQLiteDatabase sqLiteDatabase;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("MONEY123", "Inside add ppl1");
        View view = inflater.inflate(R.layout.fragment_add_more, container, false);

        etName = (EditText) view.findViewById(R.id.etName);
        etAmount= (EditText) view.findViewById(R.id.etAmount);
        rbtnGive = (RadioButton) view.findViewById(R.id.rbGive);
        rbtntake = (RadioButton) view.findViewById(R.id.rbTake);
        btnadd = (Button) view.findViewById(R.id.btnAddMore);

        Log.e("MONEY123", "Inside add ppl1");
        rbtntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = take;
                Toast.makeText(getActivity(), "Take", Toast.LENGTH_SHORT).show();
            }
        });

        rbtnGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = give;
                Toast.makeText(getActivity(), "Give", Toast.LENGTH_SHORT).show();
            }
        });

//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()){
//            case R.id.rbTake:
//                Toast.makeText(getActivity(), "Take", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.rbGive:
//                Toast.makeText(getActivity(), "Give", Toast.LENGTH_SHORT).show();
//                break;
//        }

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get back to previous fragment
                String user_name = etName.getText().toString();
                int amount = Integer.parseInt(etAmount.getText().toString());
                if(selected == give) {
                    amount = amount * -1;
                }

                userMoneyDbHelper = new UserMoneyDbHelper(getActivity());
                sqLiteDatabase = userMoneyDbHelper.getWritableDatabase();

                userMoneyDbHelper.addUserMoney(user_name, amount, 0, sqLiteDatabase);
                Toast.makeText(getActivity(), "Added one more person to your list", Toast.LENGTH_SHORT).show();
                userMoneyDbHelper.close();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        return view;

    }
}
