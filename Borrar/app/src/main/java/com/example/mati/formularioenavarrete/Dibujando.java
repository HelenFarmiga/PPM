package com.example.mati.formularioenavarrete;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class Dibujando extends View {
    float base=150;
     float altura=200;
     float radio=50;
     float mHorizontal;
     float mVertical;

    public Dibujando(Context context){
        super(context);
    }

    public Dibujando(Context context, AttributeSet atributos) {
        super(context, atributos);
    }

        protected void onDraw(Canvas lienzo) {

            lienzo.drawColor(Color.WHITE);
            Paint miPincel= new Paint();

            mHorizontal=lienzo.getWidth()/2;
            mVertical=lienzo.getHeight()/2;

            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setColor(Color.MAGENTA);
            miPincel.setStrokeWidth(5);
            //Dibujando la cabeza
            lienzo.drawCircle(mHorizontal, mVertical / 2, radio, miPincel);


            //cambiamos el color del pincel para el vestido
            miPincel.setColor(Color.RED);

            lienzo.drawLine(mHorizontal,
                    (mVertical / 2) + radio,
                    mHorizontal + (base / 2),
                    (mVertical / 4) + radio + altura,
                    miPincel);

            lienzo.drawLine(mHorizontal + (base / 2),
                    (mVertical / 4) + radio + altura,
                    mHorizontal - (base / 2),
                    (mVertical / 4) + radio + altura,
                    miPincel);

            lienzo.drawLine(mHorizontal - (base / 2),
                    (mVertical / 4) + radio + altura,
                    mHorizontal,
                    (mVertical / 2) + radio,
                    miPincel);

            //Dibujando los brazos
            miPincel.setColor(Color.MAGENTA);
            lienzo.drawLine(mHorizontal - (base / 2),
                    (mVertical / 7) + radio + altura,
                    mHorizontal,
                    (mVertical / 2) + radio,
                    miPincel);

            lienzo.drawLine(mHorizontal,
                    (mVertical / 2) + radio,
                    mHorizontal + (base / 2),
                    (mVertical / 7) + radio + altura,
                    miPincel);

            //Dibujando las piernas
            lienzo.drawLine(mHorizontal-(base/4),
                    (mVertical/4)+radio+altura+1,
                    mHorizontal-(base/4),
                    lienzo.getHeight()-350,
                    miPincel);

            lienzo.drawLine(mHorizontal+(base/4),
                    (mVertical/4)+radio+altura+1,
                    mHorizontal+(base/4),
                    lienzo.getHeight()-350,
                    miPincel);

        }
}
