package com.khushnish.mywallet.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRoboto extends TextView {
	public TextViewRoboto(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TextViewRoboto(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TextViewRoboto(Context context) {
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