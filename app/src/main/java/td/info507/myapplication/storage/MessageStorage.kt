package td.info507.myapplication.storage

import android.content.Context
import td.info507.myapplication.model.Message
import td.info507.myapplication.storage.implementation.message.MessageDataBaseStorage
import td.info507.myapplication.storage.utility.Storage

object MessageStorage {
    fun get(context: Context): Storage<Message> {
        lateinit var storage: Storage<Message>
        storage = MessageDataBaseStorage(context)
        return storage
    }
}