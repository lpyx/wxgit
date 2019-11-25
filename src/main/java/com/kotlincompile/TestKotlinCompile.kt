package com.kotlincompile

import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngineManager

fun main(args: Array<String>) {
    val factory = ScriptEngineManager()
    val engine = factory.getEngineByExtension("kts") as KotlinJsr223JvmLocalScriptEngine
   // val bindings = engine.createBindings()
    var a = mutableMapOf<String,String>()
    a.put("d","d")
    var b:Int = 2
    engine.put("a","dd")
   // $(aaa)
    //engine.setBindings(bindings,ScriptContext.ENGINE_SCOPE)
   // engine.compile("a+b")
 //   engine.compile(" bindings[\"a\"]")
    engine.compile("val s:Int = if(true) 1")
  //  println(engine.compile(" val s:Int? = (bindings[\"a\"] as Map<String,String>).get(\"d\")"))
    println(a)

}