import ScriptExample.evalIt
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class MyScript {
    val engine = ScriptEngineManager().getEngineByExtension("kts")
        ?: throw ScriptException("Unrecognized script extension: .kts")

    init {
        setIdeaIoUseFallback()
    }
}

object ScriptExample {
    val script2 =
        """
            println("I am in script2")
            "script2 was completed."
        """.trimIndent()

    fun evalIt(script: String) = MyScript().engine.eval(script) as String
}

fun main() {
    val script1 =
        """
            import ScriptExample.evalIt
            import ScriptExample.script2
        
            println("I am in script1")
            evalIt(script2)
        """.trimIndent()

    println()
    println(evalIt(script1))
}


