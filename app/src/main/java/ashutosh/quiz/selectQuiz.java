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

public class selectQuiz extends AppCompatActivity {

    EditText nameETxt;
    database d;

    public void startQuiz(View view){
        String name = nameETxt.getText().toString();
        SQLiteDatabase db = this.d.getReadableDatabase();
        ContentValues c = new ContentValues();
        Cursor cursor = d.getData("Select * from quizzes where name='"+name+"'");
        if(cursor.getCount()==0){
            Toast.makeText(this, "There isn't any quiz with this name! Please create a Quiz.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), quiz.class);
        intent.putExtra("name", nameETxt.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_quiz);
        nameETxt = findViewById(R.id.nameETxt);
        d = new database(this);
    }
}