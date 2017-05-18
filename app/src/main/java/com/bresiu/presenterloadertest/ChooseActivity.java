package com.bresiu.presenterloadertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bresiu.presenterloadertest.moved.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseActivity extends Activity {
    public static final String OPTION = "option";
    public static final int FIRST_OPTION = 1;
    public static final int SECOND_OPTION = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.button2)
    public void onFirstOptionClick() {
        startOption(FIRST_OPTION);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.button3)
    public void onSecondOptionClick() {
        startOption(SECOND_OPTION);
    }

    private void startOption(int option) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(OPTION, option);
        startActivity(intent);
    }
}
