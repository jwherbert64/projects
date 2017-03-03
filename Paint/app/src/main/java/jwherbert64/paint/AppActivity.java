package jwherbert64.paint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AppActivity extends AppCompatActivity {

    private CanvasView canvasView;

    PopupWindow popup;
    Point p;

    Boolean menu_open = false;

    int red, green, blue, size, alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_layout);

        Intent loaded = getIntent();

        Bundle extras = loaded.getExtras();

        if(extras != null) {
            String strName = extras.getString("name");

            Toast.makeText(
                    AppActivity.this,
                    "File " + strName + " loaded",
                    Toast.LENGTH_LONG).show();
        }

        canvasView = (CanvasView) findViewById(R.id.canvas);
        canvasView.setColor(Color.BLACK);
        canvasView.setSize(5);

        red = 0;
        green = 0;
        blue = 0;
        size = 5;
        alpha = 100;
    }

    public void clearCanvas(View v) {
        canvasView.clearCanvas();
    }

    public void undoCanvas (View v) {
        canvasView.undoCanvas();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] location = new int[2];
        canvasView.getLocationOnScreen(location);

        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    private void showPopup(final Activity context, Point p) {
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(canvasView.getWidth());
        popup.setHeight(canvasView.getHeight());
        popup.setFocusable(true);

        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x, p.y);

        SeekBar sb_red, sb_green, sb_blue, sb_size, sb_alpha;

        TextView tv_color_label, tv_size_label, tv_alpha_label;
        final TextView tv_red, tv_green, tv_blue, tv_size, tv_alpha;

        final ImageView iv_color;

        TextView tv_save, tv_load;

        ImageView iv_save, iv_load;

        tv_color_label = (TextView) layout.findViewById(R.id.tv_color);
        tv_size_label = (TextView) layout.findViewById(R.id.tv_size_label);
        tv_alpha_label = (TextView) layout.findViewById(R.id.tv_alpha_label);
        tv_red = (TextView) layout.findViewById(R.id.tv_red);
        tv_green = (TextView) layout.findViewById(R.id.tv_green);
        tv_blue = (TextView) layout.findViewById(R.id.tv_blue);
        tv_size = (TextView) layout.findViewById(R.id.tv_size);
        tv_alpha = (TextView) layout.findViewById(R.id.tv_alpha);

        tv_save = (TextView) layout.findViewById(R.id.tv_save);
        tv_load = (TextView) layout.findViewById(R.id.tv_load);
        iv_save = (ImageView) layout.findViewById(R.id.iv_save);
        iv_load = (ImageView) layout.findViewById(R.id.iv_load);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        tv_color_label.setTypeface(typeFace);
        tv_size_label.setTypeface(typeFace);
        tv_alpha_label.setTypeface(typeFace);
        tv_red.setTypeface(typeFace);
        tv_green.setTypeface(typeFace);
        tv_blue.setTypeface(typeFace);
        tv_size.setTypeface(typeFace);
        tv_alpha.setTypeface(typeFace);

        tv_save.setTypeface(typeFace);
        tv_load.setTypeface(typeFace);

        sb_red = (SeekBar) layout.findViewById(R.id.sb_red);
        sb_green = (SeekBar) layout.findViewById(R.id.sb_green);
        sb_blue = (SeekBar) layout.findViewById(R.id.sb_blue);
        sb_size = (SeekBar) layout.findViewById(R.id.sb_size);
        sb_alpha = (SeekBar) layout.findViewById(R.id.sb_alpha);

        sb_red.setMax(255);
        sb_red.setProgress(red);

        sb_green.setMax(255);
        sb_green.setProgress(green);

        sb_blue.setMax(255);
        sb_blue.setProgress(blue);

        sb_size.setMax(255);
        sb_size.setProgress(size);

        sb_alpha.setMax(100);
        sb_alpha.setProgress(alpha);

        iv_color = (ImageView) layout.findViewById(R.id.iv_color);

        iv_color.setBackgroundColor(Color.argb(255, red, green, blue));

        tv_red.setText("Red: " + red);
        tv_green.setText("Green: " + green);
        tv_blue.setText("Blue: " + blue);
        tv_size.setText(size + "dp");
        tv_alpha.setText(alpha + "dp");

        sb_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;

                iv_color.setBackgroundColor(Color.argb(255, red, green, blue));

                tv_red.setText("Red: " + red);

                canvasView.setColor(Color.argb(255, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;

                iv_color.setBackgroundColor(Color.argb(255, red, green, blue));

                tv_green.setText("Green: " + green);

                canvasView.setColor(Color.argb(255, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;

                iv_color.setBackgroundColor(Color.argb(255, red, green, blue));

                tv_blue.setText("Blue: " + blue);

                canvasView.setColor(Color.argb(255, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;

                iv_color.setBackgroundColor(Color.argb(255, red, green, blue));

                tv_blue.setText("Blue: " + blue);

                canvasView.setColor(Color.argb(255, red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                size = progress;

                tv_size.setText(size + "dp");

                canvasView.setSize(size);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress;

                tv_alpha.setText(alpha + "dp");

                canvasView.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        iv_save.setOnClickListener(new View.OnClickListener() {
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

                        File dir = getDir("paint", Context.MODE_PRIVATE);
                        File file = new File(dir, name + ".png");

                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(file);
                            canvasView.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(
                                AppActivity.this,
                                "File " + name + " saved",
                                Toast.LENGTH_LONG).show();
                    }
                };
                fileDialog.setNeutralButton("CREATE", CreateListener);

                fileDialog.show();
            }
        });

        iv_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoadActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.i_options) {
            if(menu_open == true) {
                popup.dismiss();
                menu_open = false;
            }
            if(menu_open == false) {
                showPopup(AppActivity.this, p);
                menu_open = true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


}
