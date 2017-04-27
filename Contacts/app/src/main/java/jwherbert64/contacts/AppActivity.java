package jwherbert64.contacts;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    TextView tv_title;
    Button b_create, b_search;
    EditText et_search;

    ListView lv_contacts;

    List<String> strContacts;

    boolean search = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        Intent deleted = getIntent();

        Bundle extras = deleted.getExtras();

        if(extras != null) {
            String name = extras.getString("name");

            Toast.makeText(
                    AppActivity.this,
                    "Contact " + name + " altered",
                    Toast.LENGTH_LONG).show();
        }

        tv_title = (TextView) findViewById(R.id.tv_title);
        b_create = (Button) findViewById(R.id.b_create);
        b_search = (Button) findViewById(R.id.b_search);
        et_search = (EditText) findViewById(R.id.et_search);

        lv_contacts = (ListView) findViewById(R.id.lv_contacts);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFace);
        b_create.setTypeface(typeFaceBold);
        b_search.setTypeface(typeFaceBold);
        et_search.setTypeface(typeFace);

        final DatabaseHelper db = new DatabaseHelper(this);

        List<Contact> contacts = db.getAllContacts();

        strContacts = new ArrayList();

        for(Contact c : contacts) {
            strContacts.add(c.getName());
        }

        final ArrayAdapter<String> adpContacts = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strContacts);

        lv_contacts.setAdapter(adpContacts);

        b_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(i);
            }
        });

        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search == true) {
                    b_search.setBackgroundResource(R.drawable.button);

                    strContacts.clear();

                    List<Contact> contacts = db.getAllContacts();

                    for(Contact c : contacts) {
                        strContacts.add(c.getName());
                    }

                    final ArrayAdapter<String> adpContacts = new ArrayAdapter<String>(AppActivity.this,
                            android.R.layout.simple_list_item_1, strContacts);

                    lv_contacts.setAdapter(adpContacts);

                    search = false;
                }
                else if(search == false) {
                    b_search.setBackgroundResource(R.drawable.button_active);

                    if(et_search.getText().toString() != null) {
                        Contact contact = db.getContact(et_search.getText().toString());

                        strContacts.clear();

                        strContacts.add(contact.name);

                        final ArrayAdapter<String> adpContacts = new ArrayAdapter<String>(AppActivity.this,
                                android.R.layout.simple_list_item_1, strContacts);

                        lv_contacts.setAdapter(adpContacts);
                    }

                    search = true;
                }
            }
        });

        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ContactActivity.class);
                i.putExtra("name", strContacts.get(position));
                startActivity(i);
            }
        });
    }
}