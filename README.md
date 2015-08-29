Android Chip Bubble Text Library

This is a simple android library for multi autocomplete textview chip text/bubble text with close button.

![Alt text](https://github.com/AmaldevTA/Android-Chip-Bubble-Text-Library/blob/master/ScreenShot.PNG "screen shot")

sample code

  autoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.MultitextView_sample);
  ChipBubbleText cp = new ChipBubbleText(MainActivity.this, autoCompleteTextView, values, 1);
  cp.setChipColor("#9999FF");
  cp.setChipTextSize(20);
  cp.initialize();
