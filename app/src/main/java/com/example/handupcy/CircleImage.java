package com.example.handupcy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by 67698 on 2018/5/26.
 */

public class CircleImage extends android.support.v7.widget.AppCompatImageView {
    private Paint paint1=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap1;
    private Paint paint2=new Paint(Paint.ANTI_ALIAS_FLAG);
    private BitmapShader bitmapShader;//着色器，后面用图片着色
    float  radius;
    float wradius;
    float hradius;
    int kuangcolor;
    float kuangwidth;
    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.CircleImage);
        kuangcolor=a.getColor(R.styleable.CircleImage_KuangColor, Color.BLACK);
        kuangwidth=a.getDimension(R.styleable.CircleImage_KuangWidth,15);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null)
        {
            return;
        }
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(20);
        myshader();
        paint2.setColor(kuangcolor);
        paint2.setStrokeWidth(kuangwidth);
        canvas.drawCircle(wradius,hradius,radius,paint1);
        canvas.drawCircle(wradius,hradius,radius,paint2);
    }

    private void myshader() {
        Matrix matrix = new Matrix();//矩阵用于后面缩放图片
        Bitmap rawBitmap = drawableToBitamp(getDrawable());
        if (rawBitmap != null) {
            final int paddingleft=getPaddingLeft();
            final int paddingright=getPaddingRight();
            final int paddingbottom=getPaddingBottom();
            final int paddingtop=getPaddingTop();
            float viewWidth = getWidth()-paddingleft-paddingright-2*kuangwidth;
            float viewHeight = getHeight()-paddingbottom-paddingtop-2*kuangwidth;
            float min = Math.min(viewWidth, viewHeight);
            float dstWidth = min;
            float dstHeight = min;
            if (bitmapShader == null || !rawBitmap.equals(bitmap1)) {
                bitmap1 = rawBitmap;
                bitmapShader = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (bitmapShader != null) {
                matrix.setScale(dstWidth / rawBitmap.getWidth(), dstHeight/rawBitmap.getHeight());
                bitmapShader.setLocalMatrix(matrix);
                //setScale和Local一起使用用于缩放图片
            }
            paint1.setShader(bitmapShader);//设置shader
            radius=min/2;
            wradius = (min+paddingleft) / 2;
            hradius=(min+paddingtop)/2;
        }
    }
    private Bitmap drawableToBitamp(Drawable drawable)
    {//将drawable 转换为bitamp
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
}



