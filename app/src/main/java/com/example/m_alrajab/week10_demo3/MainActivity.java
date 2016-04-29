package com.example.m_alrajab.week10_demo3;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    StartDraggingLsntr myStartDraggingLsnr;
    EndDraggingLsntr myEndDraggingLsntr;
    Button rectBtn, ovalBtn, btn1, btn2;
    //MyPanel panel;
    MediaPlayer waves;
    Animation animation;
    Animation animation1_1;
    ImageView stickFigure;
    int stepNumber = 0;     //I needed to create this because the animationListener was not working.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStartDraggingLsnr=new StartDraggingLsntr();
        myEndDraggingLsntr=new EndDraggingLsntr();

        stickFigure = (ImageView) findViewById(R.id.stickFigure);
        rectBtn=(Button) findViewById(R.id.rectBtn);
        findViewById(R.id.rectBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.upBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.downBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.leftBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.rightBtn).setOnLongClickListener(myStartDraggingLsnr);

        findViewById(R.id.Btn1).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn2).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn3).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn4).setOnDragListener(myEndDraggingLsntr);


        /*
        http://stackoverflow.com/questions/8257530/how-to-make-the-music-loop-after-the-music-ends-android
        http://stackoverflow.com/questions/7291731/how-to-play-audio-file-in-android
        https://www.freesound.org/people/Luftrum/sounds/48412
        http://bugmenot.com/view/freesound.org
        http://developer.android.com/guide/appendix/media-formats.html
        https://www.youtube.com/watch?v=V1ocJmXeQ28
        */

        waves = MediaPlayer.create(MainActivity.this, R.raw.oceanwavescrushing);
        waves.setLooping(true);
        waves.start();

        //animate();

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);

        animation.setAnimationListener(new Animation.AnimationListener() {
            int a;
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // the second part of the anima
                Toast.makeText(MainActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(MainActivity.this, "repeated", Toast.LENGTH_SHORT).show();
            }
        });
        animation1_1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate1_1);
        animation1_1.setAnimationListener(new Animation.AnimationListener() {
            int b;
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "started", Toast.LENGTH_SHORT).show();
                System.out.println("qwertyuiop");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // the second part of the anima
                Toast.makeText(MainActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(MainActivity.this, "repeated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void animate()
    {
        TranslateAnimation anim=new TranslateAnimation(0,0,100,100);


        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // the second part of the anima
                Toast.makeText(MainActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

/* - This is not needed.  The sound file plays without it.
    public void playMusic(View view)
    {
        waves.start();
    }
*/

    public void play(View view) {

        int left = stickFigure.getLeft();
        float X = stickFigure.getX();
        float Y = stickFigure.getY();
        //Toast.makeText(MainActivity.this, "left: " + left + " X: " + X + " Y: " + Y, Toast.LENGTH_LONG).show();

        try {
            if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right")
                    && findViewById(R.id.Btn2).getContentDescription().equals("up")) {
                animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                stickFigure.startAnimation(animation);
                stepNumber = 2;

            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right") &&
                    (! findViewById(R.id.Btn2).getContentDescription().equals("up") || findViewById(R.id.Btn2).getContentDescription().equals(null)))
            {
                animation1_1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate1_1);
                stickFigure.startAnimation(animation1_1);
                stepNumber = 1;
            }
        }
        catch (NullPointerException e)
        {
            //Toast.makeText(MainActivity.this, "There is nothing in step 1 to be played.", Toast.LENGTH_SHORT).show();
        }

        //SharedValuesXY.drawingMode="RECT";
        String description;
        description = "sequence: " + findViewById(R.id.Btn1).getContentDescription() + ", "
                + findViewById(R.id.Btn2).getContentDescription() + ", "
                + findViewById(R.id.Btn3).getContentDescription() + ", "
                + findViewById(R.id.Btn4).getContentDescription();
        //I found getContentDescription by typing "get" after the button and then looking through the suggestions.
        //Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();

        if (stepNumber == 2)
        {
            try {
                //http://android-developers.blogspot.com/2009/05/painless-threading.html
                //http://stackoverflow.com/questions/6732529/cant-create-handler-looper-prepare-in-inherited-activity
                final Handler[] innerHandler = new Handler[1];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        innerHandler[0] = new Handler() {
                            @Override
                            public void handleMessage(Message message)
                            {
                                Toast.makeText(MainActivity.this, "go to level 2", Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void dispatchMessage(Message message)
                            {
                                handleMessage(message);
                            }
                            /*
                            //http://stackoverflow.com/questions/3342651/how-can-i-delay-a-java-program-for-a-few-seconds
                            try {
                                Thread.sleep(1000);                 //1000 milliseconds is one second.
                                Toast.makeText(MainActivity.this, "go to level 2", Toast.LENGTH_LONG).show();
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            */
                        };

                        //Message message = innerHandler.obtainMessage();
                        //innerHandler.dispatchMessage(message);
                        Looper.loop();
                    }
                }).start();
            }
            catch (Exception ex) {
                System.out.println(ex);
                Toast.makeText(MainActivity.this, "go to next level", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void exit(View view) {
        Toast.makeText(MainActivity.this, "exit the game", Toast.LENGTH_SHORT).show();
    }

    private class EndDraggingLsntr implements View.OnDragListener{
        @Override
        public boolean onDrag(View view, DragEvent event) {
            if (event.getAction()==DragEvent.ACTION_DROP)
            {
                ((Button) view).setBackground( ((Button) event.getLocalState()).getBackground());
                ((Button) view).setContentDescription( ((Button) event.getLocalState()).getContentDescription());
                ((Button) view).setText("    ");
            }

            return true;
        }
    }

    private class StartDraggingLsntr implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View view) {
            WithDraggingShadow shadow = new WithDraggingShadow(view);
            ClipData data=ClipData.newPlainText("","");
            view.startDrag( data, shadow, view, 0);
            return false;
        }
    }

    //Bitmap image;
    private class WithDraggingShadow extends View.DragShadowBuilder{
        //public WithDraggingShadow(View view, Bitmap draggingPicture){
        public WithDraggingShadow(View view){
            super(view);
            //image=draggingPicture;
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
        }
    }
}
