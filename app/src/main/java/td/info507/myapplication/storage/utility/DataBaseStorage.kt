package td.info507.myapplication.storage.utility

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import td.info507.myapplication.model.Memory
import td.info507.myapplication.storage.implementation.memory.MemoryDataBaseStorage

abstract class DataBaseStorage<T>(private val helper: SQLiteOpenHelper, private val table: String): Storage<T> {

    protected abstract fun objectToValues(obj: T): ContentValues
    protected abstract fun cursorToObject(cursor: Cursor): T

    override fun insert(obj: T): Int {
        return helper.writableDatabase.insert(table, null, objectToValues(obj)).toInt()
    }

    override fun size(): Int {
        return helper.readableDatabase.query(table, null, null, null, null, null,null,null).count
    }

    override fun find(id: Int): T? {
        var obj: T? = null
        val cursor = helper.readableDatabase.query(
            table,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf("$id"),
            null,
            null,
            null
        );
        if (cursor.moveToNext()) {
            obj = cursorToObject(cursor)
        }
        cursor.close()
        return obj
    }

    override fun findAllById(id: Int): List<T> {
        var obj: MutableList<T> = mutableListOf()
        val cursor = helper.readableDatabase.rawQuery("SELECT * FROM Message WHERE memory = $id", null)
        while (cursor.moveToNext()) {
            obj.add(cursorToObject(cursor))
        }
        cursor.close()
        return obj as List<T>
    }

    override fun findAll(): List<T> {
        var compt = 0
        var obj: MutableList<T> = mutableListOf()
        val cursor = helper.readableDatabase.query(
            table,
            null,
            null,
            null,
            null,
            null,
            null
        )
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                obj.add(cursorToObject(cursor))
            }
        }
        cursor.close()
        return obj
    }

    override fun update(id: Int, obj: T) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        helper.readableDatabase.delete(
            table,
            "${BaseColumns._ID}",
            arrayOf("$id")
        )
    }
}