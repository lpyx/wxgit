import sun.nio.ch.FileChannelImpl
import java.io.FileInputStream
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

fun main(args:Array<String>){
    TestFile().t();
}
class TestFile{

    fun  t(){
        val buf = ByteBuffer.allocate(1024 * 8)
        val buffer = ByteArray(1024 * 8)
        val bis = FileInputStream("/Users/handty/IdeaProjects/wxgit/gradle.properties")
        var len = 0
        //buf.put(buffer)
        val out = "/Users/handty/IdeaProjects/wxgit/aa.txt"
        val channelOut = RandomAccessFile(out,"rwd").channel

        while ( ((bis.read(buffer)).also { len = it }) != -1) {
            println(len)
            //buf.flip()

            channelOut.write(ByteBuffer.wrap(buffer));
            buf.compact()
           // cursor += len
           // val progress = (100f * (cursor.toFloat() / totalLength.toFloat())).toInt()
        //    call?.invoke(FileDownloadEvent.Progress(progress, cursor))
        }
    }
}