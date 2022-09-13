package ashutosh.quiz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class addQuestions extends Activity {

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
    ConstraintLayout optionACons, optionBCons, optionCCons, optionDCons;
    Cursor cursor;

    public void submit(View view){
        String question = questionETxt.getText().toString();
        String answer ="", otherOption1="", otherOption2="", otherOption3="";
        SQLiteDatabase db = this.d.getWritableDatabase();
        ContentValues c = new ContentValues();
        cursor = d.getData("Select * from questions");
        if(cursor.getCount()==0){
            c.put("id", 1);
        }
        else{
            cursor = d.getData("Select max(id) from questions");
            cursor.moveToNext();
            c.put("id", Integer.parseInt(cursor.getString(0))+1);
        }
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
        else{
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(question.equals("") || answer.equals("") || otherOption1.equals("") || otherOption2.equals("") || otherOption3.equals("")){
            Toast.makeText(this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
            return;
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
            questionETxt.setText("");
            optionAETxt.setText("");
            optionBETxt.setText("");
            optionCETxt.setText("");
            optionDETxt.setText("");
            aRBtn.setChecked(false);
            bRBtn.setChecked(false);
            cRBtn.setChecked(false);
            dRBtn.setChecked(false);
            optionACons.setBackgroundResource(R.drawable.optionsbg);
            optionBCons.setBackgroundResource(R.drawable.optionsbg);
            optionCCons.setBackgroundResource(R.drawable.optionsbg);
            optionDCons.setBackgroundResource(R.drawable.optionsbg);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        optionACons = findViewById(R.id.optionACons);
        optionBCons = findViewById(R.id.optionBCons);
        optionCCons = findViewById(R.id.optionCCons);
        optionDCons = findViewById(R.id.optionDCons);

    }
}