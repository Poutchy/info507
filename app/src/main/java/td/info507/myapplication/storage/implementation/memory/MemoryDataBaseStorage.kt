package td.info507.myapplication.storage.implementation.memory

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import td.info507.myapplication.helper.DataBaseHelper
import td.info507.myapplication.model.Memory
import td.info507.myapplication.storage.utility.DataBaseStorage

class MemoryDataBaseStorage(context: Context) : DataBaseStorage<Memory>(DataBaseHelper(context), "Memory") {
    companion object {
        const val ID = 0
        const val NAME = 1
        const val DATE = 2
    }
    override fun objectToValues(obj: Memory): ContentValues {
        val values = ContentValues()
        values.put(Memory.ID, obj.id)
        values.put(Memory.NAME, obj.name)
        values.put(Memory.DATE, obj.date)
        return values
    }

    override fun cursorToObject(cursor: Cursor): Memory {
        return Memory(cursor.getInt(ID), cursor.getString(NAME), cursor.getString(DATE))
    }
}