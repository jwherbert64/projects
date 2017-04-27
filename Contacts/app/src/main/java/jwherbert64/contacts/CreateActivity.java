package jwherbert64.contacts;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by James on 4/03/2017.
 */

public class CreateActivity extends AppCompatActivity {

    TextView tv_title;
    Button b_save, b_cancel;

    TextView tv_form, tv_name, tv_phone, tv_email, tv_address;

    EditText et_name, et_phone, et_email, et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity_layout);

        tv_title = (TextView) findViewById(R.id.tv_title);
        b_save = (Button) findViewById(R.id.b_save);
        b_cancel = (Button) findViewById(R.id.b_cancel);

        tv_form = (TextView) findViewById(R.id.tv_form);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_address = (TextView) findViewById(R.id.tv_address);

        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_address = (EditText) findViewById(R.id.et_address);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFace);
        b_save.setTypeface(typeFaceBold);
        b_cancel.setTypeface(typeFaceBold);

        tv_form.setTypeface(typeFace);
        tv_name.setTypeface(typeFace);
        tv_phone.setTypeface(typeFace);
        tv_email.setTypeface(typeFace);
        tv_address.setTypeface(typeFace);

        et_name.setTypeface(typeFace);
        et_phone.setTypeface(typeFace);
        et_email.setTypeface(typeFace);
        et_address.setTypeface(typeFace);

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(CreateActivity.this);
                db.addContact(new Contact(et_name.getText().toString(), et_phone.getText().toString(),
                        et_email.getText().toString(), et_address.getText().toString()));
                Intent i = new Intent(getApplicationContext(), AppActivity.class);
                i.putExtra("name", et_name.getText().toString());
                startActivity(i);
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppActivity.class);
                startActivity(i);
            }
        });
    }
}
