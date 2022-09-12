package ashutosh.quiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class createQuiz extends AppCompatActivity {

    EditText quizNameETxt;
    database d;

    public void next(View view){
        String name = quizNameETxt.getText().toString();
        if(name.equals("")){
            Toast.makeText(this, "Please write a name", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = this.d.getWritableDatabase();
        ContentValues c = new ContentValues();
        Cursor cursor = d.getData("Select * from quizzes");
        c.put("id", cursor.getCount()+1);
        c.put("name", name);
        long added = db.insert("quizzes",null,c);
        if(added == -1){
            Toast.makeText(this, "Quiz not created!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Quiz created!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), addQuestions.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        quizNameETxt = findViewById(R.id.quizNameETxt);
        d = new database(this);
    }
}