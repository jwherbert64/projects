package jwherbert64.cupsgame;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView iv_left, iv_middle, iv_right;

    Button b_reset;

    List<Integer> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_left = (ImageView) findViewById(R.id.left);
        iv_middle = (ImageView) findViewById(R.id.middle);
        iv_right = (ImageView) findViewById(R.id.right);

        b_reset = (Button) findViewById(R.id.reset);

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        b_reset.setTypeface(typeFaceBold);

        cards = new ArrayList();
        cards.add(0);
        cards.add(1);
        cards.add(2);

        Collections.shuffle(cards);

        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(cards);

                iv_left.setImageResource(R.drawable.cardback);
                iv_middle.setImageResource(R.drawable.cardback);
                iv_right.setImageResource(R.drawable.cardback);

                Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
                Animation anim_middle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);
                Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);

                iv_left.startAnimation(anim_left);
                iv_middle.startAnimation(anim_middle);
                iv_right.startAnimation(anim_right);
            }
        });

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage();
                if(cards.get(0) == 2) {
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage();
                if(cards.get(1) == 2) {
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignImage();
                if(cards.get(2) == 2) {
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void assignImage() {
        if(cards.get(0) == 0) {
            iv_left.setImageResource(R.drawable.queen);
        }
        else if(cards.get(0) == 1) {
            iv_left.setImageResource(R.drawable.king);
        }
        else if(cards.get(0) == 2) {
            iv_left.setImageResource(R.drawable.ace);
        }

        if(cards.get(1) == 0) {
            iv_middle.setImageResource(R.drawable.queen);
        }
        else if(cards.get(1) == 1) {
            iv_middle.setImageResource(R.drawable.king);
        }
        else if(cards.get(1) == 2) {
            iv_middle.setImageResource(R.drawable.ace);
        }

        if(cards.get(2) == 0) {
            iv_right.setImageResource(R.drawable.queen);
        }
        else if(cards.get(2) == 1) {
            iv_right.setImageResource(R.drawable.king);
        }
        else if(cards.get(2) == 2) {
            iv_right.setImageResource(R.drawable.ace);
        }
    }
}