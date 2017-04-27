package jwherbert64.notepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    GridView gv_files;

    TextView tv_title;

    Button b_create, b_arrange;

    FileAdapter fileAdapter;

    boolean arrange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        Intent deleted = getIntent();

        Bundle extras = deleted.getExtras();

        if(extras != null) {
            String strName = extras.getString("name");

            Toast.makeText(
                    AppActivity.this,
                    "File " + strName + " deleted",
                    Toast.LENGTH_LONG).show();
        }


        gv_files = (GridView) findViewById(R.id.gv_files);
        tv_title = (TextView) findViewById(R.id.tv_title);
        b_create = (Button) findViewById(R.id.b_create);
        b_arrange = (Button) findViewById(R.id.b_arrange);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFace);
        b_create.setTypeface(typeFaceBold);
        b_arrange.setTypeface(typeFaceBold);

        List<String> fileNames = new ArrayList();

        List<String> fileContents = new ArrayList();

        File[] files = getDir("notepad", Context.MODE_PRIVATE).listFiles();

        for(int i = 0; i < files.length; i++)
        {
            fileNames.add(files[i].getName());
        }

        FileInputStream fis;
        try{
            File dir = getDir("notepad", Context.MODE_PRIVATE);

            for(int i = 0; i < fileNames.size(); i++) {
                File file = new File(dir, fileNames.get(i));

                String content = "";

                fis = new FileInputStream(file);

                byte[] input = new byte[fis.available()];
                while (fis.read(input) != -1) {}
                content += new String(input);
                fileContents.add(content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileAdapter = new FileAdapter(this);
        fileAdapter.createAdapter(fileNames, fileContents);

        gv_files.setAdapter(fileAdapter);

        gv_files.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), FileActivity.class);
                i.putExtra("name", fileAdapter.getItem(position).toString());
                i.putExtra("content", fileAdapter.getItemContent(position).toString());
                startActivity(i);
            }
        });

        b_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder fileDialog = new AlertDialog.Builder(AppActivity.this);
                fileDialog.setTitle("Create File");

                final EditText editText = new EditText(AppActivity.this);
                editText.setHint("Enter File Name");
                ViewGroup.LayoutParams textViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(textViewLayoutParams);
                editText.setTextSize(16);
                editText.setTextColor(Color.BLACK);
                editText.setHintTextColor(Color.BLACK);

                fileDialog.setView(editText);

                fileDialog.setPositiveButton("CANCEL", null);

                DialogInterface.OnClickListener CreateListener = new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText.getText().toString();
                        String content = "Write content for file...";

                        FileOutputStream fos;
                        try {
                            File dir = getDir("notepad", Context.MODE_PRIVATE);

                            File file = new File(dir, name);

                            fos = new FileOutputStream(file);

                            fos.write(content.getBytes());
                            fos.close();

                            fileAdapter.addFile(name, content);
                            gv_files.setAdapter(fileAdapter);

                            Toast.makeText(
                                    AppActivity.this,
                                    "File " + name + " created",
                                    Toast.LENGTH_LONG).show();

                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                };
                fileDialog.setNeutralButton("CREATE", CreateListener);

                fileDialog.show();
            }
        });

        b_arrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrange == false) {
                    b_arrange.setBackgroundResource(R.drawable.button_active);
                    arrange = true;
                }
                else if(arrange == true) {
                    b_arrange.setBackgroundResource(R.drawable.button);
                    arrange = false;
                }
            }
        });
    }
}
