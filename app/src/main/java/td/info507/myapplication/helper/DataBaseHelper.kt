package td.info507.myapplication.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import td.info507.myapplication.model.Memory
import td.info507.myapplication.model.Message

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, "myapp.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE Memory (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Memory.NAME} STRING," +
                    "${Memory.DATE} DATE" +
                    ")"
        )
        db.execSQL(
                "CREATE TABLE Message (" +
                        "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "${Message.MEMORY} INTEGER," +
                        "${Message.CONTENT} TEXT," +
                        "${Message.TYPE} STRING," +
                        "FOREIGN KEY (${Message.MEMORY}) REFERENCES Memory(${BaseColumns._ID}) ON DELETE CASCADE"+
                        ")"
        )
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}