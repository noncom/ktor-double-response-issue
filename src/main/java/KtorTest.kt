import io.ktor.application.call
import io.ktor.application.install
import io.ktor.content.*
import io.ktor.features.CallLogging
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.io.File

object KtorTest {

    fun getStaticFilesDir(): File {
        val staticFilesDir = File("src/main/resources/static")
        println("static dir = ${staticFilesDir.absolutePath}")
        //println("static content = ${staticFilesDir.readText()}")
        return staticFilesDir
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val staticFilesDir = getStaticFilesDir()
        val server = embeddedServer(Netty, 8080) {
            install(CallLogging)

            /** case [1] double response is sent on accessing "localhost:8080/" */
            routing {
                get("/") { Hello.respond(call) }
                static {
                    staticBasePackage = "static"
                    defaultResource("index.html")
                    route("static") {
                        files(staticFilesDir)
                    }
                }
            }

            /** case [2] no double response error sent when accessing "localhost:8080/" */
//            routing {
//                get("/") { Hello.respond(call) }
//
//                route("static") {
//                    static {
//                        staticBasePackage = "static"
//                        defaultResource("index.html")
//                        route("static") {
//                            files(staticFilesDir)
//                        }
//                    }
//                }
//            }

            /** case [3] no double response error sent when accessing "localhost:8080/" */
//            routing {
//                get("/") { Hello.respond(call) }
//                static {
//                    staticBasePackage = "static"
//                    default("index.html")
//                    route("static") {
//                        files(staticFilesDir)
//                    }
//                }
//            }
        }
        server.start(wait = true)
    }
}