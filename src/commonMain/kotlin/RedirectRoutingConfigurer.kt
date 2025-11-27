import io.ktor.server.application.Application
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import io.ktor.server.routing.RoutingContext
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRedirectRouting() = routing {
    val pairs = getEnvironmentVariables()
        .filter { it.key.startsWith("REDIRECT_") }
        .mapKeys { it.key.replace("REDIRECT_", "", ignoreCase = true).replace("-","_").lowercase() }

    get("r/{path?}", test(pairs))
    get("/{path?}", test(pairs))
}

private fun test(pairs: Map<String, String>): suspend RoutingContext.() -> Unit = test@ {
    val path = call.parameters["path"] ?: return@test call.respondText("No path provided!")
    val normalised = path.lowercase().replace("-", "_")
    val redirectUrl = pairs[normalised] ?: return@test call.respondText("No redirect found for $path")
    call.respondRedirect(redirectUrl)
}