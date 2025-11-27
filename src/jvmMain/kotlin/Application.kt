import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(CIO, 8080) {
        configureRedirectRouting()
    }.start(wait = true)
}

