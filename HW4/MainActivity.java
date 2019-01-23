//Author: Isaac Angle
//this class is the main class and uses the
//roll class to run threads that change the images
package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Random rand = new Random();
    private Button btnClick;
    private ImageView img1,img2,img3,img4,img5;
    private ImageView[] img = new ImageView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //making the button react to a click
        btnClick = (Button) findViewById(R.id.button);
        btnClick.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){
        Thread[] ts = new Thread[5];
        Roll[] r = new Roll[5];
        //setting the images to the ones already on the screen
        img1 = (ImageView) this.findViewById(R.id.imageView);
        img2 = (ImageView) this.findViewById(R.id.imageView2);
        img3 = (ImageView) this.findViewById(R.id.imageView5);
        img4 = (ImageView) this.findViewById(R.id.imageView4);
        img5 = (ImageView) this.findViewById(R.id.imageView7);

        //putting the images into an array for storage
        img[0] = img1;
        img[1] = img2;
        img[2] = img3;
        img[3] = img4;
        img[4] = img5;

        //creates 5 Roll objects
        for(int i = 0; i< r.length; i++){
            r[i] = new Roll(img[i]);
        }
        //shoots off 5 threads the change the images
        for(int i = 0; i <ts.length; i++){
            ts[i] = new Thread(r[i]);
            ts[i].start();
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
