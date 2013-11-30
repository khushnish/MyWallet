package com.khushnish.mywallet.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class CheckBoxRoboto extends CheckBox {
	public CheckBoxRoboto(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CheckBoxRoboto(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CheckBoxRoboto(Context context) {
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