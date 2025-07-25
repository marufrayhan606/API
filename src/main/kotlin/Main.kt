import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT")?.toIntOrNull() ?: 8080) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respondText("🚀 API is live!")
            }

            get("/ping") {
                call.respond(mapOf("message" to "pong"))
            }

            get("/greet/{name}") {
                val name = call.parameters["name"] ?: "stranger"
                call.respond(mapOf("greeting" to "Hello, $name! 👋"))
            }

            get("/info") {
                try {
                    val info = AppInfo("Sample API", "1.0", "Powered by Ktor")
                    call.respond(info)
                } catch (e: Exception) {
                    println("Error in /info route: ${e.message}")
                    call.respondText("Something went wrong 😢", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }.start(wait = true)
}

@Serializable
data class AppInfo(val name: String, val version: String, val description: String)