package td.info507.myapplication.storage

import android.content.Context
import td.info507.myapplication.model.Memory
import td.info507.myapplication.storage.implementation.memory.MemoryDataBaseStorage
import td.info507.myapplication.storage.utility.Storage

object MemoryStorage {
    fun get(context: Context): Storage<Memory> {
        lateinit var storage: Storage<Memory>
        storage = MemoryDataBaseStorage(context)
        return storage
    }
}