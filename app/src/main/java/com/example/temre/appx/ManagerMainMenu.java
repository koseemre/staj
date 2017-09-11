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
 * Created by temre on 22.08.2017.
 */

public class ManagerMainMenu  extends AppCompatActivity {

    private Button history;
    private Button ShowRequests;
    private Button MnReq;

    private Person eventPerson;
    private Person person;
    private UserService userService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main_menu);
        GlobalBus.getBus().register(this); // bilgileri nerede kullanacaksak orada tanımlıyoruz
        person = eventPerson;

        history = (Button) findViewById(R.id.buttonMnPrHistory);
        ShowRequests = (Button) findViewById(R.id.buttonTeamRequest);
        MnReq= (Button) findViewById(R.id.mnPerReq);


        userService = ServiceGenerator.createService(UserService.class);

        Call<Person> callPerson = userService.getDetailedPermissionInfos(eventPerson.getUsername());


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

        MnReq.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                person.setId(eventPerson.getId());
                person.setPassword(eventPerson.getPassword());
                person.setUsername(eventPerson.getUsername());
                person.setRole(eventPerson.getRole());

                GlobalBus.getBus().postSticky(new PersonEvent(person));

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }
        });

        history.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {

                person.setId(eventPerson.getId());
                person.setPassword(eventPerson.getPassword());
                person.setUsername(eventPerson.getUsername());
                person.setRole(eventPerson.getRole());

                GlobalBus.getBus().postSticky(new PersonEvent(person));

                Intent intent = new Intent(context, MnHistory.class);
                startActivity(intent);

            }
        });

        ShowRequests.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {



                person.setId(eventPerson.getId());
                person.setPassword(eventPerson.getPassword());
                person.setUsername(eventPerson.getUsername());
                person.setRole(eventPerson.getRole());

                GlobalBus.getBus().postSticky(new PersonEvent(person));

                Intent intent = new Intent(context, MnShowRequests.class);
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
