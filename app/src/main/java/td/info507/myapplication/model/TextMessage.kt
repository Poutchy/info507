package td.info507.myapplication.model

class TextMessage(id: Int, memory: Int, content: String) : Message(id, memory, content, "text") {
    override fun draw(): String {
        return content
    }
}