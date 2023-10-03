package td.info507.myapplication.storage.implementation.Memory

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import td.info507.myapplication.helper.DataBaseHelper
import td.info507.myapplication.model.Memory
import td.info507.myapplication.storage.utility.DataBaseStorage

class MemoryDataBaseStorage(helper: DataBaseHelper) : DataBaseStorage<Memory>(helper, "Memory") {
    companion object {
        const val ID = 0
    }
    override fun objectToValues(obj: Memory): ContentValues {
        val values = ContentValues()
        values.put(Memory.ID, obj.id)
        return values
    }

    override fun cursorToObject(cursor: Cursor): Memory {
        return Memory(cursor.getInt(MemoryDataBaseStorage.ID))
    }
}