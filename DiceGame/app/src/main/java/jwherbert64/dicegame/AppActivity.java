package jwherbert64.dicegame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AppActivity extends AppCompatActivity {

    ImageView iv_cpu, iv_player;

    TextView tv_cpu, tv_player, tv_vs;

    Button b_reset;

    Random r;

    int cpuPoints = 0, playerPoints = 0;

    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        iv_cpu = (ImageView) findViewById(R.id.iv_cpu);
        iv_player = (ImageView) findViewById(R.id.iv_player);

        tv_cpu = (TextView) findViewById(R.id.tv_cpu);
        tv_player = (TextView) findViewById(R.id.tv_player);
        tv_vs = (TextView) findViewById(R.id.tv_vs);

        b_reset = (Button) findViewById(R.id.b_reset);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        tv_cpu.setTypeface(typeFace);
        tv_player.setTypeface(typeFace);
        tv_vs.setTypeface(typeFace);

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        b_reset.setTypeface(typeFaceBold);

        r = new Random();

        iv_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameActive) {
                    int cpuRoll = r.nextInt(6) + 1;
                    int playerRoll = r.nextInt(6) + 1;

                    setImageCPU(cpuRoll);
                    setImagePlayer(playerRoll);

                    cpuPoints += cpuRoll;

                    playerPoints += playerRoll;

                    tv_cpu.setText("CPU: " + cpuPoints);
                    tv_player.setText("PLAYER: " + playerPoints);

                    Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                    iv_cpu.startAnimation(rotate);
                    iv_player.startAnimation(rotate);

                    if(playerPoints >= 21 && cpuPoints < 21) {
                        Toast.makeText(AppActivity.this, "CPU Wins with " + cpuPoints + " points", Toast.LENGTH_SHORT).show();
                        gameActive = false;
                    }
                    if(cpuPoints >= 21 && playerPoints < 21) {
                        Toast.makeText(AppActivity.this, "Player Wins with " + playerPoints + " points", Toast.LENGTH_SHORT).show();
                        gameActive = false;
                    }
                    if(cpuPoints >= 21 && playerPoints >= 21) {
                        if(playerPoints > cpuPoints) {
                            Toast.makeText(AppActivity.this, "CPU Wins with " + cpuPoints + " points", Toast.LENGTH_SHORT).show();
                        }
                        if(cpuPoints > playerPoints) {
                            Toast.makeText(AppActivity.this, "Player Wins with " + playerPoints + " points", Toast.LENGTH_SHORT).show();
                        }
                        if(playerPoints == cpuPoints) {
                            Toast.makeText(AppActivity.this, "Draw with " + playerPoints + " points", Toast.LENGTH_SHORT).show();
                        }
                        gameActive = false;
                    }
                }
            }
        });

        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AppActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setImageCPU(int roll) {
        switch(roll) {
            case 1:
                iv_cpu.setImageResource(R.drawable.a);
                break;
            case 2:
                iv_cpu.setImageResource(R.drawable.b);
                break;
            case 3:
                iv_cpu.setImageResource(R.drawable.c);
                break;
            case 4:
                iv_cpu.setImageResource(R.drawable.d);
                break;
            case 5:
                iv_cpu.setImageResource(R.drawable.e);
                break;
            case 6:
                iv_cpu.setImageResource(R.drawable.f);
                break;

        }
    }
    public void setImagePlayer(int roll) {
        switch(roll) {
            case 1:
                iv_player.setImageResource(R.drawable.a);
                break;
            case 2:
                iv_player.setImageResource(R.drawable.b);
                break;
            case 3:
                iv_player.setImageResource(R.drawable.c);
                break;
            case 4:
                iv_player.setImageResource(R.drawable.d);
                break;
            case 5:
                iv_player.setImageResource(R.drawable.e);
                break;
            case 6:
                iv_player.setImageResource(R.drawable.f);
                break;

        }
    }
}
