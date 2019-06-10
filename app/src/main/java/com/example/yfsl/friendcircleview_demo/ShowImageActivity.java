package com.example.yfsl.friendcircleview_demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class ShowImageActivity extends Activity {
    private RelativeLayout showBackground;
    private FriendCircleView friendCircleView;
    private int left,top,width,height;

    public void getArgus(){
        Intent intent = getIntent();
        if (intent != null){
            left = intent.getIntExtra("left",0);
            top = intent.getIntExtra("top",0);
            width = intent.getIntExtra("width",0);
            height = intent.getIntExtra("height",0);
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimage);
        getArgus();

        showBackground = findViewById(R.id.show_bg);
        friendCircleView = findViewById(R.id.showimage);

        showBackground.setBackgroundColor(Color.BLACK);

        int[] location = new int[2];
        location[0] = left;
        location[1] = top;

        friendCircleView.setOriginView(width,height,BitmapFactory.decodeResource(getResources(),R.drawable.icon_iv_loading),location);
    }
}
