package com.example.calculatorguruji;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends ThemeChangerActivity {

    private static final int WhiteStyle = 0;
    private static final int DarkStyle = 1;
    protected static int codeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);

        initBackButton();

        initThemeChooser();
    }

    private void initBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.THEME_NUMBER, codeNumber);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioBtnWhiteTheme), WhiteStyle);
        initRadioButton(findViewById(R.id.radioBtnDarkTheme), DarkStyle);

        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(WhiteStyle))).setChecked(true);

    }

    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(v -> {
            setAppTheme(codeStyle);
            codeNumber = codeStyle;
            recreate();
        });
    }


}


