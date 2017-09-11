package com.example.temre.appx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temre.appx.event.GlobalBus;
import com.example.temre.appx.event.PersonEvent;
import com.example.temre.appx.models.Login;
import com.example.temre.appx.models.Person;
import com.example.temre.appx.service.ServiceGenerator;
import com.example.temre.appx.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by temre on 17.08.2017.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";


    private EditText idEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserService userService;

    private Button startDateButton;
    private Button timeButton;
    private TextView startDate;
    private TextView startTime;


    private Person person;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        addListenerOnButton();


    }

    public void addListenerOnButton() {

        final Context context = this;

        loginButton = (Button) findViewById(R.id.LoginButton);

        idEditText = (EditText) findViewById(R.id.LoginUserName);
        passwordEditText = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String username = idEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                userService = ServiceGenerator.createService(UserService.class);
                Call<Login> callLogins = userService.login(username);

                callLogins.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {

                        if (response.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "response is ok", Toast.LENGTH_LONG).show();
                            Login resultLogin;
                            resultLogin = response.body();
                            System.out.println(resultLogin.getPassword());

                            if (response.body() != null) {
                                if (password.equals(resultLogin.getPassword())) {

                                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                                    person = new Person();

                                    person.setUsername(resultLogin.getUsername());
                                    person.setPassword(passwordEditText.getText().toString());
                                    person.setRole(resultLogin.getRole());


                                    if (person.getRole() != 3) //manager girisi
                                    {

                                        GlobalBus.getBus().postSticky(new PersonEvent(person));

                                        Intent intent = new Intent(context, ManagerMainMenu.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    } else if (person.getRole() == 3) //developer girisi
                                    {
                                        GlobalBus.getBus().postSticky(new PersonEvent(person));

                                        Intent intent = new Intent(context, MainMenu.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }

                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "WRONG PASSWORD OR USERNAME", Toast.LENGTH_LONG).show();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        System.out.println("there in6 ");
                        Toast.makeText(getApplicationContext(), "EXCEPTION fail", Toast.LENGTH_LONG).show();
                    }
                });

            }

        });

    }


}
