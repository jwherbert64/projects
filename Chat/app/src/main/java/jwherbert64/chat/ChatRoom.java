package jwherbert64.chat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by James on 31/01/2017.
 */

public class ChatRoom extends AppCompatActivity {

    private Button b_send;
    private EditText et_msg;
    private TextView tv_room, tv_con;

    private String userName, roomName;

    private DatabaseReference root;

    private String tmpKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        b_send = (Button) findViewById(R.id.b_send);
        et_msg = (EditText) findViewById(R.id.et_msg);
        tv_room = (TextView) findViewById(R.id.tv_room);
        tv_con = (TextView) findViewById(R.id.tv_con);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        b_send.setTypeface(typeFaceBold);
        et_msg.setTypeface(typeFace);
        tv_room.setTypeface(typeFace);
        tv_con.setTypeface(typeFace);

        userName = getIntent().getExtras().get("userName").toString();
        roomName = getIntent().getExtras().get("roomName").toString();

        tv_room.setText("Room " + roomName);

        root = FirebaseDatabase.getInstance().getInstance().getReference().child(roomName);

        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                tmpKey = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference messageRoot = root.child(tmpKey);

                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put("name", userName);
                map2.put("msg", et_msg.getText().toString());

                messageRoot.updateChildren(map2);

                et_msg.setText("");
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendCon(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendCon(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String chatMsg, chatUserName;

    private void appendCon(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while(i.hasNext()) {
            chatMsg = (String)((DataSnapshot)i.next()).getValue();
            chatUserName = (String)((DataSnapshot)i.next()).getValue();

            tv_con.append(chatUserName + ": " + chatMsg + " \n");
        }
    }
}
