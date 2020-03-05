package com.cognitive.executorrecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DynamicViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_view);

        // Parent layout
        LinearLayout parentLayout = findViewById(R.id.layout);

        //sample commit
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;

        for (int i = 1; i < 101; i++){
            // Add the text layout to the parent layout
            view = layoutInflater.inflate(R.layout.file_item, parentLayout, false);

            // In order to get the view we have to use the new view with text_layout in it

            TextView textView = view.findViewById(R.id.file_name_i);
            if(textView.getParent() != null) {
                ((ViewGroup)textView.getParent()).removeView(textView); // <- fix
            }
            textView.setText("Row " + i);

            // Add the text view to the parent layout
            parentLayout.addView(textView);
        }
    }
}
