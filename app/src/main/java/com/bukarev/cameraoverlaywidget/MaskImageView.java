package com.bukarev.cameraoverlaywidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;

public class MaskImageView extends View {
    private int NO_IMAGE = 0;
    private int image = NO_IMAGE;
    private int mask = NO_IMAGE;
    private int margin = 0;
    private int alpha = 200;

    public MaskImageView(Context context) {
        super(context);
    }

    public MaskImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaskImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setResources(@DrawableRes int image, @DrawableRes int mask) {
        this.image = image;
        this.mask = mask;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (image == NO_IMAGE) return;
        Bitmap bitmap = createFrame(image, mask);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private Bitmap createFrame(@DrawableRes int image, @DrawableRes int mask) {
        Bitmap result = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        RectF rectangle = new RectF(0, 0, getWidth(), getHeight());

        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), image);
        Bitmap maskBitmap = BitmapFactory.decodeResource(getResources(), mask);

        Paint fillPaint = getFillPaint();
        Paint clearPaint = getClearPaint();

        Rect source = getImageSourceSize(imageBitmap);
        Rect dest = getImageDestinationSize(imageBitmap);

        canvas.drawRect(rectangle, fillPaint);
        canvas.drawBitmap(maskBitmap, source, dest, clearPaint);
        canvas.drawBitmap(imageBitmap, source, dest, null);
        return result;
    }

    private Paint getClearPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setAlpha(255);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        return paint;
    }

    private Paint getFillPaint() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setAlpha(alpha);
        return paint;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskImageView);
        try {
            this.image = typedArray.getResourceId(R.styleable.MaskImageView_miv_image, NO_IMAGE);
            this.mask = typedArray.getResourceId(R.styleable.MaskImageView_miv_mask, NO_IMAGE);
            this.margin = (int) typedArray.getDimension(R.styleable.MaskImageView_miv_margin, 0);
            this.alpha = typedArray.getInt(R.styleable.MaskImageView_miv_alpha, 0);
        } finally {
            typedArray.recycle();
        }
    }

    private Rect getImageSourceSize(Bitmap image) {
        return new Rect(0, 0, image.getWidth(), image.getHeight());
    }

    private Rect getImageDestinationSize(Bitmap image) {
        int widthS = getWidth();
        int heightS = getHeight();
        float widthI = image.getWidth();
        float heightI = image.getHeight();
        float aspect = widthI / heightI;
        widthI = (widthS - (this.margin * 2));
        heightI = widthI / aspect;

        int resWX = (int) widthI;
        int resWY = (int) heightI;
        int startX = (int) ((widthS - widthI) / 2);
        int startY = (int) ((heightS - heightI) / 2);

        return new Rect(startX, startY, resWX + startX, resWY + startY);
    }
}
