package jwherbert64.notepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by James on 23/02/2017.
 */

public class FileActivity  extends AppCompatActivity {

    TextView tv_name;

    Button b_save, b_delete;

    EditText et_content;

    String strName, strContent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_activity_layout);

        tv_name = (TextView) findViewById(R.id.tv_name);
        b_save = (Button) findViewById(R.id.b_save);
        b_delete = (Button) findViewById(R.id.b_delete);
        et_content = (EditText) findViewById(R.id.et_content);

        Intent i = getIntent();

        strName = i.getExtras().getString("name");
        strContent = i.getExtras().getString("content");

        tv_name.setText("File " + strName);
        et_content.setText(strContent);
        et_content.setSelection(et_content.getText().length());

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_name.setTypeface(typeFace);
        b_save.setTypeface(typeFaceBold);
        b_delete.setTypeface(typeFaceBold);
        et_content.setTypeface(typeFace);

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos;
                try {
                    File dir = getDir("notepad", Context.MODE_PRIVATE);

                    File file = new File(dir, strName);

                    fos = new FileOutputStream(file);

                    fos.write(et_content.getText().toString().getBytes());
                    fos.close();

                    Toast.makeText(
                            FileActivity.this,
                            "File " + strName + " saved",
                            Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder fileDialog = new AlertDialog.Builder(FileActivity.this);
                fileDialog.setTitle("Delete File");

                final TextView textView = new TextView(FileActivity.this);
                textView.setText("Are you sure you want to delete this file");
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

                        File dir = getDir("notepad", Context.MODE_PRIVATE);

                        File file = new File(dir, strName);

                        file.delete();

                        Intent i = new Intent(getApplicationContext(), AppActivity.class);
                        i.putExtra("name", strName);
                        startActivity(i);
                    }
                };
                fileDialog.setNeutralButton("DELETE", CreateListener);

                fileDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), AppActivity.class);
        startActivity(i);
    }

}
