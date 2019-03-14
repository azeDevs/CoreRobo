package robo.models

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.javacord.api.entity.user.User

class JsonPrinter
constructor(private val om: ObjectMapper) {

    @Throws(JsonProcessingException::class)
    fun printUser(u: User) {
        println(om.writeValueAsString(u))
    }

}