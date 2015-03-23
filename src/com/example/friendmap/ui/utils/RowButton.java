package com.example.friendmap.ui.utils;

import com.example.friendmap.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class RowButton extends LinearLayout{
	private int mColorUp=0xFFAAAAAA,mColorDown=0xFF999999;
	public RowButton(Context context){
		this(context, null, 0);
	}
	public RowButton(Context context,AttributeSet attrs){
		this(context, attrs,0);
	}
	public RowButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.rowbutton);
		mColorUp=getResources().getColor(R.color.rowbutton_default_up);
		mColorDown=getResources().getColor(R.color.rowbutton_default_down);
		
        mColorUp = typedArray.getColor(R.styleable.rowbutton_rowbutton_color_up,mColorUp);
        mColorDown=typedArray.getColor(R.styleable.rowbutton_rowbutton_color_down,mColorDown);
        
        Drawable drawable=typedArray.getDrawable(R.styleable.rowbutton_rowbutton_image_src);
        
        
        
        typedArray.recycle();
        
        inflate(context, R.layout.button_row, this);
        
        if(drawable!=null){
        	ImageView imageView=(ImageView)findViewById(R.id.rowbutton_imageview);
        	imageView.setImageDrawable(drawable);
        }
        
        Button button=(Button)findViewById(R.id.rowbutton_button);
        button.setBackgroundColor(mColorUp);
        button.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					view.setBackgroundColor(mColorDown);
				}
				else {
					view.setBackgroundColor(mColorUp);
				}
				return false;
			}
		});
	}
	public void setOnBtnClickListener(OnClickListener onClickListener){
		Button button=(Button) findViewById(R.id.rowbutton_button);
		button.setOnClickListener(onClickListener);
	}
}
