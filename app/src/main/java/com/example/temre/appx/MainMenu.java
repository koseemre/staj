package com.example.temre.appx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.temre.appx.event.GlobalBus;
import com.example.temre.appx.event.PersonEvent;
import com.example.temre.appx.models.Person;
import com.example.temre.appx.service.ServiceGenerator;
import com.example.temre.appx.service.UserService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by temre on 17.08.2017.
 */
public class MainMenu extends AppCompatActivity {

    private Button history;
    private Button request;
    private Button requestSituation;
    private Person eventPerson;
    private Person person;
    private UserService userService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        GlobalBus.getBus().register(this); // bilgileri nerede kullanacaksak orada tanımlıyoruz


        history = (Button) findViewById(R.id.buttonHistory);
        request = (Button) findViewById(R.id.buttonRequest);
        requestSituation = (Button) findViewById(R.id.situation);
        person = eventPerson;

        userService = ServiceGenerator.createService(UserService.class);

        Call<Person> callPerson = userService.getDetailedPermissionInfos(eventPerson.getUsername().toString());


        callPerson.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                if (response.isSuccessful()) {


                    Toast.makeText(getApplicationContext(), "response is ok", Toast.LENGTH_LONG).show();
                        Person tempPerson;
                        tempPerson = response.body();
                        person.setRemainingComp(tempPerson.getRemainingComp());
                        person.setRemainingVac(tempPerson.getRemainingVac());



                }

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                System.out.println("there in6 ");
                Toast.makeText(getApplicationContext(), "EXCEPTION fail", Toast.LENGTH_LONG).show();
            }
        });


        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        history.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                person.setRole(eventPerson.getRole());
                person.setPassword(eventPerson.getPassword());
                person.setUsername(eventPerson.getUsername());
                person.setRequest(eventPerson.isRequest());
                GlobalBus.getBus().postSticky(new PersonEvent(person));
                Intent intent = new Intent(context, History.class);
                startActivity(intent);

            }
        });

        requestSituation.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {



               /* String password = eventPerson.getPassword();
                String username = eventPerson.getUsername();
                int role = eventPerson.getRole();*/

                person.setRole(eventPerson.getRole());
                person.setPassword(eventPerson.getPassword());
                person.setUsername(eventPerson.getUsername());
                person.setRequest(eventPerson.isRequest());

                GlobalBus.getBus().postSticky(new PersonEvent(person));

                Intent intent = new Intent(context, RequestSituation.class);
                startActivity(intent);


            }
        });

        request.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                int id = eventPerson.getId();
                String password = eventPerson.getPassword();
                String username = eventPerson.getUsername();


                person.setId(id);
                person.setPassword(password);
                person.setUsername(username);
                person.setRole(eventPerson.getRole());

                Toast.makeText(getApplicationContext(),"its username  " + eventPerson.getUsername() + "vac" + person.getRemainingVac() ,Toast.LENGTH_LONG).show();

                GlobalBus.getBus().postSticky(new PersonEvent(person));

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });



    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PersonEvent event) {

        eventPerson  = event.getPerson();
        Log.d("onMessageEvent", " name: "+eventPerson.getName());
    };


}