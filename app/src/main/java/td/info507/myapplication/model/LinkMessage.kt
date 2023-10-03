package td.info507.myapplication.model

class LinkMessage(id: Int, memory: Int, content: String) : Message(id, memory, content, "link") {
    override fun draw(): String {
        return content
    }
}