open class Fun1{
    open fun a(i:Int = 10){
        println(i)
    }
}

class D1:Fun1(){
    override fun a(i: Int) {
        println(i)
    }
}

fun main(args: Array<String>){
    val b:Int? = null;

}

fun lam(i:Int=10,que:()->Unit){

}

inline fun <reified T>testT(){

}


