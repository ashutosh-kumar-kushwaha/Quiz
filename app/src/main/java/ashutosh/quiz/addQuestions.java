package ashutosh.quiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class addQuestions extends AppCompatActivity {

    RadioButton aRBtn;
    RadioButton bRBtn;
    RadioButton cRBtn;
    RadioButton dRBtn;
    EditText questionETxt;
    EditText optionAETxt;
    EditText optionBETxt;
    EditText optionCETxt;
    EditText optionDETxt;
    database d;
    String name;

    public void submit(View view){
        String question = questionETxt.getText().toString();
        String answer ="", otherOption1="", otherOption2="", otherOption3="";
        SQLiteDatabase db = this.d.getWritableDatabase();
        ContentValues c = new ContentValues();
        Cursor cursor = d.getData("Select * from questions");
        c.put("id", cursor.getCount()+1);
        c.put("quiz", name);
        c.put("question", question);
        if(aRBtn.isChecked()){
            answer = optionAETxt.getText().toString();
            otherOption1 = optionBETxt.getText().toString();
            otherOption2 = optionCETxt.getText().toString();
            otherOption3 = optionDETxt.getText().toString();
        }
        else if(bRBtn.isChecked()){
            answer = optionBETxt.getText().toString();
            otherOption1 = optionAETxt.getText().toString();
            otherOption2 = optionCETxt.getText().toString();
            otherOption3 = optionDETxt.getText().toString();
        }
        else if(cRBtn.isChecked()){
            answer = optionCETxt.getText().toString();
            otherOption1 = optionAETxt.getText().toString();
            otherOption2 = optionBETxt.getText().toString();
            otherOption3 = optionDETxt.getText().toString();
        }
        else if(dRBtn.isChecked()){
            answer = optionDETxt.getText().toString();
            otherOption1 = optionAETxt.getText().toString();
            otherOption2 = optionBETxt.getText().toString();
            otherOption3 = optionCETxt.getText().toString();
        }


        c.put("answer", answer);
        c.put("otherOption1", otherOption1);
        c.put("otherOption2", otherOption2);
        c.put("otherOption3", otherOption3);
        long added = db.insert("questions",null,c);
        if(added == -1){
            Toast.makeText(this, "Question not added!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkA(View view){
        aRBtn.setChecked(true);
        bRBtn.setChecked(false);
        cRBtn.setChecked(false);
        dRBtn.setChecked(false);
    }

    public void checkB(View view){
        aRBtn.setChecked(false);
        bRBtn.setChecked(true);
        cRBtn.setChecked(false);
        dRBtn.setChecked(false);
    }

    public void checkC(View view){
        aRBtn.setChecked(false);
        bRBtn.setChecked(false);
        cRBtn.setChecked(true);
        dRBtn.setChecked(false);
    }

    public void checkD(View view){
        aRBtn.setChecked(false);
        bRBtn.setChecked(false);
        cRBtn.setChecked(false);
        dRBtn.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.i("info","F9");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);
        aRBtn = (RadioButton) findViewById(R.id.aRBtn);
        bRBtn = (RadioButton) findViewById(R.id.bRBtn);
        cRBtn = (RadioButton) findViewById(R.id.cRBtn);
        dRBtn = (RadioButton) findViewById(R.id.dRBtn);
        questionETxt = (EditText) findViewById(R.id.questionETxt);
        optionAETxt = (EditText) findViewById(R.id.optionAETxt);
        optionBETxt = (EditText) findViewById(R.id.optionBETxt);
        optionCETxt = (EditText) findViewById(R.id.optionCETxt);
        optionDETxt = (EditText) findViewById(R.id.optionDETxt);
        d = new database(this);
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
    }
}