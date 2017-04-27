package jwherbert64.colorgame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class AppActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, b_reset;

    String buttonColor1, buttonColor2, buttonColor3, buttonColor4;

    Random r;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        b_reset = (Button) findViewById(R.id.b_reset);

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        b_reset.setTypeface(typeFaceBold);

        buttonColor1 = initialColor();
        buttonColor2 = initialColor();
        buttonColor3 = initialColor();
        buttonColor4 = initialColor();

        applyColor(button1, buttonColor1);
        applyColor(button2, buttonColor2);
        applyColor(button3, buttonColor3);
        applyColor(button4, buttonColor4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor1 = pickColor(buttonColor1);
                applyColor(button1, buttonColor1);

                buttonColor2 = pickColor(buttonColor2);
                applyColor(button2, buttonColor2);

                checkWin();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor1 = pickColor(buttonColor1);
                applyColor(button1, buttonColor1);

                buttonColor2 = pickColor(buttonColor2);
                applyColor(button2, buttonColor2);

                buttonColor3 = pickColor(buttonColor3);
                applyColor(button3, buttonColor3);

                checkWin();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor2 = pickColor(buttonColor2);
                applyColor(button2, buttonColor2);

                buttonColor3 = pickColor(buttonColor3);
                applyColor(button3, buttonColor3);

                buttonColor4 = pickColor(buttonColor4);
                applyColor(button4, buttonColor4);

                checkWin();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonColor3 = pickColor(buttonColor3);
                applyColor(button3, buttonColor3);

                buttonColor4 = pickColor(buttonColor4);
                applyColor(button4, buttonColor4);

                checkWin();
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

    public void checkWin() {
        score++;

        checkColor("red");
        checkColor("green");
        checkColor("blue");
    }

    public void checkColor(String color) {
        if(buttonColor1 == color
                && buttonColor2 == color
                && buttonColor3 == color
                && buttonColor4 == color) {
            Toast.makeText(AppActivity.this, "You won in " + score + " moves", Toast.LENGTH_SHORT).show();

            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }
    }

    public String initialColor() {
        String color = "";

        r = new Random();
        int tmp = r.nextInt(3) + 1;

        if(tmp == 1) {
            color = "red";
        }
        else if(tmp == 2) {
            color = "green";
        }
        else if(tmp == 3) {
            color = "blue";
        }

        return color;
    }

    public String pickColor(String color) {
        if(color == "red") {
            color = "green";
        }
        else if(color == "green") {
            color = "blue";
        }
        else if(color == "blue") {
            color = "red";
        }
        return color;
    }

    public void applyColor(Button button, String color) {
        if(color.equals("red")) {
            button.setBackgroundColor(Color.parseColor("#FF2E33"));
        }
        else if(color.equals("green")) {
            button.setBackgroundColor(Color.parseColor("#37F62D"));
        }
        else if(color.equals("blue")) {
            button.setBackgroundColor(Color.parseColor("#2BECE5"));
        }
    }
}
