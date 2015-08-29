package com.aml.androidchipbubbletext;

import android.content.Context;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MultiAutoCompleteTextView;

public class ChipBubbleOnTouchListner implements OnTouchListener {

	private MultiAutoCompleteTextView autoCompleteTextView;
	//private Context context;

	public ChipBubbleOnTouchListner(MultiAutoCompleteTextView autoCompleteTextView, Context context) {
		super();
		this.autoCompleteTextView = autoCompleteTextView;
		//this.context = context;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		Layout layout = ((MultiAutoCompleteTextView) v).getLayout();
		float x = event.getX() + autoCompleteTextView.getScrollX();
		int offset = layout.getOffsetForHorizontal(0, x);
		System.out.println(offset);
	
		return true;
	}

}
