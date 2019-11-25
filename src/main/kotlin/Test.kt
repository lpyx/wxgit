fun foo(){
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            println(it)
        }
    }
    println("end")
}
fun main(args:Array<String>){

    val base = Base();
    base.s()

    (C1() as Base).s()

    (C2() as Base).s()
}

class Person constructor(a:Int){
    val c = a.toString()
    init{
        println(a)
        println("init")
        println(a)
    }
}

open class Base{
    open fun s(){
        println("B1")
    }
}

open class C1:Base(){
    final override fun s(){println("c1")}
}

open class C2:C1(){
}