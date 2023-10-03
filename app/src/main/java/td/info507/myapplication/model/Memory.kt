package td.info507.myapplication.model

class Memory (val id: Int, var lastId:Int = 0){
    var fil = HashMap<Int, Message>()
    companion object {
        const val ID = "id"
        const val LASTID = "lastId"
    }

    fun addMessage(content: String, type: String?): Int{
        var message: Message = TextMessage(this.lastId, id, content)
        if (type?.equals("link") == true) {
            message = LinkMessage(this.lastId, id, content)
        } else if (type?.equals("image") == true) {
            message = ImageMessage(this.lastId, this.id, content)
        }
        fil[lastId] = message
        lastId++
        return lastId - 1
    }
}