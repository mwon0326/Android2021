package ac.kr.kpu.game.s2017180010.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox firewallCheckbox;
    private TextView outTextView;
    private EditText editText;
    private TextView editTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firewallCheckbox = findViewById(R.id.checkbox);
        outTextView = findViewById(R.id.OutTextView);
        editText = findViewById(R.id.editText);
        editTextView = findViewById(R.id.editTextView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextView.setText("User into = " + s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onButtonApply(View view) {
        boolean checked = firewallCheckbox.isChecked();
        String text = checked? "Using firewall" : "Not using firewall";
        outTextView.setText(text);

        String user = editText.getText().toString();
        editTextView.setText("User into = " + user);

    }

    public void onCheckFirewall(View view) {
        boolean checked = firewallCheckbox.isChecked();
        String text = checked? "Using firewall" : "Not using firewall";
        outTextView.setText(text);
    }
}