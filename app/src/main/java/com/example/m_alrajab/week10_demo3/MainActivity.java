package com.example.m_alrajab.week10_demo3;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    ImageView stickFigure;
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

        animate();
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
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
        stickFigure.startAnimation(animation);

        //SharedValuesXY.drawingMode="RECT";
        String description;
        description = "sequence: " + findViewById(R.id.Btn1).getContentDescription() + ", "
                + findViewById(R.id.Btn2).getContentDescription() + ", "
                + findViewById(R.id.Btn3).getContentDescription() + ", "
                + findViewById(R.id.Btn4).getContentDescription();
        //I found getContentDescription by typing "get" after the button and then looking through the suggestions.
        Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
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
