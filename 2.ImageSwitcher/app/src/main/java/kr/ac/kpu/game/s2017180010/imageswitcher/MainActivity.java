package kr.ac.kpu.game.s2017180010.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private ImageView mainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        mainTextView.setText("Program started");

        mainImageView = findViewById(R.id.mainImageView);


    }

    public void onButtonPrevious(View view) {
        mainTextView.setText("2 / 5");
        mainImageView.setImageResource(R.mipmap.eagle);
    }

    public void onButtonNext(View view) {
        mainTextView.setText("3 / 5");
        mainImageView.setImageResource(R.mipmap.giraffe);
    }
}