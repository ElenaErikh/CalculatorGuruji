package com.example.calculatorguruji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Calculator calc;
    private TextView inputTextView;

    private String str;
    private StringBuilder sb;
    private Symbols symbol;

    private final String DEFAULT_VALUE = "ॐ";
    private final int MAX_LENGTH = 5;
    private final int MIN_TEXT_SIZE = 45;
    private final int MAX_TEXT_SIZE = 60;

    private final int[] numberBtnsID = new int[]{R.id.oneBtn, R.id.twoBtn, R.id.threeBtn,
            R.id.fourBtn, R.id.fiveBtn, R.id.sixBtn, R.id.sevenBtn, R.id.eightBtn, R.id.nineBtn,
            R.id.zeroBtn, R.id.doubleZeroBtn};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);
        calc = new Calculator();
        sb = new StringBuilder();

        setBtnListeners();
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
    }

    private void initNumberBtns() {
        for (int n : numberBtnsID) {
            findViewById(n).setOnClickListener(v -> {
                Button btn = (Button) v;

                if (inputTextView.getText().equals(DEFAULT_VALUE)) {
                    inputTextView.setText("");
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
            setTextStr(".");
        });
    }

    private void initPercentBtn() {
        findViewById(R.id.percentBtn).setOnClickListener(v -> {
            updateText(calc.calcPercent(str, inputTextView.getText().toString(), symbol));
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
             updateText(calc.makeNumberNegative(inputTextView.getText().toString()));
        });
    }

    private void initResultBtn() {
        findViewById(R.id.resultBtn).setOnClickListener(v -> {
            updateText(calc.getResult(str, inputTextView.getText().toString(), symbol));
            str = null;
            sb = new StringBuilder();
        });
    }

    private void updateText(String s) {
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