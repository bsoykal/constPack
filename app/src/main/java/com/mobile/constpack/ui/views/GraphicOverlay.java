package com.mobile.constpack.ui.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.vision.barcode.Barcode;
import com.mobile.constpack.R;

public class GraphicOverlay extends View {
    private final Object mLock = new Object();
    private Barcode barcode;
    private Paint paint;
    private int previewWidth, previewHeight;

    public GraphicOverlay(Context context) {
        super(context, null);
    }

    public GraphicOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setStrokeWidth(6.0f);
    }

    public void setBarcode(Barcode barcode, boolean isValid) {
        this.barcode = barcode;
        paint.setColor(ContextCompat.getColor(getContext(), isValid ? R.color.blue100 : R.color.red));
        postInvalidate();
    }

    public void setCameraInfo(int previewWidth, int previewHeight) {
        synchronized (mLock) {
            this.previewWidth = previewWidth;
            this.previewHeight = previewHeight;
        }
        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (barcode == null)
            return;
        synchronized (mLock) {

            float widthScaleFactor = (float) canvas.getWidth() / (float) previewWidth;
            float heightScaleFactor = (float) canvas.getHeight() / (float) previewHeight;

            Point[] arrPoints = barcode.cornerPoints;
            for (Point arrPoint : arrPoints) {
                arrPoint.x *= widthScaleFactor;
                arrPoint.y *= heightScaleFactor;
            }
            canvas.drawLine(arrPoints[0].x, arrPoints[0].y, arrPoints[1].x, arrPoints[1].y, paint);
            canvas.drawLine(arrPoints[1].x, arrPoints[1].y, arrPoints[2].x, arrPoints[2].y, paint);
            canvas.drawLine(arrPoints[2].x, arrPoints[2].y, arrPoints[3].x, arrPoints[3].y, paint);
            canvas.drawLine(arrPoints[3].x, arrPoints[3].y, arrPoints[0].x, arrPoints[0].y, paint);
        }
    }
}

