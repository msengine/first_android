package com.example.byhisson.fragmentex;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by byhisson on 2017. 12. 18..
 * findViewById()는 내부적으로 많은 코드가 실행되는 함수
 * 일단 처음 뷰를 획득하여 저장한 다음 findViewById() 함수를
 * 호출하지 않고 저장된 뷰를 다시 이용하도록 한다
 */

public class PersonListItemView {

    public ImageView typeImageView;
    public TextView nameView;
    public TextView addressView;
    public ImageView menuImageView;

    public PersonListItemView(View root){
        typeImageView = (ImageView)root.findViewById(R.id.custom_item_type_image);
        nameView = (TextView)root.findViewById(R.id.custom_item_name);
        addressView = (TextView)root.findViewById(R.id.custom_item_address);
        menuImageView = (ImageView)root.findViewById(R.id.custom_item_menu);
    }

}
