package com.example.friendmap.ui.utils;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.friendmap.R;
public class ColorButton extends Button{
	private int mColorUp=0xFFAAAAAA,mColorDown=0xFF999999;
	public ColorButton(Context context){
		super(context);
		super.setBackgroundColor(mColorUp);
	}
	public ColorButton(Context context,AttributeSet attrs){
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.colorbutton);
        mColorUp = typedArray.getColor(R.styleable.colorbutton_colorbutton_color_up,mColorUp);
        mColorDown=typedArray.getColor(R.styleable.colorbutton_colorbutton_color_down,mColorDown);
        super.setBackgroundColor(mColorUp);
        typedArray.recycle();
	}
	public ColorButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.colorbutton);
        mColorUp = typedArray.getColor(R.styleable.colorbutton_colorbutton_color_up,mColorUp);
        mColorDown=typedArray.getColor(R.styleable.colorbutton_colorbutton_color_down,mColorDown);
        super.setBackgroundColor(mColorUp);
        typedArray.recycle();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		boolean result=super.onTouchEvent(event);
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			super.setBackgroundColor(mColorDown);
		}
		else if (event.getAction()==MotionEvent.ACTION_UP) {
			super.setBackgroundColor(mColorUp);
		}
		return result;
	}
	
}
