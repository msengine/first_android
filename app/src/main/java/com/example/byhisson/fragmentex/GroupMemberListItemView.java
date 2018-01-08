package com.example.byhisson.fragmentex;

import android.support.v4.media.session.IMediaControllerCallback;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by byhisson on 2018. 1. 5..
 */

public class GroupMemberListItemView {

    public TextView groupMemberView;
    public ImageView memberImageView;

    public GroupMemberListItemView(View root){
        groupMemberView = (TextView) root.findViewById(R.id.group_member_item);
        memberImageView = (ImageView) root.findViewById(R.id.group_member_item_menu);
    }
}
