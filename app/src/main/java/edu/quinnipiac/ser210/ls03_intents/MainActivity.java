package edu.quinnipiac.ser210.ls03_intents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final String myKey = "ID_ONE";
    public static  final int REQUEST_CODE = 0;

    public static final int PICK_IMAGE_REQUEST = 1; //request code for the implicit intent
    private ImageView imageView;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //java object for imageView
        imageView = (ImageView) findViewById(R.id.result);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK  && requestCode == REQUEST_CODE){
            Log.i("is data.hasExtra true",Boolean.toString(data.hasExtra("return_key") ));
            if (data.hasExtra("return_key")){
                String result = data.getStringExtra("return_key");
                if (result != null && result.length() >0)
                    Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            }
        }
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            InputStream stream = null;
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                if (imageView == null)
                    Log.e("MainActivity","imageView null");
                else
                    imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    
    }

    public void onClick(View v){
        EditText text = (EditText) findViewById(R.id.inputforintent);
        String value = text.getText().toString();
        Intent intent = new Intent(this,ResultActivity.class);

        intent.putExtra(myKey,value);

        startActivityForResult(intent, REQUEST_CODE);


    }
    public void pickImage(View v){

    }
}
