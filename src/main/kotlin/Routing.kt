package fo.damkjaer

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val pairs = System
        .getenv()
        .filter { it.key.startsWith("REDIRECT_") }
        .mapKeys { it.key.replace("REDIRECT_", "", ignoreCase = true).replace("-","_").lowercase() }


    routing {
        get("r/{path?}") {
            val path = call.parameters["path"] ?: return@get call.respondText("No path provided!")
            val normalised = path.lowercase().replace("-", "_")
            val redirectUrl = pairs[normalised] ?: return@get call.respondText("No redirect found for $path")
            call.respondRedirect(redirectUrl)
        }
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
