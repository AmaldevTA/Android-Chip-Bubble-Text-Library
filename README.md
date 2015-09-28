Android Chip Bubble Text Library

This is a simple android library for multi autocomplete textview chip text/bubble text with close button.

(It only works for single line MultiAutoCompleteTextView)

![Alt text](https://github.com/AmaldevTA/Android-Chip-Bubble-Text-Library/blob/master/ScreenShot.PNG "screen shot")

sample code

  autoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.MultitextView_sample);
  
  String[] values = {"coutinho","suarez","messi","ronaldo","silva","aguero"};
  
  int threshold = 2; 
  
  ChipBubbleText cp = new ChipBubbleText(MainActivity.this, autoCompleteTextView, values, threshold);
  
  cp.setChipColor("#9999FF");
  
  cp.setChipTextSize(20);
  
  cp.initialize();
