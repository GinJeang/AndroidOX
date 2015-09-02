package myxo.ss.saint.myxo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button b[][], refresh;
    private String turn;
    private int board[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = new Button[3][3];
        initButton();
        board = new int[3][3];
        initBoard();
        turn = "X";
    }

    protected void initButton(){
        b[0][0] = (Button) findViewById(R.id.button1);
        b[0][1] = (Button) findViewById(R.id.button2);
        b[0][2] = (Button) findViewById(R.id.button3);
        b[1][0] = (Button) findViewById(R.id.button4);
        b[1][1] = (Button) findViewById(R.id.button5);
        b[1][2] = (Button) findViewById(R.id.button6);
        b[2][0] = (Button) findViewById(R.id.button7);
        b[2][1] = (Button) findViewById(R.id.button8);
        b[2][2] = (Button) findViewById(R.id.button9);
        refresh = (Button) findViewById(R.id.refresh);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                final int inI = i, inJ = j;
                b[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        b[inI][inJ].setText(turn);
                        b[inI][inJ].setClickable(false);
                        b[inI][inJ].setBackgroundColor(0xFF6D9486);
                        if(turn.equals("X")){
                            board[inI][inJ] = 1;
                        }
                        else{
                            board[inI][inJ] = 2;
                        }
                        swap();
                        check();
                    }
                });
            }
        }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
    }

    protected void initBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = 0;
            }
        }
    }

    protected void swap(){
        if(turn.equals("X")){
            turn = "O";
        }
        else{
            turn = "X";
        }
    }

    protected void restart(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                b[i][j].setText("TAP");
                b[i][j].setClickable(true);
                b[i][j].setBackgroundColor(0xFF1E2528);
                board[i][j] = 0;
            }
        }
    }

    protected void disableButton(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                b[i][j].setClickable(false);
            }
        }
    }

    protected void check(){
        for(int i = 0; i < 3; i++){
            if((board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i])||
                    (board[i][0] !=0 && board[i][0] == board[i][1] && board[i][0] == board[i][2])){
                disableButton();
            }
            if((board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) ||
                    (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0])){
                disableButton();
            }
        }
    }


}