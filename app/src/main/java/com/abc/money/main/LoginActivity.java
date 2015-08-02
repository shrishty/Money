package com.abc.money.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shrishty on 7/25/15.
 */
public class LoginActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "shFile";
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        final EditText etname = (EditText) findViewById(R.id.etName);
        final EditText etphno = (EditText) findViewById(R.id.etPhone);
        final EditText etemail = (EditText) findViewById(R.id.etEmail);
        final EditText etpassword = (EditText) findViewById(R.id.etPassword);
        Button btnSubmit = (Button) findViewById(R.id.btnRegister);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do database operations
                String user_name = etname.getText().toString();
                String password = etpassword.getText().toString();
                String email = etemail.getText().toString();
                String phon = etphno.getText().toString();

                userDbHelper = new UserDbHelper(context);
                sqLiteDatabase = userDbHelper.getWritableDatabase();

                userDbHelper.addInfo(user_name, phon, email, password, sqLiteDatabase);
                Toast.makeText(getBaseContext(), "You are Registered", Toast.LENGTH_LONG).show();
                userDbHelper.close();

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn", true);
                editor.commit();

                Intent activityChangeIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(activityChangeIntent);
                LoginActivity.this.finish();
            }
        });
    }

}
