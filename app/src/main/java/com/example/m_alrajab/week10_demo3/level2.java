package com.example.m_alrajab.week10_demo3;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class level2 extends AppCompatActivity {

    StartDraggingLsntr myStartDraggingLsnr;
    EndDraggingLsntr myEndDraggingLsntr;
    Button rectBtn, ovalBtn, btn1, btn2;
    //MyPanel panel;
    MediaPlayer waves;
    Animation animation;
    Animation animation2_1;
    Animation animation1_2;
    ImageView stickFigure;
    ImageView boat;
    RelativeLayout boatAndStickFigure;
    int stepNumber = 0;     //I needed to create this because the animationListener was not working.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        myStartDraggingLsnr=new StartDraggingLsntr();
        myEndDraggingLsntr=new EndDraggingLsntr();

        stickFigure = (ImageView) findViewById(R.id.stickFigure);
        boat = (ImageView) findViewById(R.id.stickFigure);
        boatAndStickFigure = (RelativeLayout) findViewById(R.id.boatAndStickFigure);
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


        //waves = MediaPlayer.create(level2.this, R.raw.oceanwavescrushing);
        //waves.setLooping(true);
        //waves.start();

        //animate();

        animation = AnimationUtils.loadAnimation(level2.this, R.anim.translate);

        animation.setAnimationListener(new Animation.AnimationListener() {
            int a;
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(level2.this, "started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // the second part of the anima
                Toast.makeText(level2.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(level2.this, "repeated", Toast.LENGTH_SHORT).show();
            }
        });
        animation2_1 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_1);
        animation2_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(level2.this, "started", Toast.LENGTH_SHORT).show();
                System.out.println("qwertyuiop");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // the second part of the anima
                Toast.makeText(level2.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(level2.this, "repeated", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void play(View view) {



        try {
            if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right") &&
                    (findViewById(R.id.Btn2).getContentDescription() == null) &&
                    (findViewById(R.id.Btn3).getContentDescription() == null))
            {
                animation2_1 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_1);
                boatAndStickFigure.startAnimation(animation2_1);
                stepNumber = 1;
            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right")
                    && findViewById(R.id.Btn2).getContentDescription().equals("down")) {
                animation = AnimationUtils.loadAnimation(level2.this, R.anim.translate);
                stickFigure.startAnimation(animation);
                stepNumber = 2;

            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right") &&
                    (! findViewById(R.id.Btn2).getContentDescription().equals("down")))
            {
                animation2_1 = AnimationUtils.loadAnimation(level2.this, R.anim.translate1_1);
                stickFigure.startAnimation(animation2_1);
                stepNumber = 1;
            }
            else if (stepNumber == 1 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("up"))
            {
                animation1_2 = AnimationUtils.loadAnimation(level2.this, R.anim.translate1_2);
                stickFigure.startAnimation(animation1_2);
                stepNumber = 2;
            }
        }
        catch (NullPointerException e)
        {
            Toast.makeText(level2.this, "There is nothing in step 1 to be played.", Toast.LENGTH_SHORT).show();
        }

        String description;
        description = "sequence: " + findViewById(R.id.Btn1).getContentDescription() + ", "
                + findViewById(R.id.Btn2).getContentDescription() + ", "
                + findViewById(R.id.Btn3).getContentDescription() + ", "
                + findViewById(R.id.Btn4).getContentDescription();

        if (stepNumber == 2)
        {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();

                        try {
                            Thread.sleep(9000);
                            System.out.println("qwertyuiop");
                            Toast.makeText(level2.this, "go to level 2", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(level2.this, level2.class);
                            //startActivity(intent);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        Toast.makeText(level2.this, "go to level 2", Toast.LENGTH_LONG).show();

                            /*
                            //http://stackoverflow.com/questions/3342651/how-can-i-delay-a-java-program-for-a-few-seconds
                            try {
                                Thread.sleep(1000);                 //1000 milliseconds is one second.
                                Toast.makeText(MainActivity.this, "go to level 2", Toast.LENGTH_LONG).show();
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            */



                    }
                }).start();
            }
            catch (Exception ex) {
                System.out.println(ex);
                Toast.makeText(level2.this, "go to next level", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void exit(View view) {
        Toast.makeText(level2.this, "exit the game", Toast.LENGTH_SHORT).show();
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
