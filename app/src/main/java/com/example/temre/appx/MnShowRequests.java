package com.example.temre.appx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temre.appx.event.GlobalBus;
import com.example.temre.appx.event.PersonEvent;
import com.example.temre.appx.models.Person;
import com.example.temre.appx.models.personInfo;
import com.example.temre.appx.service.ServiceGenerator;
import com.example.temre.appx.service.UserService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by temre on 22.08.2017.
 */

public class MnShowRequests extends AppCompatActivity {

    final Context context = this;
    UserService userService;
    Person eventPerson;
    private ListView listApps;
    Button buttonSave;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_for_manager);
        GlobalBus.getBus().register(this); // bilgileri nerede kullanacaksak orada tanımlıyoruz

        userService = ServiceGenerator.createService(UserService.class);
        listApps = (ListView)findViewById(R.id.ListViewReq);

        Call<List<Person>> callPersons = userService.GetMyTeamMemberRequests(eventPerson.getUsername());

        callPersons.enqueue(new Callback<List<Person>>() {

            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                if(response.isSuccessful()) {


                    List<Person> myTeamRequests = response.body();
                    FeedAdapter feedAdapter = new FeedAdapter(MnShowRequests.this,R.layout.list_record,myTeamRequests);
                    listApps.setAdapter(feedAdapter); // oluşturduğumuz feedAdapter i ListView e gömüyoruz ;]
                    buttonSave = (Button)findViewById(R.id.buttonSave);

                 buttonSave.setOnClickListener(new View.OnClickListener() {

                     @Override
                     public void onClick(View arg0) {

                         CheckBox cb;
                         TextView tv;
                         for (int x = 0; x<listApps.getChildCount();x++){
                             cb = (CheckBox)listApps.getChildAt(x).findViewById(R.id.checkBoxAccept);
                             tv = (TextView)listApps.getChildAt(x).findViewById(R.id.personUserName);
                             if(cb.isChecked()){
                                 System.out.println("checked "+tv.getText().toString());
                                 Call<personInfo> callPersonInfo =  userService.reqAcceptFunc(tv.getText().toString());
                                 callPersonInfo.enqueue(new Callback<personInfo>() {

                                     @Override
                                     public void onResponse(Call<personInfo> call, Response<personInfo> response) {

                                         if(response.isSuccessful()) {

                                             Intent intent = new Intent(context, ManagerMainMenu.class);
                                             startActivity(intent);
                                            // personInfo p = response.body();

                                         }else {

                                             Toast.makeText(getApplicationContext(),"resssponse fault",Toast.LENGTH_LONG).show();
                                         }

                                     }
                                     @Override
                                     public void onFailure(Call<personInfo> call, Throwable t) {

                                         Toast.makeText(getApplicationContext(),"EXCEPTION fail",Toast.LENGTH_LONG).show();
                                     }
                                 });
                                 System.out.println("update func is worked");
                             }
                         }


                     }
                 });


                }else {

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
