package com.example.calculatorguruji;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ThemeChangerActivity {

    Calculator calc;
    private TextView inputTextView;

    private String str;
    private StringBuilder sb;
    private Symbols symbol;
    private int themeNumber = 0;

    private String DEFAULT_VALUE;

    private final int MAX_LENGTH = 5;
    private final int MIN_TEXT_SIZE = 45;
    private final int MAX_TEXT_SIZE = 60;
    private final int requestCode = 6880;
    protected static final String THEME_NUMBER = "THEME_NUMBER";

    private final int[] numberBtnsID = new int[]{R.id.oneBtn, R.id.twoBtn, R.id.threeBtn,
            R.id.fourBtn, R.id.fiveBtn, R.id.sixBtn, R.id.sevenBtn, R.id.eightBtn, R.id.nineBtn,
            R.id.zeroBtn, R.id.doubleZeroBtn};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calc = new Calculator();
        sb = new StringBuilder();
        DEFAULT_VALUE = getResources().getString(R.string.first_element);

        int themeCode = getIntent().getIntExtra(THEME_NUMBER, 0);
        setTheme(getAppTheme(getCodeStyle(themeCode)));

        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);

        setBtnListeners();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int themeCode = getIntent().getIntExtra(THEME_NUMBER, 0);
        setTheme(getAppTheme(getCodeStyle(themeCode)));
    }

    private void setBtnListeners() {

        initNumberBtns();
        initSymbolBtns(findViewById(R.id.additionBtn), Symbols.sum);
        initSymbolBtns(findViewById(R.id.divBtn), Symbols.div);
        initSymbolBtns(findViewById(R.id.multBtn), Symbols.mul);
        initSymbolBtns(findViewById(R.id.subBtn), Symbols.sub);
        initClearBtn();
        initPlusMinBtn();
        initPercentBtn();
        initDotBtn();
        initResultBtn();
        initSettingsTextView();
    }

    private void initSettingsTextView() {
        TextView settingsTextView = findViewById(R.id.settingsTextView);
        settingsTextView.setOnClickListener(v -> {
            openSettingsActivityForResult();
        });
    }

    private void openSettingsActivityForResult() {
        Intent runSettings = new Intent(this, SettingsActivity.class);
        startActivityForResult(runSettings, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != this.requestCode) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK && data != null) {
            int themeData = data.getIntExtra(THEME_NUMBER, themeNumber);
            setAppTheme(themeData);

            if (themeData == WhiteStyle) {
                inputTextView.setBackgroundResource(R.drawable.om1);

//                Drawable imgW = getResources().getDrawable(R.drawable.om1dark);
//                inputTextView.setCompoundDrawables(imgW, null, null, null);

                //inputTextView.setBackground(getResources().getDrawable(R.drawable.om1));
            } else {
                inputTextView.setBackgroundResource(R.drawable.om1dark);

//                Drawable imgD = getResources().getDrawable(R.drawable.om1dark);
//                inputTextView.setCompoundDrawables(imgD, null, null, null);

                //inputTextView.setBackground(getResources().getDrawable(R.drawable.om1dark));
                //inputTextView.setBackgroundResource(R.drawable.om1dark);
            }

            recreate();
        }
    }

    private void initNumberBtns() {
        for (int n : numberBtnsID) {
            findViewById(n).setOnClickListener(v -> {
                Button btn = (Button) v;
                String s1 = inputTextView.getText().toString();
                String s2 = DEFAULT_VALUE;
                String s3 = "";
                if (s1.equals(s2)) {
                    inputTextView.setText(s3);
                }

                setTextStr((btn.getText().toString()));
            });
        }
    }

    private void initSymbolBtns(View button, Symbols s) {
        button.setOnClickListener(v -> {
            symbol = s;
            str = inputTextView.getText().toString();
            sb = new StringBuilder();
        });
    }

    private void initDotBtn() {
        findViewById(R.id.dotBtn).setOnClickListener(v -> {
            updateText(calc.checkNumsBeforeDot(inputTextView.getText().toString(), DEFAULT_VALUE));
        });
    }

    private void initPercentBtn() {
        findViewById(R.id.percentBtn).setOnClickListener(v -> {
            updateText(calc.calcPercent(str, inputTextView.getText().toString(), symbol, DEFAULT_VALUE));
            sb = new StringBuilder();
        });
    }

    private void initClearBtn() {
        findViewById(R.id.clearBtn).setOnClickListener(v -> {
            inputTextView.setText(DEFAULT_VALUE);
            str = null;
            sb = new StringBuilder();
        });
    }

    private void initPlusMinBtn() {
        findViewById(R.id.plusMinBtn).setOnClickListener(v -> {
            updateText(calc.makeNumberNegative(inputTextView.getText().toString(), DEFAULT_VALUE));
        });
    }

    private void initResultBtn() {
        findViewById(R.id.resultBtn).setOnClickListener(v -> {
            updateText(calc.getResult(str, inputTextView.getText().toString(), symbol, DEFAULT_VALUE));
            str = null;
            sb = new StringBuilder();
        });
    }

    private void updateText(String s) {
        if (s.equals(DEFAULT_VALUE)) {
            return;
        }
        sb = new StringBuilder(s);
        inputTextView.setText(s);
    }

    public void setTextStr(String s) {
        if (sb.length() > MAX_LENGTH) {
            inputTextView.setTextSize(MIN_TEXT_SIZE);
        } else {
            inputTextView.setTextSize(MAX_TEXT_SIZE);
        }
        sb.append(s);
        updateText(sb.toString());
    }



}