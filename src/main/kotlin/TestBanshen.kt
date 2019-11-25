class Banshen{
    val b = 2;
      object a{
        fun a(){println()}


    }
}

open class P1{
    fun f1() = "1"
}



sealed class Parent1(){

}

class T1<in T:Number>{
    fun proT(t:T){println(t.toDouble())}
}

fun main(args:Array<String>){
   val t1 = T1<Number>();
    val t2:T1<Double> = t1;

}