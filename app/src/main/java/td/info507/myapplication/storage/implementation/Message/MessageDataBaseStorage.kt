package td.info507.myapplication.storage.implementation.Message

import android.content.ContentValues
import android.database.Cursor
import td.info507.myapplication.helper.DataBaseHelper
import td.info507.myapplication.model.ImageMessage
import td.info507.myapplication.model.LinkMessage
import td.info507.myapplication.model.Memory
import td.info507.myapplication.model.Message
import td.info507.myapplication.model.TextMessage
import td.info507.myapplication.storage.utility.DataBaseStorage

class MessageDataBaseStorage(helper: DataBaseHelper) : DataBaseStorage<Message>(helper, "Message") {
    companion object {
        const val ID = 0
        const val MEMORY = 1
        const val CONTENT = 2
        const val TYPE = 3
    }
    override fun objectToValues(obj: Message): ContentValues {
        val values = ContentValues()
        values.put(Message.ID, obj.id)
        values.put(Message.MEMORY, obj.memory)
        values.put(Message.CONTENT, obj.content)
        values.put(Message.TYPE, obj.type)
        return values
    }

    override fun cursorToObject(cursor: Cursor): Message {
        val type = cursor.getString(MessageDataBaseStorage.TYPE)
        if (type == "image") {
            return ImageMessage(
                cursor.getInt(MessageDataBaseStorage.ID),
                cursor.getInt(MessageDataBaseStorage.MEMORY),
                cursor.getString(MessageDataBaseStorage.CONTENT))
        } else if (type =="link"){
            return LinkMessage(
                cursor.getInt(MessageDataBaseStorage.ID),
                cursor.getInt(MessageDataBaseStorage.MEMORY),
                cursor.getString(MessageDataBaseStorage.CONTENT))
        } else {
            return TextMessage(
                cursor.getInt(MessageDataBaseStorage.ID),
                cursor.getInt(MessageDataBaseStorage.MEMORY),
                cursor.getString(MessageDataBaseStorage.CONTENT))
        }
    }
}