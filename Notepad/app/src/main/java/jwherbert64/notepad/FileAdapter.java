package jwherbert64.notepad;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends BaseAdapter {
    private Context context;

    List<String> mNames;

    List<String> mContents;

    public void createAdapter(List<String> names, List<String> contents) {
        mNames = names;
        mContents = contents;
    }

    public void addFile(String name, String content) {
        mNames.add(name);
        mContents.add(content);
    }

    public FileAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return mNames.size();
    }

    @Override
    public Object getItem(int position) {
        return mNames.get(position);
    }

    public Object getItemContent(int position) { return mContents.get(position); };

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");

        final float scale = context.getResources().getDisplayMetrics().density;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int la = (int) (150 * scale + 0.5f);
        int lb = (int) (50 * scale + 0.5f);

        ViewGroup.LayoutParams llp = new ViewGroup.LayoutParams(new GridView.LayoutParams(la, la + lb));
        linearLayout.setLayoutParams(llp);
        linearLayout.setPadding(3, 3, 3, 3);
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.text_color));

        TextView tv_name = new TextView(context);
        LinearLayout.LayoutParams nlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lb);
        nlp.setMargins(0, 0, 0, 5);
        tv_name.setLayoutParams(nlp);
        tv_name.setTextSize(16);
        tv_name.setTextColor(ContextCompat.getColor(context,  R.color.text_color_light));
        tv_name.setTypeface(typeFace);
        tv_name.setBackgroundColor(ContextCompat.getColor(context, R.color.background));
        tv_name.setPadding(5, 0, 5, 0);
        tv_name.setGravity(Gravity.CENTER);
        tv_name.setText(mNames.get(position));

        TextView tv_content = new TextView(context);
        ViewGroup.LayoutParams clp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, la);
        tv_content.setLayoutParams(clp);
        tv_content.setTextSize(12);
        tv_content.setTextColor(ContextCompat.getColor(context, R.color.text_color));
        tv_content.setTypeface(typeFace);
        tv_content.setBackgroundColor(ContextCompat.getColor(context, R.color.text_color_light));
        tv_content.setPadding(5, 0, 5, 0);
        tv_content.setGravity(Gravity.CENTER);
        tv_content.setText(mContents.get(position));

        linearLayout.addView(tv_content, 0);
        linearLayout.addView(tv_name, 0);

        return linearLayout;
    }
}