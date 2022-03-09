package valenzuela.manuel.chatbot.utils

import valenzuela.manuel.chatbot.utils.Constans.OPEN_GOOGLE
import valenzuela.manuel.chatbot.utils.Constans.OPEN_SEARCH
import java.lang.Exception
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {
    fun basicResponse(_message: String): String{
        val randmon = (0..2).random()
        val message = _message.toLowerCase()

        return when{
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin  and it landed on $result"
            }

            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try{
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"
                }catch (e: Exception){
                    "Sorry, i can't solve that"
                }
            }

            message.contains("hello") -> {
                when (randmon) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error"
                }
            }

            message.contains("how are you?") -> {
                when (randmon) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            message.contains("time") && message.contains("?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            message.contains("open") &&  message.contains("google") -> {
                OPEN_GOOGLE
            }

            message.contains("search") -> {
                OPEN_SEARCH
            }

            else -> {
                when (randmon) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "idk"
                    else -> "error"
                }
            }
        }
    }
}