package id.ac.e020320043.challenge_23_shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mLocationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("satria");
        setContentView(R.layout.activity_main);
        mLocationEditText = findViewById(R.id.editTextSearchLocation);


        TextView tv1 = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        TextView tv5 = findViewById(R.id.textView5);
        TextView tv6 = findViewById(R.id.textView6);
        TextView tv7 = findViewById(R.id.textView7);
        TextView tv8 = findViewById(R.id.textView8);
        TextView tv9 = findViewById(R.id.textView9);

        if (tv1 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv1.setText(msg);

        }  else  if (tv2 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv2.setText(msg);

        } else if (tv3 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv3.setText(msg);
        }else if (tv4 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv4.setText(msg);
        }else if (tv5 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv5.setText(msg);
        }else if (tv6 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv6.setText(msg);
        }else if (tv7 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv7.setText(msg);
        }else if (tv8 != null) {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv8.setText(msg);
        }else   {
            getIntent();
            String msg = getIntent().getStringExtra("msg");
            tv9.setText(msg);
        }
    }


    public void addItem (View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void searchLoc(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null ) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }
}