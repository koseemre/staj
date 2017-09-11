package com.example.temre.appx;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.temre.appx.event.GlobalBus;
import com.example.temre.appx.event.PersonEvent;
import com.example.temre.appx.models.Person;
import com.example.temre.appx.models.Reason;
import com.example.temre.appx.service.ServiceGenerator;
import com.example.temre.appx.service.UserService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final String TEXT_CONTENTS = "TextContents";
    List<Reason> reasons = new ArrayList<>();
    private UserService userService;

    private Person eventPerson;
    private Person person;

    private EditText total;
    private Spinner excuses;
    private Button sendRequest;
// time issue
    private Button startDateButton;
    private Button startTimeButton;
    private Button endDateButton;
    private Button endTimeButton;

    private TextView startDate;
    private TextView startTime;
    private TextView endDate;
    private TextView endTime;

// permission req
    private TextView vac;
    private TextView comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalBus.getBus().register(this);

        person = eventPerson;


        String username = eventPerson.getUsername();
        String password = eventPerson.getPassword();
        int role = eventPerson.getRole();


        Toast.makeText(getApplicationContext(),"its username  " + username + "vac" + eventPerson.getRemainingVac() ,Toast.LENGTH_LONG).show();


        userService = ServiceGenerator.createService(UserService.class);


        vac = (TextView) findViewById(R.id.rmV);
        comp = (TextView) findViewById(R.id.rmC);
        
        
        startDateButton = (Button) findViewById(R.id.startDateButton);
        startTimeButton = (Button) findViewById(R.id.startTimeButton);
        startDate = (TextView) findViewById(R.id.sd);
        startTime = (TextView) findViewById(R.id.st);
        endDateButton = (Button) findViewById(R.id.endDateButton);
        endTimeButton = (Button) findViewById(R.id.endTimeButton);
        endDate = (TextView) findViewById(R.id.ed);
        endTime = (TextView) findViewById(R.id.et);

        excuses = (Spinner) findViewById(R.id.ExcuseSpinner);
        sendRequest = (Button) findViewById(R.id.SendPermission);
        total = (EditText) findViewById(R.id.TotalPerms);

        vac.setText( Float.toString(eventPerson.getRemainingVac()));
        comp.setText(Float.toString(eventPerson.getRemainingComp()));

        
        addItemsOnSpinner();
        addListenerOnButton();

    }

    // add items into spinner dynamically
    public void addItemsOnSpinner() {

        excuses = (Spinner) findViewById(R.id.ExcuseSpinner);



        Call<List<Reason>> callReasons = userService.GetReasonsFunc();

        callReasons.enqueue(new Callback<List<Reason>>() {

            @Override
            public void onResponse(Call<List<Reason>> call, Response<List<Reason>> response) {

                if(response.isSuccessful()) {

                    reasons = response.body();

            /*   for(int i=0; i< response.body().size() ; i++){
                    reasons.add(response.body().get(i));
                   System.out.println(i+);

                }*/  List<String> list = new ArrayList<String>();

                    if(reasons != null)
                        for(int i=0;i<reasons.size();i++){
                            list.add(reasons.get(i).getReason());
                        }
                    else{
                        list.add("Yıllık izin");
                        list.add("Hastalık");
                        list.add("Evlenme");
                        list.add("Diğer");
                    }



                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);

                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    excuses.setAdapter(dataAdapter);


                }
                else {

                    Toast.makeText(getApplicationContext(),"response fault",Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<List<Reason>>call, Throwable t) {

                Toast.makeText(getApplicationContext(),"EXCEPTION fail",Toast.LENGTH_LONG).show();
            }
        });





    }

    public void addListenerOnButton() {

        final Context context = this;

        startTimeButton.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

                timePicker.show();
            }
        });
        endTimeButton.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();//
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
                int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
                TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk

                //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
                timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTime.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
                    }
                }, hour, minute, true);//true 24 saatli sistem için
                timePicker.setTitle("Saat Seçiniz");
                timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
                timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

                timePicker.show();
            }
        });

        startDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

                DatePickerDialog datePicker;//Datepicker objemiz
                datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        startDate.setText( dayOfMonth + "." + monthOfYear+ "."+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                    }
                },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                datePicker.show();
            }
        });
        endDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
                int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

                DatePickerDialog datePicker;//Datepicker objemiz
                datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        endDate.setText( dayOfMonth + "." + monthOfYear+ "."+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

                    }
                },year,month,day);//başlarken set edilcek değerlerimizi atıyoruz
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                datePicker.show();
            }
        });

        sendRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int role = eventPerson.getRole();

                String password = eventPerson.getPassword();
                String username = eventPerson.getUsername();


                String start = startDate.getText().toString() +" " + startTime.getText().toString()+":00.0000";
                String end = endDate.getText().toString() +" " + endTime.getText().toString()+":00.0000";

                float total2 = Float.valueOf(total.getText().toString());
                person = new Person();
                person.setRole(role);
                person.setUsername(username);
                person.setPassword(password);
                person.setStart_date(start);
                person.setEnd_date(end);
                String exc = excuses.getSelectedItem().toString();
                int excuseId = -1;
                person.setExcuse(exc);
                if(exc.toLowerCase().contains("yıllık"))
                    excuseId = 12;
                else if(exc.toLowerCase().contains("okul"))
                    excuseId = 13;
                else if(exc.toLowerCase().contains("ya da"))
                    excuseId = 1;
                else if(exc.toLowerCase().contains("yakın ailesinden"))
                    excuseId = 2;
                else if(exc.toLowerCase().contains("erkekler için"))
                    excuseId =3;
                else if(exc.toLowerCase().contains("ebeveyninin vefatı"))
                    excuseId = 4;
                else if(exc.toLowerCase().contains("akrabasının vefatı"))
                    excuseId = 5;
                else if(exc.toLowerCase().contains("askerlik"))
                    excuseId =6;
                else if(exc.toLowerCase().contains("aynı"))
                    excuseId =7 ;
                else if(exc.toLowerCase().contains("şehirler arası"))
                    excuseId = 8;
                else if(exc.toLowerCase().contains("doğum izni"))
                    excuseId = 9;
                else if(exc.toLowerCase().contains("hastalık izni (raporlu)"))
                    excuseId = 10;
                else if(exc.toLowerCase().contains("hastalık izni (raporsuz)"))
                    excuseId = 14;
                else
                    excuseId = -1;

                person.setTotal(total2);
                person.setRequest(false);



                userService = ServiceGenerator.createService(UserService.class);

                Call<String> callLogins = userService.sendRequest(person.getUsername(),
                        person.getRole(),person.getStart_date(),
                        person.getEnd_date(),person.getExcuse(),
                        person.getTotal(),excuseId);

                callLogins.enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if(response.isSuccessful()) {

                            String resultReq;
                            resultReq = response.body();

                            if(resultReq.equals("ok")){
                                Toast.makeText(getApplicationContext(),"response is taken",Toast.LENGTH_LONG).show();
                                person.setRequest(true);
                                GlobalBus.getBus().postSticky(new PersonEvent(person));
                                Intent intent = new Intent(context, MainMenu.class);
                                startActivity(intent);
                            }


                        }
                        else {

                             Toast.makeText(getApplicationContext(),"response fault",Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),"EXCEPTION fail",Toast.LENGTH_LONG).show();
                    }
                });






            }
        });

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);
//        String savedString = savedInstanceState.getString(TEXT_CONTENTS);
//        textView.setText(savedString);
     //   textView.setText(savedInstanceState.getString(TEXT_CONTENTS));
        Log.d(TAG, "onRestoreInstanceState: out");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");
      //  outState.putString(TEXT_CONTENTS, textView.getText().toString());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: in");
        super.onResume();
        Log.d(TAG, "onResume: out");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PersonEvent event) {
        eventPerson  = event.getPerson();
        Log.d("onMessageEvent", " name: "+eventPerson.getName());
    };


}
