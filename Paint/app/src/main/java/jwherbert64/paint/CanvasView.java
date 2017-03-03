package jwherbert64.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private List<Path> mPaths;
    private List<Paint> mPaints;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    Context context;
    int color;
    int size;
    int alpha;


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.context = context;

        color = Color.BLACK;
        size = 5;
        alpha = 255;

        mPaths = new ArrayList();

        mPaints = new ArrayList();
    }

    public void setBitmap(Bitmap bitmap) {
        clearCanvas();
        mBitmap = bitmap;
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        mCanvas.drawBitmap(mutableBitmap, 0, 0, null);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    @Override
    protected void onSizeChanged(int currW, int currH, int w, int h) {
        super.onSizeChanged(currW, currH, w, h);
        mBitmap = Bitmap.createBitmap(currW, currH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < mPaths.size(); i++) {
            if (mBitmap == null) {
                mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
            }
            canvas.drawPath(mPaths.get(i), mPaints.get(i));
            canvas.drawBitmap(mBitmap, 0, 0, mPaints.get(i));
        }
    }

    private void startTouch(float x, float y) {
        Path mPath = new Path();
        mPaths.add(mPath);

        Paint mPaint;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(size);
        mPaint.setAlpha(alpha);
        mPaints.add(mPaint);

        mPaths.get(mPaths.size() - 1).moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mX);

        if(dx >= TOLERANCE || dy >= TOLERANCE) {
            mPaths.get(mPaths.size() - 1).quadTo(mX, mY, (x + mX) / 2, (y + mY) /2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        if(mPaths.isEmpty() == false) {
            mPaths.clear();
            mPaints.clear();
            invalidate();
        }
    }

    public void undoCanvas() {
        if(mPaths.isEmpty() == false) {
            mPaths.remove(mPaths.size() - 1);
            mPaints.remove(mPaints.size() - 1);
            invalidate();
        }
    }

    public void setSize(int s) {
        size = s;
    }

    public void setColor(int c) {
        color = c;
    }

    public void setAlpha(int a) { alpha = a; }

    private void upTouch() {
        mPaths.get(mPaths.size() - 1).lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;

        }

        return true;
    }
}
