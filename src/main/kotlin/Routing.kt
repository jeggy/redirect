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
        val impl: suspend RoutingContext.() -> Unit = impl@ {
            val path = call.parameters["path"] ?: return@impl call.respondText("No path provided!")
            val normalised = path.lowercase().replace("-", "_")
            val redirectUrl = pairs[normalised] ?: return@impl call.respondText("No redirect found for $path")
            call.respondRedirect(redirectUrl)
        }
        get("r/{path?}", impl)
        get("/{path?}", impl)
    }
}
