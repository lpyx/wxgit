//package com.jvm;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Direct {
//
//    final static int _1MB =1024*1024;
//    public static void main(String[] args) throws Exception {
//       Field field = Unsafe.class.getDeclaredField("theUnsafe");
//
//        ByteBuffer bf1 = ByteBuffer.allocateDirect(10 * 1024 * 1024);
////        ByteBuffer bf2 = ByteBuffer.allocateDirect(10 * 1024 * 1024);
////        ByteBuffer bf3 = ByteBuffer.allocateDirect(10 * 1024 * 1024);
//        field.setAccessible(true);
//            Unsafe unsafe = (Unsafe)(field.get(null));
//            List<ByteBuffer> list = new ArrayList<ByteBuffer>();
//            while(true){
//                System.out.println("FFFFFFFFF");
//                list.add(ByteBuffer.allocateDirect(10 * 1024 * 1024));
//                //list.add(unsafe.allocateMemory(10 * 1024 * 1024));
//            }
//
//    }
//}
//
