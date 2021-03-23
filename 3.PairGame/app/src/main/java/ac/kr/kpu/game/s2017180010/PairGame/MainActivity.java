package ac.kr.kpu.game.s2017180010.PairGame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] buttonIds = {
            R.id.card00, R.id.card01, R.id.card02, R.id.card03,
            R.id.card10, R.id.card11, R.id.card12, R.id.card13,
            R.id.card20, R.id.card21, R.id.card22, R.id.card23,
            R.id.card30, R.id.card31, R.id.card32, R.id.card33,
            R.id.card40, R.id.card41, R.id.card42, R.id.card43
    };

    /*private static final int[] cardIds = {
            R.mipmap.card_2c, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_3d,
            R.mipmap.card_4h, R.mipmap.card_4h, R.mipmap.card_5s, R.mipmap.card_5s,
            R.mipmap.card_as, R.mipmap.card_as, R.mipmap.card_jc, R.mipmap.card_jc,
            R.mipmap.card_kd, R.mipmap.card_kd, R.mipmap.card_qh, R.mipmap.card_qh
    };*/

    private static final int[] cardIds = {
            R.mipmap.flower_01, R.mipmap.flower_01, R.mipmap.flower_02, R.mipmap.flower_02,
            R.mipmap.flower_03, R.mipmap.flower_03, R.mipmap.flower_04, R.mipmap.flower_04,
            R.mipmap.flower_05, R.mipmap.flower_05, R.mipmap.flower_06, R.mipmap.flower_06,
            R.mipmap.flower_07, R.mipmap.flower_07, R.mipmap.flower_08, R.mipmap.flower_08,
            R.mipmap.flower_09, R.mipmap.flower_09, R.mipmap.flower_10, R.mipmap.flower_10
    };

    private ImageButton preButton;
    private int pairCardNum = buttonIds.length;
    private TextView scoreTextView;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);

        startGame();
    }

    public void onButtonCard(View view) {
        if (view == preButton)
        {
            int color = getResources().getColor(R.color.purple_700);
            scoreTextView.setTextColor(color);
            return;
        }

        int preCard = 0;
        scoreTextView.setTextColor(getResources().getColor(R.color.gray));

        if (preButton != null)
        {
            preButton.setImageResource(R.mipmap.back_image);
            preCard = (Integer)preButton.getTag();
        }

        int buttonIndex = getButonIndex(view.getId());
        Log.d(TAG, "onButtonCard() has been called. ID -" + view.getId() + "buttonIndex - " + buttonIndex);

        int card = cardIds[buttonIndex];
        ImageButton imageButton = (ImageButton)view;
        imageButton.setImageResource(card);

        if (card == preCard)
        {
            imageButton.setVisibility(view.INVISIBLE);
            preButton.setVisibility(view.INVISIBLE);
            preButton = null;
            pairCardNum -= 2;

            if (pairCardNum == 0)
                askRestart();
            return;
        }

        if (preButton != null) {
            setScore(score += 1);
        }
        preButton = imageButton;
    }

    private int getButonIndex(int resId){
        for (int i = 0; i < buttonIds.length; i++)
        {
            if (buttonIds[i] == resId)
                return i;
        }
        return -1;
    }

    public void onButtonRestart(View view) {
        askRestart();
    }

    private void askRestart()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.restart_dialog_title);
        builder.setMessage(R.string.restart_dialog_message);
        builder.setPositiveButton(R.string.common_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startGame();
            }
        });
        builder.setNegativeButton(R.string.common_no, null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void startGame()
    {
        Random random = new Random();

        for (int i = 0; i < cardIds.length; i++)
        {
            int ri = random.nextInt(cardIds.length);
            int t = cardIds[i];
            cardIds[i] = cardIds[ri];
            cardIds[ri] = t;
        }

        for (int i = 0; i < buttonIds.length; i++)
        {
            ImageButton b = findViewById(buttonIds[i]);
            b.setTag(cardIds[i]);
            b.setVisibility(View.VISIBLE);
            b.setImageResource(R.mipmap.back_image);
        }

        setScore(0);
        preButton = null;
    }

    public void setScore(int score)
    {
        this.score = score;
        scoreTextView.setText("Flips : " + score);
    }
}