package com.abc.money.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by shrishty on 7/19/15.
 */
public class Tab3 extends Fragment {

    Button btnAddMore;
    SQLiteDatabase sqLiteDatabase;
    UserMoneyDbHelper userMoneyDbHelper;
    ListView lview;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_3,container,false);

        lview = (ListView) v.findViewById(R.id.lvHome);
        listDataAdapter = new ListDataAdapter(getActivity(), R.layout.row_layout);
        lview.setAdapter(listDataAdapter);
        userMoneyDbHelper = new UserMoneyDbHelper(getActivity());
        sqLiteDatabase = userMoneyDbHelper.getReadableDatabase();
        cursor = userMoneyDbHelper.getUserMoneyInformation(sqLiteDatabase);

        if(cursor.moveToFirst()){
            do {

                String name;
                int amount;
                name = cursor.getString(0);
                Log.e("Money1234", " b4 name" + name);
                amount = Integer.parseInt(cursor.getString(1));
                Log.e("Money1234", " inside cursor " + amount);
                if(amount < 0) {
                    DataProvider dataProvider = new DataProvider(name, amount);
                    listDataAdapter.add(dataProvider);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();


        btnAddMore = (Button) v.findViewById(R.id.btnAddMoreToGive);
        btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPeopleFragment frag = new AddPeopleFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.main_activity, frag, "frag-2").addToBackStack("tab3");
                transaction.commit();
            }
        });
        return v;
    }
}
