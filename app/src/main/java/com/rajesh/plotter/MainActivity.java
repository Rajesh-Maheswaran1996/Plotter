package com.rajesh.plotter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Bitmap mutableBitmap;
    Bitmap tempBitmap;
    Bitmap workingBitmap;
    Paint paint = new Paint();
    float x,y;
    Canvas canvas;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csels,myOptions);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        workingBitmap = Bitmap.createBitmap(bitmap);
        mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
        tempBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //canvas = new Canvas(mutableBitmap);
                //imageView.setAdjustViewBounds(true);
                //imageView.setImageBitmap(mutableBitmap);
                canvas = new Canvas(mutableBitmap);
                //mutableBitmap.eraseColor(Color.TRANSPARENT);
                //imageView.setImageBitmap(tempBitmap);
                //Toast.makeText(getApplicationContext(),"X:"+String.valueOf(event.getX())+"  Y:"+String.valueOf(event.getY()),Toast.LENGTH_SHORT).show();
                x=event.getX();
                y=event.getY();
                canvas.drawCircle(x, y, 25, paint);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(mutableBitmap);
                mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

                //imageView.invalidate();
                //Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

}
