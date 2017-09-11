package com.example.temre.appx;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.temre.appx.event.GlobalBus;
import com.example.temre.appx.event.PersonEvent;
import com.example.temre.appx.models.Person;
import com.example.temre.appx.service.ServiceGenerator;
import com.example.temre.appx.service.UserService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by temre on 17.08.2017.
 */

public class MnHistory extends AppCompatActivity{


    final Context context = this;
    UserService userService;
    Person eventPerson;
    private ListView listApps;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mn_history);
        GlobalBus.getBus().register(this); // bilgileri nerede kullanacaksak orada tanımlıyoruz

        userService = ServiceGenerator.createService(UserService.class);
        listApps = (ListView)findViewById(R.id.mnHistoryLW);

        Call<List<Person>> callPersons = userService.GetMyPerHistoryFunc(eventPerson.getUsername());
        callPersons.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                if(response.isSuccessful()) {

                    List<Person> myRequestHistory = response.body();
                    FeedAdapterForHistory feedAdapter = new FeedAdapterForHistory(MnHistory.this,R.layout.list_record_history,myRequestHistory);
                    listApps.setAdapter(feedAdapter); // oluşturduğumuz feedAdapter i ListView e gömüyoruz ;]
                }
                else {

                    Toast.makeText(getApplicationContext(),"response fault",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

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
