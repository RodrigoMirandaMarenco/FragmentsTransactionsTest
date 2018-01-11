package com.rodrigomirandamarenco.fragmentstransactionstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open_dialog_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestDialogFragment().show(getSupportFragmentManager(), TestDialogFragment.class.getSimpleName());
            }
        });
    }

}
