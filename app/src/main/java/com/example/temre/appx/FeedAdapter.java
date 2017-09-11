package com.example.temre.appx;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.temre.appx.models.Person;

import java.util.List;

/**
 * Created by temre on 16.08.2017.
 */

public class FeedAdapter extends ArrayAdapter {

    private static final String TAG = "FeedAdapter";

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Person> applications;

    public FeedAdapter(@NonNull Context context, @LayoutRes int resource, List<Person> applications) {
        super(context, resource); //context=MainActivity , resource = list_record
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }


    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(layoutResource,parent,false);
        }

        //View view  = layoutInflater.inflate(layoutResource,parent,false);
        TextView nameView  = (TextView) convertView.findViewById(R.id.personUserName);
        TextView idView  = (TextView) convertView.findViewById(R.id.permissionExcuse);
        TextView detailView  = (TextView) convertView.findViewById(R.id.permissionDetail);
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBoxAccept);
        Person currentApp = applications.get(position);

        nameView .setText(currentApp.getUsername());
        idView .setText(currentApp.getExcuse());
        String detail = currentApp.getStart_date() +"  "+ currentApp.getEnd_date() + "  at total:  " + currentApp.getTotal() + " day(s)";
        detailView .setText(detail);

        // return view;
        return convertView;
    }
}
