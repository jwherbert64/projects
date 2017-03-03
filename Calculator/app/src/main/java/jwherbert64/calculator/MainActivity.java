package jwherbert64.calculator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_num1, et_num2;

    Button b_add, b_subtract, b_multiply, b_divide;

    TextView tv_result;

    double num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);

        b_add = (Button) findViewById(R.id.b_add);
        b_subtract = (Button) findViewById(R.id.b_subtract);
        b_multiply = (Button) findViewById(R.id.b_multiply);
        b_divide = (Button) findViewById(R.id.b_divide);

        tv_result = (TextView) findViewById(R.id.tv_result);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        et_num1.setTypeface(typeFace);
        et_num2.setTypeface(typeFace);
        tv_result.setTypeface(typeFace);
        b_add.setTypeface(typeFaceBold);
        b_subtract.setTypeface(typeFaceBold);
        b_multiply.setTypeface(typeFaceBold);
        b_divide.setTypeface(typeFaceBold);


        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_num1.getText().toString().equals("") &&
                        !et_num2.getText().toString().equals("")) {
                    num1 = Double.parseDouble(et_num1.getText().toString());
                    num2 = Double.parseDouble(et_num2.getText().toString());

                    result = num1 + num2;

                    tv_result.setText("" + result);
                }
            }
        });

        b_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_num1.getText().toString().equals("") &&
                        !et_num2.getText().toString().equals("")) {
                    num1 = Double.parseDouble(et_num1.getText().toString());
                    num2 = Double.parseDouble(et_num2.getText().toString());

                    result = num1 - num2;

                    tv_result.setText("" + result);
                }
            }
        });

        b_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_num1.getText().toString().equals("") &&
                        !et_num2.getText().toString().equals("")) {
                    num1 = Double.parseDouble(et_num1.getText().toString());
                    num2 = Double.parseDouble(et_num2.getText().toString());

                    result = num1 * num2;

                    tv_result.setText("" + result);
                }
            }
        });

        b_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_num1.getText().toString().equals("") &&
                        !et_num2.getText().toString().equals("")) {
                    num1 = Double.parseDouble(et_num1.getText().toString());
                    num2 = Double.parseDouble(et_num2.getText().toString());

                    result = num1 / num2;

                    tv_result.setText("" + result);
                }
            }
        });
    }
}
