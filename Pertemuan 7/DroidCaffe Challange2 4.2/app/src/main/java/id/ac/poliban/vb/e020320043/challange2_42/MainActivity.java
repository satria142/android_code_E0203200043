package id.ac.poliban.vb.e020320043.challange2_42;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("SATRIA challange 2 4.2");

        // Find the editText_main view and assign it to editText.
        EditText editText = findViewById(R.id.editText_main);

        if (editText != null)
            // If view is found, set the listener for editText.
            editText.setOnEditorActionListener(
                    (textView, actionId, keyEvent) -> {
                        // Start with no event.
                        boolean handled = false;

                        // If the action for the keyboard is defined as
                        // IME_ACTION_SEND (android:imeOptions="actionSend" in the
                        // layout), call the dialNumber method and return true.
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            dialNumber();
                            handled = true;
                        }
                        return handled;
                    });
    }

    /**
     * Creates a string (phoneNum) for the phone number to dial,
     * and performs an implicit intent to dial the number.
     */
    @SuppressLint("QueryPermissionsNeeded")
    private void dialNumber() {
        // Find the editText_main view.
        EditText editText = findViewById(R.id.editText_main);
        String phoneNum = null;

        // If the editText field is not null,
        // concatenate "tel: " with the phone number string.
        if (editText != null) phoneNum = "tel:" + editText.getText().toString();

        // Log the concatenated phone number for dialing.
        Log.d(TAG, "dialNumber: " + phoneNum);

        // Specify the intent.
        Intent intent = new Intent(Intent.ACTION_DIAL);

        // Set the data for the intent as the phone number.
        intent.setData(Uri.parse(phoneNum));

        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "ImplicitIntents: Can't handle this!");
        }
    }
}