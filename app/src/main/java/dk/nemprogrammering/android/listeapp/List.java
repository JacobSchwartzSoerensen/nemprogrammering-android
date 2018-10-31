package dk.nemprogrammering.android.listeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class List extends AppCompatActivity {

    private static final String TAG = "List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String test = savedInstanceState.getString("test");

        Log.d(TAG, "Value: " + test);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("test", "Dette er en test");
        super.onSaveInstanceState(savedInstanceState);
    }
}
