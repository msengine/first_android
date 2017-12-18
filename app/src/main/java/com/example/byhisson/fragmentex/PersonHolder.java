package com.example.byhisson.fragmentex;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by byhisson on 2017. 12. 18..
 */

public class PersonHolder {

    public ImageView typeImageView;
    public TextView titleView;
    public TextView dateView;
    public ImageView menuImangeView;

    public PersonHolder(View root){
        typeImageView = (ImageView)root.findViewById(R.id.custom_item_type_image);
        titleView = (TextView)root.findViewById(R.id.custom_item_title);
        dateView = (TextView)root.findViewById(R.id.custom_item_date);
        menuImangeView = (ImageView)root.findViewById(R.id.custom_item_menu);
    }

}
