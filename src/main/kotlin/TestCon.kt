//import java.io.Serializable
//import java.math.BigDecimal
//import java.sql.Timestamp
//
//data class TestCon(var i:Long = 0L, var dd:String, var s:String ="ff", var s1:String="dd", var d4:String = "ffg")
//
//
//class ScanUseRecordDO(
//        var id: Long = 0,
//        var orgId: Long,
//        var projectCode: String,
//        var taskId: Long,
//        var materialLotId: Long,
//        var materialCode: String?,
//        var materialName: String,
//        var unit: String?,
//        var qrcode: String,
//        var amount: BigDecimal,
//        var amountUsed: BigDecimal,
//        var amountRemaining: BigDecimal,
//        var undoAmount: BigDecimal = BigDecimal(0.0),
//        //  var valid: Boolean = true,
//        var operatorId: Long,
//        var operatorName: String,
//        var createdAt: Timestamp? = null,
//        var updatedAt: Timestamp? = null,
//        var valid: Boolean? = true
//): Serializable
//fun main(args:Array<String>){
//
//    java.lang.Long, java.lang.Long,
//    java.lang.String, java.lang.Long,
//    java.lang.Long,
//    java.lang.String, java.lang.String, java.lang.String, java.lang.String,
//    java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal,
//    java.lang.Long, java.lang.String, java.sql.Timestamp, java.sql.Timestamp
//    val s= ScanUseRecordDO(1L,2L,"",
//            4L,5L,"","","",
//            "",BigDecimal(0.00),BigDecimal(0.00),BigDecimal(0.00),
//            BigDecimal(0.00),5L,"",null,null)
//    println(s)
//}