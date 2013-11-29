package com.khushnish.mywallet.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class RadioButtonRoboto extends RadioButton {
	public RadioButtonRoboto(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public RadioButtonRoboto(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RadioButtonRoboto(Context context) {
		super(context);
		init();
	}

	private void init() {
		if (!isInEditMode()) {
			final Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"fonts/Roboto-Regular.ttf");
			setTypeface(tf);
		}
	}
}