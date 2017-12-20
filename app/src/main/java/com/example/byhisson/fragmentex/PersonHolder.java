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

public class PersonHolder {

    public ImageView typeImageView;
    public TextView textView1;
    public TextView textView2;
    public ImageView menuImangeView;

    public PersonHolder(View root){
        typeImageView = (ImageView)root.findViewById(R.id.custom_item_type_image);
        textView1 = (TextView)root.findViewById(R.id.custom_item1);
        textView2 = (TextView)root.findViewById(R.id.custom_item2);
        menuImangeView = (ImageView)root.findViewById(R.id.custom_item_menu);
    }

}
