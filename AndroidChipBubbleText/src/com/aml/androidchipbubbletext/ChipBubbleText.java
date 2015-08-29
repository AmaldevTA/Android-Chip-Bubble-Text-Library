package com.aml.androidchipbubbletext;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class ChipBubbleText {

	private MultiAutoCompleteTextView multiAutoCompleteTextView;
	private String[] adapterValue;
	private int threshold;
	private Context context;
	private ChipPropery chipPropery;
		
	public ChipBubbleText(Context context, MultiAutoCompleteTextView multiAutoCompleteTextView,
			String[] adapterValue, int threshold) {
		super();
		this.context = context;
		this.multiAutoCompleteTextView = multiAutoCompleteTextView;
		this.adapterValue = adapterValue;
		this.threshold = threshold;
		chipPropery = new ChipPropery();
		chipPropery.setChipColor("#00FFFFFF");
		chipPropery.setChipTextSize(18);
	}
	
	public void setChipColor(String color){
		chipPropery.setChipColor(color);
	}
	
	public void setChipTextSize(float chipTextSize){
		chipPropery.setChipTextSize(chipTextSize);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, adapterValue);
		multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		multiAutoCompleteTextView.setThreshold(threshold);
		multiAutoCompleteTextView.setAdapter(adapter);
		multiAutoCompleteTextView.addTextChangedListener(new ChipBubbleTextWatcher(context, multiAutoCompleteTextView, chipPropery));
		multiAutoCompleteTextView.setOnClickListener(new ChioBubbleOnClickListner(context, multiAutoCompleteTextView, chipPropery));
		multiAutoCompleteTextView.setSingleLine(true);
	}
	
	
}
