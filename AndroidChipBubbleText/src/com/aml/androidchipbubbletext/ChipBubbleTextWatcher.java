package com.aml.androidchipbubbletext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.MultiAutoCompleteTextView;

public class ChipBubbleTextWatcher implements TextWatcher {

	private MultiAutoCompleteTextView multiAutoCompleteTextView;
	private Context context;
	private ChipPropery chipPropery;

	private String original = "";
	private int error = 0;

	public ChipBubbleTextWatcher(Context context, MultiAutoCompleteTextView multiAutoCompleteTextView, ChipPropery chipPropery) {
		super();
		this.multiAutoCompleteTextView = multiAutoCompleteTextView;
		this.context = context;
		this.chipPropery = chipPropery;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		String temp = s.toString().replaceAll("\\s+$", "");		
		int backspaceFlag = 0;
		if ((original.length()-1) == temp.length()) {
			backspaceFlag = 1;
		}
		
		if (original.equals(temp)) {
			error = 1;
		} else {
			original = temp;
			error = 0;
		}
		if (temp.length() > 0) {
			if (temp.charAt(temp.length() - 1) == ',') {
				if (error == 0) {
					CharSequence chiptxt = s.toString().replaceAll("\\s+$", "");
					GeneralFunctions.setChips(context, chiptxt, multiAutoCompleteTextView, chipPropery);
					multiAutoCompleteTextView.setSelection(multiAutoCompleteTextView.length());
				}
			} else {
				
				if (backspaceFlag == 1) {
					int lastComma = original.lastIndexOf(",");
					String chipPart = original.substring(0, lastComma + 1);
					String nonChipPart = original.substring(lastComma + 1, original.length());
					multiAutoCompleteTextView.setText(chipPart);
					multiAutoCompleteTextView.append(nonChipPart);
					multiAutoCompleteTextView.setSelection(multiAutoCompleteTextView.length());
				}			
			}
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
    	int flag =0;
    	for(int i = s.length(); i > 0; i--){

            if(s.subSequence(i-1, i).toString().equals("\n")){
                 s.replace(i-1, i, ",");
                 flag = 1;
            }
        
            if (i == 1) {
                if(s.subSequence(i-1, i).toString().equals(",")){
                    s.replace(i-1, i, " ");
                    flag = 1;
               }
			}
            if ((i>1) && (i < (s.length()+1))) {
                if((s.subSequence(i-1, i).toString().equals(",")) && (s.subSequence(i-2, i-1).toString().equals(","))){
                    s.replace(i-1, i, " ");
                    flag = 1;
               }
			}
            
        }
    	if (flag == 1) {
    	     CharSequence chiptxt = s.toString().replaceAll("\\s+$", "");;
    	     if (chiptxt.length()>0) {
    	    	 GeneralFunctions.setChips(context, chiptxt, multiAutoCompleteTextView, chipPropery);
			 }else{
				 multiAutoCompleteTextView.setText(""); 
			 }
        	 multiAutoCompleteTextView.setSelection(multiAutoCompleteTextView.length());
        	 flag =0;
		}
    	
	}
	
}
