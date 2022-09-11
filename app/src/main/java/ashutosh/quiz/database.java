package ashutosh.quiz;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname = "questions.db";

    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table quizzes(id integer primary key, name text)");
        sqLiteDatabase.execSQL("create table questions(id integer primary key, quiz text, question text, answer text, otherOption1 text, otherOption2 text, otherOption3 text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getData(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
