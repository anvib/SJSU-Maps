package com.example.anvita.lab2;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.graphics.drawable.ShapeDrawable;
import android.content.Context;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.graphics.Color;
import android.graphics.Outline;


public class CustomDrawableView extends View {
    private ShapeDrawable rect;

    public CustomDrawableView(Context context) {
        super(context);

        int x = 300;
        int y = 300;
        int width = 300;
        int height = 450;

        rect = new ShapeDrawable(new RectShape());
        rect.getPaint().setColor(0xff74AC23);
        rect.setBounds(x, y, x + width, y + height);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomDrawableView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);

        int x = 300;
        int y = 300;
        int width = 300;
        int height = 450;

        rect = new ShapeDrawable(new RectShape());
        rect.getPaint().setColor(0xff74AC23);

        rect.getOutline(new Outline());
        rect.setAlpha(150);
        rect.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas canvas) {
        rect.draw(canvas);
    }
}