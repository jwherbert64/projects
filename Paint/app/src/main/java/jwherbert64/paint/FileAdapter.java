package jwherbert64.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by James on 3/03/2017.
 */

public class FileAdapter extends BaseAdapter {
    private Context context;

    List<String> mNames;

    List<Bitmap> mBitmaps;

    public void createAdapter(List<String> names, List<Bitmap> bitmap) {
        mNames = names;
        mBitmaps = bitmap;
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

    public Object getItemContent(int position) { return mBitmaps.get(position); };

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void deleteItem(int position) {
        mNames.remove(position);
        mBitmaps.remove(position);
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
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.black));

        TextView tv_name = new TextView(context);
        LinearLayout.LayoutParams nlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, lb);
        nlp.setMargins(0, 0, 0, 5);
        tv_name.setLayoutParams(nlp);
        tv_name.setTextSize(16);
        tv_name.setTextColor(ContextCompat.getColor(context,  R.color.white ));
        tv_name.setTypeface(typeFace);
        tv_name.setBackgroundResource(R.drawable.button);
        tv_name.setPadding(5, 0, 5, 0);
        tv_name.setGravity(Gravity.CENTER);
        tv_name.setText(mNames.get(position));

        ImageView iv_bitmap = new ImageView(context);
        ViewGroup.LayoutParams blp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, la);
        iv_bitmap.setLayoutParams(blp);
        iv_bitmap.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        iv_bitmap.setImageBitmap(mBitmaps.get(position));

        linearLayout.addView(iv_bitmap, 0);
        linearLayout.addView(tv_name, 0);

        return linearLayout;
    }
}
