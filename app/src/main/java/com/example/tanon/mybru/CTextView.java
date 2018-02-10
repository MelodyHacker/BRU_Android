package com.example.tanon.mybru;

/**
 * Created by korrio on 11/12/2017 AD.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by korrio on 11/12/2017 AD.
 */

public class CTextView extends android.support.v7.widget.AppCompatTextView {
    int mystyle;

    public CTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public CTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public CTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface NormalFont = FontCache.getTypeface("fonts/helvethaix.ttf", context);
        setTypeface(NormalFont);

    }

}
