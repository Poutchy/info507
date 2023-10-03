package td.info507.myapplication.model

import android.graphics.BitmapFactory
import java.io.File

class ImageMessage(id: Int, memory: Int, content: String) : Message(id, memory, content, "image") {
    override fun draw(): String {
        return content
    }
}