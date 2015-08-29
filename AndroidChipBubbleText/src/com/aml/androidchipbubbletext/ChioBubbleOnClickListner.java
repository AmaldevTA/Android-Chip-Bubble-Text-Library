package com.aml.androidchipbubbletext;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MultiAutoCompleteTextView;

public class ChioBubbleOnClickListner implements OnClickListener {

	private MultiAutoCompleteTextView autoCompleteTextView;
	private Context context;
	private ChipPropery chipPropery;
	
	
	
	public ChioBubbleOnClickListner(Context context, MultiAutoCompleteTextView autoCompleteTextView, ChipPropery chipPropery) {
		super();
		this.autoCompleteTextView = autoCompleteTextView;
		this.context = context;
		this.chipPropery = chipPropery;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i = autoCompleteTextView.getSelectionStart();
		String temp = autoCompleteTextView.getText().toString();
	
		if ((i>0) && (i < temp.length())) {
			if (((i+1)>0) && ((i+1) < temp.length())) {
				if (temp.charAt(i+1) == ',') {
					i = i + 1;
				}
			}
		}
		
		
		if ((i>0) && (i < temp.length())) {
			
			int end =i,start=0,flag=0;			
			if (temp.charAt(i) == ',') {
				flag =1;
				
				while ((i >= 0) && (i < temp.length())) {
					i--;
					if (i < 0) {
						start =0;
						break;
					}
					if (temp.charAt(i) == ',') {
						start = i+1;
						break;
					}
				}
				
			}else{
				autoCompleteTextView.setSelection(autoCompleteTextView.length());
			}
			if (flag == 1) {
				
				StringBuilder bs = new StringBuilder(temp);
				bs.replace(start, end+1, "");
				String updatedValue = bs.toString();
				if (updatedValue.length()>0) {
					if (updatedValue.charAt(updatedValue.length()-1) != ',') {
						updatedValue = updatedValue+",";
					}
				}
				CharSequence cs = updatedValue;
				autoCompleteTextView.setText("");
				if (cs.toString().length()>0) {
					GeneralFunctions.setChips(context, cs, autoCompleteTextView, chipPropery);
				}				
			}			
		}else{
			autoCompleteTextView.setSelection(autoCompleteTextView.length());
		}
	}

	
}