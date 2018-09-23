package org.techtown.rock_paper_scissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.com_rock) ImageView com_rock;
    @BindView(R.id.com_paper) ImageView com_paper;
    @BindView(R.id.com_scissor) ImageView com_scissor;

    @BindView(R.id.user_rock) ImageView user_rock;
    @BindView(R.id.user_paper) ImageView user_paper;
    @BindView(R.id.user_scissor) ImageView user_scissor;

    @BindView(R.id.rock) ImageView rock;
    @BindView(R.id.paper) ImageView paper;
    @BindView(R.id.scissor) ImageView scissor;

    @BindView(R.id.result) TextView result;
    @BindView(R.id.text) TextView text;
    @BindView(R.id.reset) Button reset;

    @BindView(R.id.com_score) TextView com_score_text;
    @BindView(R.id.user_score) TextView user_score_text;
    int com_score = 0;
    int user_score = 0;

    int current_user;
    int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init_com();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });


        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_rock.setVisibility(View.VISIBLE);
                user_paper.setVisibility(View.INVISIBLE);
                user_scissor.setVisibility(View.INVISIBLE);

                current_user = 0;
                result(randomNum, current_user);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_rock.setVisibility(View.INVISIBLE);
                user_paper.setVisibility(View.VISIBLE);
                user_scissor.setVisibility(View.INVISIBLE);

                current_user = 1;
                result(randomNum, current_user);
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_rock.setVisibility(View.INVISIBLE);
                user_paper.setVisibility(View.INVISIBLE);
                user_scissor.setVisibility(View.VISIBLE);

                current_user = 2;
                result(randomNum, current_user);
            }
        });
    }

    public void result(int randomNum, int current_user) {
        int[][] indexArray = {
                {0, 1, -1},
                {-1, 0, 1},
                {1, -1, 0}
        };
        //0 : 비김, 1 : user가 이김, -1 : user가 짐

        if (indexArray[randomNum][current_user] == 0)
            Toast.makeText(this, "비겼어요", Toast.LENGTH_SHORT).show();
        else if (indexArray[randomNum][current_user] == 1) {
            user_score++;
            Toast.makeText(this, "이겼어요", Toast.LENGTH_SHORT).show();
        }
        else if (indexArray[randomNum][current_user] == -1) {
            com_score++;
            Toast.makeText(this, "졌어요", Toast.LENGTH_SHORT).show();
        }

        show_score();

    }

    public void init_com() {
        Random r = new Random();
        randomNum = r.nextInt(3);

        if (randomNum == 0) {
            com_rock.setVisibility(View.VISIBLE);
            com_paper.setVisibility(View.INVISIBLE);
            com_scissor.setVisibility(View.INVISIBLE);
        } else if (randomNum == 1) {
            com_rock.setVisibility(View.INVISIBLE);
            com_paper.setVisibility(View.VISIBLE);
            com_scissor.setVisibility(View.INVISIBLE);
        } else {
            com_rock.setVisibility(View.INVISIBLE);
            com_paper.setVisibility(View.INVISIBLE);
            com_scissor.setVisibility(View.VISIBLE);
        }
    }

    public void reset() {
        init_com();
        text.setVisibility(View.VISIBLE);
        user_rock.setVisibility(View.INVISIBLE);
        user_paper.setVisibility(View.INVISIBLE);
        user_scissor.setVisibility(View.INVISIBLE);
        result.setText("선택하세요↓");
    }

    public void show_score() {
        com_score_text.setText("컴퓨터 : " + com_score + "승");
        user_score_text.setText("나 : " + user_score + "승");
    }

}
