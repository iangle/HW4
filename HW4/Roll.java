//Author: Isaac Angle
//this class sets up what happens to the images on the screen
package com.example.myapplication;

import android.media.Image;
import android.widget.ImageView;

import com.example.myapplication.R;
import java.util.Random;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Roll implements Runnable{
    private Random rand = new Random();
    private ImageView img;
    //this function runs the threads that allow for the dice to change
    //images at different times
    @Override
    public void run() {
        int numTimes = rand.nextInt(25);
        for (int i = 0; i < numTimes; i++) {
            int randNum = rand.nextInt(6);
            //changing the image randomly
            switch (randNum) {
                case 0:
                    img.setImageResource(R.drawable.dice1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.dice2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.dice3);
                    break;
                case 3:
                    img.setImageResource(R.drawable.dice4);
                    break;
                case 4:
                    img.setImageResource(R.drawable.dice5);
                    break;
                case 5:
                    img.setImageResource(R.drawable.dice6);
                    break;

            }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
    //default constructor used to add an image to the class
    public Roll(ImageView img){
    this.img = img;
    }
}
