package ashutosh.quiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

public class quiz extends Activity {
    SQLiteDatabase db;
    database d;
    String quiz;
    TextView questionTxtVw;
    TextView optionATxtVw;
    TextView optionBTxtVw;
    TextView optionCTxtVw;
    TextView optionDTxtVw;
    Button submitBtn;
    RadioButton aRBtn;
    RadioButton bRBtn;
    RadioButton cRBtn;
    RadioButton dRBtn;
    int score;
    int userChoice=0;
    int ques;
    int ans;
    int totalQues;
    ConstraintLayout optionACons;
    ConstraintLayout optionBCons;
    ConstraintLayout optionCCons;
    ConstraintLayout optionDCons;
    long startTime;
    long timeTaken;
    Cursor cursor;
    int[] questionIDs;
    Random random = new Random();



    Map<Integer, Boolean> mp = new HashMap<Integer, Boolean>();

    public void submit(View view){
        if(aRBtn.isChecked()){
            userChoice = 1;
            aRBtn.setChecked(false);
            optionACons.setBackgroundResource(R.drawable.optionsbg);
        }
        else if(bRBtn.isChecked()){
            userChoice = 2;
            bRBtn.setChecked(false);
            optionBCons.setBackgroundResource(R.drawable.optionsbg);
        }
        else if(cRBtn.isChecked()){
            userChoice = 3;
            cRBtn.setChecked(false);
            optionCCons.setBackgroundResource(R.drawable.optionsbg);
        }
        else if(dRBtn.isChecked()){
            userChoice = 4;
            dRBtn.setChecked(false);
            optionDCons.setBackgroundResource(R.drawable.optionsbg);
        }
        else{
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userChoice == ans){
            score++;
            Toast.makeText(this, "Correct Answer!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
        }

        if(ques < totalQues){
            userChoice = 0;
            ans = generateQuestion();
            ques++;
        }
        else{
            timeTaken = System.currentTimeMillis() - startTime;
            String passedStatus;
            if((float)score/(float)totalQues > 0.33f){
                passedStatus = "Passed!";
            }
            else{
                passedStatus = "Failed!";
            }
            new AlertDialog.Builder(this).setTitle("Quiz Over!").setMessage("Your Score : "+Integer.toString(score)+ "/"+ Integer.toString(totalQues) + "\n\nTime Taken : " + Long.toString(timeTaken/1000) + " seconds\n\n" + passedStatus).setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    quiz.this.finish();
                }
            }).setIcon(android.R.drawable.ic_dialog_info).show();
        }
    }

    public void checkA(View view){
        aRBtn.setChecked(true);
        bRBtn.setChecked(false);
        cRBtn.setChecked(false);
        dRBtn.setChecked(false);
        optionACons.setBackgroundResource(R.drawable.optionsbgselected);
        optionBCons.setBackgroundResource(R.drawable.optionsbg);
        optionCCons.setBackgroundResource(R.drawable.optionsbg);
        optionDCons.setBackgroundResource(R.drawable.optionsbg);
    }

    public void checkB(View view){
        aRBtn.setChecked(false);
        bRBtn.setChecked(true);
        cRBtn.setChecked(false);
        dRBtn.setChecked(false);
        optionBCons.setBackgroundResource(R.drawable.optionsbgselected);
        optionACons.setBackgroundResource(R.drawable.optionsbg);
        optionCCons.setBackgroundResource(R.drawable.optionsbg);
        optionDCons.setBackgroundResource(R.drawable.optionsbg);
    }

    public void checkC(View view){
        aRBtn.setChecked(false);
        bRBtn.setChecked(false);
        cRBtn.setChecked(true);
        dRBtn.setChecked(false);
        optionCCons.setBackgroundResource(R.drawable.optionsbgselected);
        optionACons.setBackgroundResource(R.drawable.optionsbg);
        optionBCons.setBackgroundResource(R.drawable.optionsbg);
        optionDCons.setBackgroundResource(R.drawable.optionsbg);
    }

    public void checkD(View view){
        aRBtn.setChecked(false);
        bRBtn.setChecked(false);
        cRBtn.setChecked(false);
        dRBtn.setChecked(true);
        optionDCons.setBackgroundResource(R.drawable.optionsbgselected);
        optionBCons.setBackgroundResource(R.drawable.optionsbg);
        optionCCons.setBackgroundResource(R.drawable.optionsbg);
        optionACons.setBackgroundResource(R.drawable.optionsbg);
    }

    public void skip(View view){
        if(ques < totalQues){
            userChoice = 0;
            ans = generateQuestion();
            ques++;
        }
        else{
            timeTaken = System.currentTimeMillis() - startTime;
            String passedStatus;
            if((float)score/(float)totalQues > 0.33f){
                passedStatus = "Passed!";
            }
            else{
                passedStatus = "Failed!";
            }
            new AlertDialog.Builder(this).setTitle("Quiz Over!").setMessage("Your Score : "+Integer.toString(score)+ "/"+ Integer.toString(totalQues) + "\n\nTime Taken : " + Long.toString(timeTaken/1000) + " seconds\n\n" + passedStatus).setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    quiz.this.finish();
                }
            }).setIcon(android.R.drawable.ic_dialog_info).show();
        }
        optionACons.setBackgroundResource(R.drawable.optionsbg);
        optionBCons.setBackgroundResource(R.drawable.optionsbg);
        optionCCons.setBackgroundResource(R.drawable.optionsbg);
        optionDCons.setBackgroundResource(R.drawable.optionsbg);
    }

    public int generateQuestion(){
        int questionId;
        do{
            questionId = questionIDs[random.nextInt(totalQues)];
        }while(mp.containsKey(questionId));
        mp.put(questionId, true);
        cursor = d.getData("Select * from questions where id="+Integer.toString(questionId));
        cursor.moveToNext();
        questionTxtVw.setText(cursor.getString(2));
        Map<Integer, Boolean> mp2 = new HashMap<Integer, Boolean>();
        int a = random.nextInt(4) + 3,b,c,d, ans=0;
        optionATxtVw.setText(cursor.getString(a));
        mp2.put(a, true);
        do{
            b = random.nextInt(4) + 3;
        }while(mp2.containsKey(b));
        mp2.put(b, true);
        optionBTxtVw.setText(cursor.getString(b));
        do{
            c = random.nextInt(4) + 3;
        }while(mp2.containsKey(c));
        mp2.put(c, true);
        optionCTxtVw.setText(cursor.getString(c));
        do{
            d = random.nextInt(4) + 3;
        }while(mp2.containsKey(d));
        mp2.put(d, true);
        optionDTxtVw.setText(cursor.getString(d));

        if(a==3){
            ans = 1;
        }
        else if(b==3){
            ans = 2;
        }
        else if(c==3){
            ans = 3;
        }
        else{
            ans = 4;
        }
        return ans;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        d = new database(this);
        db = d.getReadableDatabase();
        Intent intent =  getIntent();
        quiz = intent.getExtras().getString("name");
        cursor = d.getData("Select * from questions where quiz='"+quiz+"'");
        if(cursor.getCount()==0){
            Toast.makeText(this, "There isn't any question in this quiz. Please delete the quiz and create it again with some questions.", Toast.LENGTH_SHORT).show();
            quiz.this.finish();
        }
        totalQues = cursor.getCount();
        questionIDs = new int[totalQues];
        int i=0;
        while(cursor.moveToNext()){
            questionIDs[i++] = Integer.parseInt(cursor.getString(0));
        }


        questionTxtVw = findViewById(R.id.questionTxtVw);
        optionATxtVw = findViewById(R.id.optionATxtVw);
        optionBTxtVw = findViewById(R.id.optionBTxtVw);
        optionCTxtVw = findViewById(R.id.optionCTxtVw);
        optionDTxtVw = findViewById(R.id.optionDTxtVw);
        submitBtn = findViewById(R.id.submitBtn);
        optionACons = findViewById(R.id.optionACons);
        optionBCons = findViewById(R.id.optionBCons);
        optionCCons = findViewById(R.id.optionCCons);
        optionDCons = findViewById(R.id.optionDCons);
        startTime = System.currentTimeMillis();
        aRBtn = findViewById(R.id.aRBtn);
        bRBtn = findViewById(R.id.bRBtn);
        cRBtn = findViewById(R.id.cRBtn);
        dRBtn = findViewById(R.id.dRBtn);
        score = 0;
        ques = 1;
        ans = generateQuestion();

    }
}