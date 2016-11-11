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


public class CustomDrawableUser extends View {
    private ShapeDrawable user;

    public CustomDrawableUser(Context context) {
        super(context);

        int x = 300;
        int y = 300;
        int width = 300;
        int height = 450;

        user = new ShapeDrawable(new OvalShape());
        user.getPaint().setColor(0xff74AC23);
        //mDrawable.setBounds(x, y, x + width, y + height);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomDrawableUser(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);

        int x = 300;
        int y = 300;
        int width = 300;
        int height = 450;

        user = new ShapeDrawable(new OvalShape());
        user.getPaint().setColor(0xff74AC23);

        user.getOutline(new Outline());
        user.setAlpha(150);
      //  mDrawable.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas canvas) {
        user.draw(canvas);
    }
}