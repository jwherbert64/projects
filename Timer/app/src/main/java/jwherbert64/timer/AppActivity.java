package jwherbert64.timer;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {

    EditText et_time;

    Button b_start;

    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        et_time = (EditText) findViewById(R.id.et_time);

        b_start = (Button) findViewById(R.id.b_start);

        tv_time = (TextView) findViewById(R.id.tv_time);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        et_time.setTypeface(typeFace);
        b_start.setTypeface(typeFace);
        tv_time.setTypeface(typeFace);

        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        b_start.setTypeface(typeFaceBold);

        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = et_time.getText().toString();
                if(!time.equalsIgnoreCase("")) {
                    int seconds = Integer.valueOf(time);
                    CountDownTimer countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
                        @Override
                        public void onTick(long millis) {
                            tv_time.setText("" + (int)(millis / 1000));
                        }

                        @Override
                        public void onFinish() {
                            tv_time.setText("Finished");
                        }
                    }.start();
                }
            }
        });
    }
}
