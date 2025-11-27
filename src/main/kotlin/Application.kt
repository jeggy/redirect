package fo.damkjaer

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

// http://damkjær.fo/r/olivar-2025 -> https://photos.app.goo.gl/tF4cpW2afpN3fwTA8
fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}

