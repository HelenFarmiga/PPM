package com.example.mati.randomutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by mati on 3/12/15.
 */
public class DrawShapes1 extends Activity {
    private static RandomShapeView mDrawingArea;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_shapes1);
        mDrawingArea = (RandomShapeView) findViewById(R.id.drawing_area);
    }

    /**
     * Handles events for the button. Redraws the ShapeView.
     */
    public void redraw(View clickedButton) {
        mDrawingArea.invalidate();
    }
}