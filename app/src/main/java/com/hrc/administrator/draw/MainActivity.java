package com.hrc.administrator.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    class MyView extends View{
        private Paint paint1=new Paint();
        private Paint paint2=new Paint();
        private Paint paint3=new Paint();
        private boolean useCenter=true;
        //用于设置绘制文本的字体大小(5个文本)
        private float[] textSizeArray=new float[]{15,18,21,24,27};

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //更具useCenter来判断当前的状态
            if(useCenter){
                useCenter=false;
                //设置画笔的颜色
                paint1.setColor(Color.RED);
                paint2.setColor(Color.BLACK);
                paint3.setColor(Color.GREEN);
                //设置画笔的宽度
                paint1.setStrokeWidth(6);
                paint2.setStrokeWidth(4);
                paint3.setStrokeWidth(2);
            }else{
                useCenter=true;
                //设置画笔的颜色
                paint1.setColor(Color.BLACK);
                paint2.setColor(Color.RED);
                paint3.setColor(Color.BLUE);
                //设置画笔的宽度
                paint1.setStrokeWidth(2);
                paint2.setStrokeWidth(4);
                paint3.setStrokeWidth(6);
            }
            //每次触摸屏幕时将字体大小倒置，也就是将第一个和第n个元素交换,第二个和第n-1个元素交换,以此类推
            for (int i=0;i<textSizeArray.length/2;i++){
                float textSize=textSizeArray[i];
                textSizeArray[i]=textSizeArray[textSizeArray.length-1-i];
                textSizeArray[textSizeArray.length-1-i]=textSize;
            }
            //刷新view
            invalidate();
            return super.onTouchEvent(event);
        }

        public MyView(Context context){
            super(context);
            setBackgroundColor(Color.WHITE);
            paint1.setColor(Color.BLACK);
            paint1.setStrokeWidth(2);
            paint2.setColor(Color.RED);
            paint2.setStrokeWidth(4);
            paint3.setColor(Color.BLUE);
            paint3.setStrokeWidth(6);
        }
        //扩张画多条直线的方法
        private void drawLinesExt(Canvas canvas,float[] pts,Paint paint){
            float[] points=new float[pts.length*2-4];
            for(int i=0,j=0;i<pts.length;i=i+2){
                points[j++]=pts[i];
                points[j++]=pts[i+1];
                if(i>1&&i<pts.length-2){
                    points[j++]=pts[i];
                    points[j++]=pts[i+1];
                }
            }
            canvas.drawLines(points,paint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //绘制像素点
            canvas.drawPoint(60,120,paint3);
            canvas.drawPoint(70,130,paint3);
            canvas.drawPoints(new float[]{70,140,75,145,75,160},paint2);
            //绘制直线
            canvas.drawLine(10,10,300,10,paint1);
            canvas.drawLine(10,30,300,30,paint2);
            canvas.drawLine(10,50,300,50,paint3);
            //绘制正方形
            drawLinesExt(canvas,new float[]{10,70,120,70,120,170,10,170,10,70},paint2);
            drawLinesExt(canvas,new float[]{25,85,105,85,105,155,25,155,25,85},paint3);
            //绘制三角形
            drawLinesExt(canvas,new float[]{160,70,230,150,170,155,160,170},paint2);
            //设置非填充状态
            paint2.setStyle(Paint.Style.STROKE);
            //画空心圆
            canvas.drawCircle(260,110,40,paint2);
            //设置填充状态
            paint2.setStyle(Paint.Style.FILL);
            //画实心圆
            canvas.drawCircle(260,110,30,paint2);
            RectF rectF=new RectF();
            rectF.left=30;
            rectF.top=190;
            rectF.right=120;
            rectF.bottom=280;
            //画弧
            canvas.drawArc(rectF,0,200,useCenter,paint2);
            rectF.left=140;
            rectF.top=190;
            rectF.right=280;
            rectF.bottom=290;
            paint2.setStyle(Paint.Style.STROKE);
            //画空心椭圆
            canvas.drawArc(rectF,0,360,useCenter,paint2);
            rectF.left=160;
            rectF.top=190;
            rectF.right=260;
            rectF.bottom=290;
            paint3.setStyle(Paint.Style.STROKE);
            //画空心圆
            canvas.drawArc(rectF,0,360,useCenter,paint3);
            float y=0;
            //绘制文本
            for (int i=0;i<textSizeArray.length;i++){
                paint1.setTextSize(textSizeArray[i]);
                paint1.setColor(Color.BLUE);
                //获得文本的宽度可以使用measureText方法
                canvas.drawText("Android (宽度："+paint1.measureText("Android")+")",20,315+y,paint1);
                y+=paint1.getTextSize()+5;
            }
            paint1.setTextSize(22);
            canvas.drawPosText("圆形",new float[]{180,230,210,250},paint1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
