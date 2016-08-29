package com.imd030.sgr.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.imd030.sgr.R;

/**
 * Created by netou on 27/08/2016.
 */
public class PrincipalActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar =  getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#339999")));
    }

}
