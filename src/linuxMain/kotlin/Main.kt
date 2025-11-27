import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

fun main() {
    embeddedServer(CIO, 8080) {
        configureRedirectRouting()
    }.start(wait = true)
}
