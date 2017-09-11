package com.example.temre.appx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;
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

public class RequestSituation extends AppCompatActivity {

    private TextView info;
    private CheckBox isSend;
    private CheckBox isOk;
    private Person eventPerson;

    private UserService userService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_situation);


        info = (TextView) findViewById(R.id.textViewReqInf);
        isSend = (CheckBox) findViewById(R.id.cbSend);
        isOk = (CheckBox) findViewById(R.id.cbisOK);

        userService = ServiceGenerator.createService(UserService.class);
        //Login login = new Login(id,password,false);
        GlobalBus.getBus().register(this);
        System.out.println("usernameee "+ eventPerson.getUsername());

        Call<Person> callLogins = userService.GetMyRequestSituationF(eventPerson.getUsername());



        callLogins.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                if(response.isSuccessful()) {
                   // Person person = eventPerson;

                    if(response.body() != null){
                        Person person;
                        person = eventPerson;
                      //  person = response.body();

                    person.setExcuse(response.body().getExcuse().toString());
                    person.setStart_date(response.body().getStart_date().toString());
                    person.setEnd_date(response.body().getEnd_date().toString());
                    person.setGivenRequest(response.body().isGivenRequest());
                    person.setTotal(response.body().getTotal());
                        person.setRequest(response.body().isRequest());


                        String requestStr = person.getStart_date() + "  " +  person.getEnd_date();

                        info.setText(requestStr);
                        isSend.setChecked(person.isRequest());
                        isOk.setChecked(person.isGivenRequest());

                        GlobalBus.getBus().postSticky(new PersonEvent(person));
                    }

                }

            }
            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                System.out.println("there in6 ");
                Toast.makeText(getApplicationContext(),"EXCEPTION fail",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PersonEvent event) {

        eventPerson  = event.getPerson();
        Log.d("onMessageEvent", " name: "+eventPerson.getName());
    };
}
