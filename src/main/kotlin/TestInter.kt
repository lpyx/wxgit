interface Parent{
    val a:Int;

    fun b()

    fun c() = 1
}

class Child:Parent{
    override val a: Int = 3

    override fun b() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun Child.test(){
    println("fff")
}

open class C
class D:C()
fun C.foo() = "c"
fun D.foo() = "d"
fun foo(c:C){println(c.foo())}

val C.testP
    get() = "dd"

var D.testP
    get() = "ff"
    set(i:String){println("dd")}
fun main(args:Array<String>){
    foo(D())
    println((D() as C) .testP)
}