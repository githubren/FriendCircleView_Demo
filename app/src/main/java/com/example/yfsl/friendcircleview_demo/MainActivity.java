package com.example.yfsl.friendcircleview_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.thum_image);

        Bitmap bitmap = getImageThumbnail(R.drawable.icon_iv_loading,450,450);
        image.setImageBitmap(bitmap);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowImageActivity.class);
                int[] imageLocation = new int[2];
                v.getLocationOnScreen(imageLocation);
                intent.putExtra("left",imageLocation[0]);
                intent.putExtra("top",imageLocation[1]);
                intent.putExtra("width",image.getWidth());
                intent.putExtra("height",image.getHeight());
                startActivity(intent);
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });
    }

    /**
     * 获取缩略图
     * @param imageId
     * @param width
     * @param height
     * @return
     */
    private Bitmap getImageThumbnail(int imageId, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeResource(getResources(),imageId,options);
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        options.inJustDecodeBounds = false; // 设为 false
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeResource(getResources(),imageId,options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }
}
