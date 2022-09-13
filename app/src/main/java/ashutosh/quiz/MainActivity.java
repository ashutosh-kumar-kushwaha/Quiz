package ashutosh.quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    public void selectQuizActivity(View view){
        Intent intent = new Intent(getApplicationContext(), selectQuiz.class);
        startActivity(intent);
    }

    public void createQuizActivity(View view){
        Intent intent = new Intent(getApplicationContext(), createQuiz.class);
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