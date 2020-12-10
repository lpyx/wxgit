//package com.jvm;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
//public class ObjectInHeap
//{
//    private long address = 0;
//
//    private Unsafe unsafe;
//
//    {
//        try {
//            Field field = Unsafe.class.getDeclaredFields()[0];
//            field.setAccessible(true);
//            unsafe = (Unsafe)(field.get(null));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ObjectInHeap()
//    {
//        address = unsafe.allocateMemory(2 * 1024 * 1024);
//    }
//
//    // Exception in thread "main" java.lang.OutOfMemoryError
//    public static void main(String[] args)
//    {
//        while (true)
//        {
//            ObjectInHeap heap = new ObjectInHeap();
//            System.out.println("memory address=" + heap.address);
//        }
//    }
//}
