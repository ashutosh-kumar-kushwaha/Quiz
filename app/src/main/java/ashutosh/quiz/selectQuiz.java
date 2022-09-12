package ashutosh.quiz;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
    Cursor cursor;

    public void startQuiz(View view){
        String name = nameETxt.getText().toString();
        SQLiteDatabase db = this.d.getReadableDatabase();
        ContentValues c = new ContentValues();
        cursor = d.getData("Select * from quizzes where name='"+name+"'");
        if(cursor.getCount()==0){
            Toast.makeText(this, "There isn't any quiz with this name!", Toast.LENGTH_SHORT).show();
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
        cursor = d.getData("Select * from quizzes");
        if(cursor.getCount()==0){
            new AlertDialog.Builder(this).setTitle("No quiz found!").setMessage("There isn't any quiz in the database.\nPlease create a quiz first").setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    selectQuiz.this.finish();
                }
            }).setIcon(android.R.drawable.ic_dialog_alert).show();
        }
    }
}