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
import java.util.Observable;
import java.util.Observer;

public class level2 extends AppCompatActivity {

    StartDraggingLsntr myStartDraggingLsnr;
    EndDraggingLsntr myEndDraggingLsntr;
    Button rectBtn, ovalBtn, btn1, btn2;
    MediaPlayer waves;
    Animation animation;
    Animation animation2_1;
    Animation animation2_12;
    Animation animation2_123;
    Animation animation2_2;
    Animation animation2_23;
    Animation animation2_3;
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
                    (findViewById(R.id.Btn2).getContentDescription() == null))
            {
                animation2_1 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_1);
                boatAndStickFigure.startAnimation(animation2_1);
                stepNumber = 1;
            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right") &&
                    (! findViewById(R.id.Btn2).getContentDescription().equals("down")))
            {
                animation2_1 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_12);
                stickFigure.startAnimation(animation2_1);
                stepNumber = 1;
            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right")
                    && findViewById(R.id.Btn2).getContentDescription().equals("down")
                    && findViewById(R.id.Btn3).getContentDescription() == null) {
                animation2_12 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_12);
                boatAndStickFigure.startAnimation(animation2_12);
                stepNumber = 2;
            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right")
                    && findViewById(R.id.Btn2).getContentDescription().equals("down")
                    && (! findViewById(R.id.Btn3).getContentDescription().equals("right"))) {
                animation2_123 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_123);
                stickFigure.startAnimation(animation2_12);  //
                stepNumber = 2;
            }
            else if (stepNumber == 0 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right")
                    && findViewById(R.id.Btn2).getContentDescription().equals("down")
                    && findViewById(R.id.Btn3).getContentDescription().equals("right")) {
                animation2_123 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_123);
                boatAndStickFigure.startAnimation(animation2_123);
                stepNumber = 3;
                //This does not appear the right way, so I tried to do it a different way.
                //step3wait();
            }
            else if (stepNumber == 1 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("down") &&
                    findViewById(R.id.Btn2).getContentDescription().equals(null))
            {
                animation2_2 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_2);
                boatAndStickFigure.startAnimation(animation2_2);
                stepNumber = 2;
            }
            else if (stepNumber == 1 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("down") &&
                    (! findViewById(R.id.Btn2).getContentDescription().equals("right")))
            {
                animation2_2 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_2);
                boatAndStickFigure.startAnimation(animation2_2);
                stepNumber = 2;
            }
            else if (stepNumber == 1 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("down") &&
                    (findViewById(R.id.Btn2).getContentDescription().equals("right")))
            {
                animation2_23 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_23);
                boatAndStickFigure.startAnimation(animation2_23);
                stepNumber = 3;
            }
            else if (stepNumber == 2 &&
                    findViewById(R.id.Btn1).getContentDescription().equals("right"))
            {
                animation2_3 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_3);
                boatAndStickFigure.startAnimation(animation2_3);
                stepNumber = 3;
            }
            else
            {
                Toast.makeText(level2.this, "No", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(level2.this, description, Toast.LENGTH_SHORT).show();

        if (stepNumber == 3)
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
                            Intent intent = new Intent(level2.this, level3.class);
                            startActivity(intent);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        Toast.makeText(level2.this, "go to level 2", Toast.LENGTH_LONG).show();
                    }
                }).start();
            }
            catch (Exception ex) {
                System.out.println(ex);
                Toast.makeText(level2.this, "go to next level", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void step3wait()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    Thread.sleep(6000);
                    step3();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void step3()
    {
        animation2_3 = AnimationUtils.loadAnimation(level2.this, R.anim.translate2_3);
        boatAndStickFigure.startAnimation(animation2_3);
        stepNumber = 3;
    }
    //http://stackoverflow.com/questions/14457711/android-listening-for-variable-changes
    //http://stackoverflow.com/questions/7157123/in-android-how-do-i-take-an-action-whenever-a-variable-changes/7157281#7157281
    public interface step3Listener
    {
        public void onVariableChanged (boolean step3);

    }
    //http://stackoverflow.com/questions/15433855/how-to-create-change-listener-for-variable
    public class Step3Listener implements Observer
    {
        @Override
        public void update(Observable observable, Object o) {

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
