package kotlinweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import org.springframework.data.jpa.repository.Query
import javax.persistence.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom
import javax.persistence.Entity

@SpringBootApplication
class KotlinWebApplication

fun main(args: Array<String>) { //main function
    runApplication<KotlinWebApplication>(*args)
}

@RestController
class KotlinWeb {

    @GetMapping("/hello") //Creates new extension for website
    fun helloWorld(): String {
        return "hello world" //returns hello world on /hello page
    }

    @GetMapping("/number") //Creates new extension for website
    fun helloUser(@RequestParam("number") number : Float ): String { //RequestParam requests parameter in url to display on page
        return "bye $number" //Displays "bye" and whatever number is provided
    }

    @GetMapping("/time") //Creates new extension for website
     fun dateTime(): LocalDateTime {
        return LocalDateTime.now() //Returns current time
    }
}

@RestController
class MessageResource(val service: MessageService) {
    @GetMapping("/") //Get method
    fun index(): List<Message> = service.findMessages() //uses our findMessages method defined below

    @PostMapping("/", consumes = ["application/json"]) //post method. specifies that we should receive json.
    fun post(@RequestBody message: Message) : String { //requestbody converts http request to domain type/body. (grabs stuff from http)
        service.post(message)
        return "OK" //returns OK when we POST stuff (for confirmation)
    }
}

@Entity
@Table(name = "MESSAGES") //creates path to a database table
data class Message( //data class substitutes for getters and setters. used to hold data/state.
    @Id //creates path to a database id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatically generates primary key value
    val id: String? = null, val text: String) //Entering Id is not required. If it is not entered, @generatedvalue generates a random id which is used in the database.

interface MessageRepository : CrudRepository<Message, String> {
    @Query("select m from Message m") //sql query to grab messages
    fun findMessages(): List<Message> //the findMessages() function wil execute the sql query.
}

@Service
class MessageService(val db: MessageRepository) {
    fun findMessages(): List<Message> = db.findMessages() //gets all messages from the database.

    fun post(message: Message) { //writes Message object to the database
        db.save(message)
    }
}