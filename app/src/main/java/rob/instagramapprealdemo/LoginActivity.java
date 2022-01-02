package rob.instagramapprealdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    AutoCompleteTextView usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Set<String> getUsername;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initial();
        sharedPerfenceFun(this);


    }



    private void initial() {
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);



        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int lengthOfUsername, int i1, int i2) {
                if (lengthOfUsername > 0){
                    loginButton.setEnabled(true);
                }else if (lengthOfUsername == 0){
                    loginButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int lengthOfTextChanged, int i1, int i2) {
                if (lengthOfTextChanged > 0 ){
                    loginButton.setEnabled(true);
                }else if (lengthOfTextChanged == 0){
                    loginButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });


    }
    private void sharedPerfenceFun(LoginActivity loginActivity) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor = preferences.edit();





        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsername = new HashSet<>();
                getUsername.add(usernameEditText.getText().toString().trim());

                //Put Value into SharedPreference and Apply it.
                editor.putStringSet("UserNameOfShared", getUsername);

                editor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "created", BaseTransientBottomBar.LENGTH_LONG).show();

            }
        });










        SharedPreferences getPreferences =  PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        Set<String> getUsernameHistory = new HashSet<>();
        Set<String> getUserName = getPreferences.getStringSet("UserNameOfShared", getUsernameHistory);

        String[] USERNAMEHISTROY = new String[getUserName.size()];
        getUserName.toArray(USERNAMEHISTROY);
        Log.i(TAG, "sharedPerfenceFun: "+ getUserName);
        ArrayAdapter<String> historyInputAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, USERNAMEHISTROY);
        usernameEditText.setAdapter(historyInputAdapter);
        usernameEditText.setThreshold(1);


        
        


    }


}