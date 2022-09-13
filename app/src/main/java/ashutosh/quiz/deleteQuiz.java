package ashutosh.quiz;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class deleteQuiz extends Activity {

    SQLiteDatabase db;
    database d;
    EditText quizNameETxt;

    public void delete(View view){
        String name = quizNameETxt.getText().toString();
        SQLiteDatabase db = this.d.getReadableDatabase();
        ContentValues c = new ContentValues();
        Cursor cursor = d.getData("Select * from quizzes where name='"+name+"'");
        if(cursor.getCount()==0){
            Toast.makeText(this, "There isn't any quiz with this name!", Toast.LENGTH_SHORT).show();
            return;
        }

        db.execSQL("delete from quizzes where name='"+ name +"'");
        db.execSQL("delete from questions where quiz='"+ name +"'");
        Toast.makeText(this, name+" deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_quiz);
        d = new database(this);
        db = this.d.getWritableDatabase();
        quizNameETxt = findViewById(R.id.quizNameETxt2);
    }
}