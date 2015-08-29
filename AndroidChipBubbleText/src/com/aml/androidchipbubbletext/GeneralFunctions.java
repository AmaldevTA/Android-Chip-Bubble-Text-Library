package com.aml.androidchipbubbletext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class GeneralFunctions {
	

	public static void setChips(final Context context, CharSequence typedText,
			final MultiAutoCompleteTextView mutiView, ChipPropery chipPropery) {

			SpannableStringBuilder ssb = new SpannableStringBuilder(typedText);
			String chips[] = typedText.toString().split(",");
			int x = 0;
						
			for (String c : chips) {

				if (c.length() > 2) {
					
					BitmapDrawable bmpDrawableText = getBitmapDrawable(context, c, false, 1, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableText), x, x + c.length() - 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					
					BitmapDrawable bmpDrawableDummy1 = getBitmapDrawable(context, " ", false, 2, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableDummy1), x + c.length() - 2, x + c.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					BitmapDrawable bmpDrawableClose = getBitmapDrawable(context, "", true, 3, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableClose), x + c.length() - 1, x + c.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					
					BitmapDrawable bmpDrawableDummy2 = getBitmapDrawable(context, " ", false, 4, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableDummy2), x + c.length(), x + c.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

				}else if (c.length() > 1) {
					
					BitmapDrawable bmpDrawableText = getBitmapDrawable(context, c, false, 1, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableText), x, x + c.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					BitmapDrawable bmpDrawableClose = getBitmapDrawable(context, "", true, 3, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableClose), x + c.length() - 1, x + c.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					
					BitmapDrawable bmpDrawableDummy = getBitmapDrawable(context, " ", false, 4, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableDummy), x + c.length(), x + c.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

				}else{
					
					BitmapDrawable bmpDrawableText = getBitmapDrawable(context, c, true, 1, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableText), x, x + c.length() , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					
					BitmapDrawable bmpDrawableDummy = getBitmapDrawable(context, " ", false, 4, chipPropery);
					ssb.setSpan(new ImageSpan(bmpDrawableDummy), x + c.length(), x + c.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

				}
											
				mutiView.setMovementMethod(LinkMovementMethod.getInstance());
				x = x + c.length() + 1;
			}
			mutiView.setText(ssb);
	
	}
	
	private static BitmapDrawable getBitmapDrawable(Context context, String str, boolean close, int count, ChipPropery chipPropery){
		TextView textView = new TextView(context);
		textView.setBackgroundColor(Color.parseColor(chipPropery.getChipColor()));
		textView.setTextSize(chipPropery.getChipTextSize());
		textView.setText(str); 
		textView.setLayoutParams(new ViewGroup.LayoutParams(
		        ViewGroup.LayoutParams.WRAP_CONTENT,
		        ViewGroup.LayoutParams.WRAP_CONTENT));
		textView.setPadding(0, 1, 0, 5);
		if ((count == 1) && close) {
			textView.setPadding(10, 1, 0, 5);
			textView.setBackgroundResource(R.drawable.textview_background_all_corners_round);
			GradientDrawable drawable = (GradientDrawable) textView.getBackground();
			drawable.setColor(Color.parseColor(chipPropery.getChipColor()));
		}else if (count == 1) {
			textView.setPadding(10, 1, 0, 5);
			textView.setBackgroundResource(R.drawable.textview_background_left_corners_round);
			GradientDrawable drawable = (GradientDrawable) textView.getBackground();
			drawable.setColor(Color.parseColor(chipPropery.getChipColor()));
		}else if (close) {
			textView.setPadding(0, 1, 0, 5);
			textView.setBackgroundResource(R.drawable.textview_background_right_corners_round);
			GradientDrawable drawable = (GradientDrawable) textView.getBackground();
			drawable.setColor(Color.parseColor(chipPropery.getChipColor()));
		}else if (count == 4) {
			textView.setPadding(0, 1, 0, 5);
			textView.setBackgroundColor(Color.parseColor("#00FFFFFF"));
		} else {
			textView.setPadding(0, 1, 0, 5);
		}
		
		int image = android.R.drawable.presence_offline;
		if (close) {
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, image, 0);
		}else{
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		}

		int spec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		textView.measure(spec, spec);
		textView.layout(0, 0, textView.getMeasuredWidth(),textView.getMeasuredHeight());
		Bitmap b = Bitmap.createBitmap(textView.getWidth(),textView.getHeight(), Bitmap.Config.ARGB_8888);
		
		
		Canvas canvas = new Canvas(b);
		canvas.translate(-textView.getScrollX(), -textView.getScrollY());
		textView.draw(canvas);
		textView.setDrawingCacheEnabled(true);
		
		
		Bitmap cacheBmp = textView.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		textView.destroyDrawingCache(); 
		
		BitmapDrawable bmpDrawable = new BitmapDrawable(context.getResources(), viewBmp);
		bmpDrawable.setBounds(0, 0, bmpDrawable.getIntrinsicWidth(),bmpDrawable.getIntrinsicHeight());
		
		return bmpDrawable;

	}
	
}
