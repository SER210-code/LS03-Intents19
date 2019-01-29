package edu.quinnipiac.ser210.ls03_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    public void finish() {

        Intent intent = new Intent();
        EditText returnVal = (EditText) findViewById(R.id.inputforintent);

        intent.putExtra("return_key","OK");

         setResult(RESULT_OK,intent);
        super.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String value = getIntent().getStringExtra(MainActivity.myKey);
        TextView returnVal = (TextView) findViewById(R.id.returnValue);
        returnVal.setText(value);
    }
}
