package ashutosh.quiz;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void quizActivity(View view){
        Intent intent = new Intent(getApplicationContext(), selectQuiz.class);
        startActivity(intent);
    }

    public void addQuizActivity(View view){
        Intent intent = new Intent(getApplicationContext(), addQuiz.class);
        startActivity(intent);
    }

    public void deleteQuiz(View view){
        Intent intent = new Intent(getApplicationContext(), deleteQuiz.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}