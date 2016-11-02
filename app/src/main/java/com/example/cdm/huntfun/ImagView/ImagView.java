package com.example.cdm.huntfun.ImagView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/** 头像的显示设置
 * Created by lian on 2016/10/17.
 */
public class ImagView extends ImageView {

    private Paint mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mRawBitmap;
    private BitmapShader mShader;//设置渲染器
    private Matrix mMatrix = new Matrix();

    public ImagView(Context context) {
        super(context);
    }

    public ImagView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ImagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
                 Bitmap rawBitmap = getBitmap(getDrawable());
                if (rawBitmap != null){
                    int viewWidth = getWidth();
                    int viewHeight = getHeight();
                    int viewMinSize = Math.min(viewWidth, viewHeight);
                    float dstWidth = viewMinSize;
                    float dstHeight = viewMinSize;
                    if (mShader == null || !rawBitmap.equals(mRawBitmap)){
                        mRawBitmap = rawBitmap;
                        mShader = new BitmapShader(mRawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                             }
                    if (mShader != null){
                               mMatrix.setScale(dstWidth / rawBitmap.getWidth(), dstHeight / rawBitmap.getHeight());
                               mShader.setLocalMatrix(mMatrix);
                            }
                         mPaintBitmap.setShader(mShader);
                        float radius = viewMinSize / 2.0f;
                        canvas.drawCircle(radius, radius, radius, mPaintBitmap);
                    } else {
                        super.onDraw(canvas);
                     }
             }
    private Bitmap getBitmap(Drawable drawable) {
          if (drawable instanceof BitmapDrawable) {//判断是否bitmap的封装Drawble
              return ((BitmapDrawable) drawable).getBitmap();
          } else if (drawable instanceof ColorDrawable) {//单色可绘制Drawble
              Rect rect = drawable.getBounds();
              int width = rect.right - rect.left;
              int height = rect.bottom - rect.top;
              int color = ((ColorDrawable) drawable).getColor();
              Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
              Canvas canvas = new Canvas(bitmap);
              canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
              return bitmap;
          } else {
              return null;
          }

    }
}
