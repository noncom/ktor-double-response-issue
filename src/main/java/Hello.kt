import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.html.respondHtml
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title

object Hello {

    suspend fun respond(call: ApplicationCall) {
        call.respondHtml {
            head {
                title { +"Hello" }
            }
            body {
                p { +"Hello world!" }
            }
        }
    }

}