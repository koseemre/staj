package com.example.temre.appx;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.temre.appx.models.Person;

import java.util.List;

/**
 * Created by temre on 7.09.2017.
 */

public class FeedAdapterForHistory extends ArrayAdapter {

    private static final String TAG = "FeedAdapterForHistory";

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Person> applications;

    public FeedAdapterForHistory(@NonNull Context context, @LayoutRes int resource, List<Person> applications) {
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
        TextView nameView  = (TextView) convertView.findViewById(R.id.personUserNameH);
        TextView idView  = (TextView) convertView.findViewById(R.id.permissionExcuseH);
        TextView detailView  = (TextView) convertView.findViewById(R.id.permissionDetailH);
        Person currentApp = applications.get(position);

        nameView .setText(currentApp.getUsername());
        idView .setText(currentApp.getExcuse());
        String detail = currentApp.getStart_date() +"  "+ currentApp.getEnd_date() + "  at total:  " + currentApp.getTotal() + " day(s)";
        detailView .setText(detail);

        // return view;
        return convertView;
    }
}
