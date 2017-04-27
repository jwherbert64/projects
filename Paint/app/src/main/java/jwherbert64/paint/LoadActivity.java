package jwherbert64.paint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {

    GridView gv_files;

    TextView tv_title;
    Button b_delete, b_arrange;

    FileAdapter fileAdapter;

    boolean delete = false;
    boolean arrange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity_layout);

        gv_files = (GridView) findViewById(R.id.gv_files);
        tv_title = (TextView) findViewById(R.id.tv_title);
        b_delete = (Button) findViewById(R.id.b_delete);
        b_arrange = (Button) findViewById(R.id.b_arrange);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface typeFaceBold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");

        tv_title.setTypeface(typeFace);
        b_delete.setTypeface(typeFaceBold);
        b_arrange.setTypeface(typeFaceBold);

        List<String> fileNames = new ArrayList();

        List<Bitmap> fileBitmaps = new ArrayList();

        File[] files = getDir("paint", Context.MODE_PRIVATE).listFiles();

        for(int i = 0; i < files.length; i++)
        {
            fileNames.add(files[i].getName());

            try {
                File dir = getDir("paint", Context.MODE_PRIVATE);
                File file = new File(dir, fileNames.get(i));
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                fileBitmaps.add(bitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        fileAdapter = new FileAdapter(this);
        fileAdapter.createAdapter(fileNames, fileBitmaps);

        gv_files.setAdapter(fileAdapter);

        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delete == false) {
                    b_delete.setBackgroundResource(R.drawable.button_active);
                    delete = true;
                }
                else if(delete == true) {
                    b_delete.setBackgroundResource(R.drawable.button);
                    delete = false;
                }
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

        gv_files.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(delete == false) {
                    Intent i = new Intent(getApplicationContext(), AppActivity.class);
                    i.putExtra("name", fileAdapter.getItem(position).toString());
                    startActivity(i);
                }
                if(delete == true) {
                    final String strName = fileAdapter.getItem(position).toString();
                    final int pos = position;

                    AlertDialog.Builder fileDialog = new AlertDialog.Builder(LoadActivity.this);
                    fileDialog.setTitle("Delete File");

                    final TextView textView = new TextView(LoadActivity.this);
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

                            File dir = getDir("paint", Context.MODE_PRIVATE);

                            File file = new File(dir, strName);

                            file.delete();

                            fileAdapter.deleteItem(pos);
                            gv_files.setAdapter(fileAdapter);

                            Toast.makeText(
                                    LoadActivity.this,
                                    "File " + strName + " deleted",
                                    Toast.LENGTH_LONG).show();
                        }
                    };
                    fileDialog.setNeutralButton("DELETE", CreateListener);

                    fileDialog.show();

                }
            }
        });
    }
}
