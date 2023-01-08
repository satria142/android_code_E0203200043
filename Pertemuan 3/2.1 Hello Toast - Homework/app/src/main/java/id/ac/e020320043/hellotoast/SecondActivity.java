package id.ac.e020320043.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    TextView helloViewView;
    TextView counterViewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        helloViewView = (TextView) findViewById(R.id.textToast);
        counterViewView = (TextView) findViewById(R.id.textCount);
        Intent intent = getIntent();
        helloViewView.setText("Hello!");
        counterViewView.setText
                (Integer.toString(intent.getIntExtra(MainActivity.COUNT, 0)));
    }

}