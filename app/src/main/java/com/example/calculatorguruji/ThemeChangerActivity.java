package com.example.calculatorguruji;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThemeChangerActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "LOGIN";
    private static final String AppTheme = "APP_THEME";
    protected static final int WhiteStyle = 0;
    protected static final int DarkStyle = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getAppTheme(R.style.WhiteStyle));
    }

    protected int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    protected int getCodeStyle(int codeStyle){
        return getSharedPreferences(NameSharedPreference, MODE_PRIVATE).getInt(AppTheme, codeStyle);
    }

    protected void setAppTheme(int codeStyle) {

        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        sharedPref.edit()
                .putInt(AppTheme, codeStyle)
                .apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        if (codeStyle == WhiteStyle) {
            return R.style.WhiteStyle;
        } else {
            return R.style.DarkStyle;
        }
    }


}
