package td.info507.myapplication.model

abstract class Message (
    val id: Int,
    val memory: Int,
    val content: String,
    val type: String
): Drawable {
    companion object {
        const val ID = "id"
        const val MEMORY = "memory"
        const val CONTENT = "content"
        const val TYPE = "type"
    }
}