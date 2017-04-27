package jwherbert64.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by James on 8/03/2017.
 */

public class ContactActivity extends AppCompatActivity {

    TextView tv_title;
    Button b_delete, b_back;

    TextView tv_form, tv_name_title, tv_phone_title, tv_email_title, tv_address_title;

    TextView tv_name, tv_phone, tv_email, tv_address;

    Contact contact;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_activity_layout);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        name = bundle.getString("name");

        tv_title = (TextView) findViewById(R.id.tv_title);
        b_delete = (Button) findViewById(R.id.b_delete);
        b_back = (Button) findViewById(R.id.b_back);

        tv_form = (TextView) findViewById(R.id.tv_form);
        tv_name_title = (TextView) findViewById(R.id.tv_name_title);
        tv_phone_title = (TextView) findViewById(R.id.tv_phone_title);
        tv_email_title = (TextView) findViewById(R.id.tv_email_title);
        tv_address_title = (TextView) findViewById(R.id.tv_address_title);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_address = (TextView) findViewById(R.id.tv_address);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFace);
        b_delete.setTypeface(typeFaceBold);
        b_back.setTypeface(typeFaceBold);

        tv_form.setTypeface(typeFace);
        tv_name_title.setTypeface(typeFace);
        tv_phone_title.setTypeface(typeFace);
        tv_email_title.setTypeface(typeFace);
        tv_address_title.setTypeface(typeFace);

        tv_name.setTypeface(typeFace);
        tv_phone.setTypeface(typeFace);
        tv_email.setTypeface(typeFace);
        tv_address.setTypeface(typeFace);

        final DatabaseHelper db = new DatabaseHelper(this);

        contact = db.getContact(name);

        tv_name.setText(contact.getName());
        tv_phone.setText(contact.getPhone());
        tv_email.setText(contact.getEmail());
        tv_address.setText(contact.getAddress());

        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder fileDialog = new AlertDialog.Builder(ContactActivity.this);
                fileDialog.setTitle("Delete Contact");

                final TextView textView = new TextView(ContactActivity.this);
                textView.setText("Are you sure you want to delete this contact?");
                ViewGroup.LayoutParams textViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                textView.setLayoutParams(textViewLayoutParams);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(16);
                textView.setTextColor(Color.BLACK);

                fileDialog.setView(textView);

                fileDialog.setPositiveButton("CANCEL", null);

                DialogInterface.OnClickListener CreateListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteContact(name);
                        Intent i = new Intent(getApplicationContext(), AppActivity.class);
                        i.putExtra("name", name);
                        startActivity(i);
                    }
                };
                fileDialog.setNeutralButton("DELETE", CreateListener);

                fileDialog.show();
            }
        });

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppActivity.class);
                startActivity(i);
            }
        });
    }
}
