package ashutosh.quiz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class createQuiz extends Activity {

    EditText quizNameETxt;
    database d;
    Cursor cursor;

    public void next(View view){
        String name = quizNameETxt.getText().toString();
        if(name.equals("")){
            Toast.makeText(this, "Please write a name", Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = this.d.getWritableDatabase();
        ContentValues c = new ContentValues();
        cursor = d.getData("Select * from quizzes");
        if(cursor.getCount()==0){
            c.put("id", +1);
        }
        else{
            cursor = d.getData("Select * from quizzes where name='"+name+"'");
            if(cursor.getCount()!=0){
                Toast.makeText(this, "There is already a quiz with this name. Please type an another name.", Toast.LENGTH_SHORT).show();
                return;
            }
            cursor = d.getData("Select max(id) from quizzes");
            cursor.moveToNext();
            c.put("id", Integer.parseInt(cursor.getString(0))+1);
        }
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