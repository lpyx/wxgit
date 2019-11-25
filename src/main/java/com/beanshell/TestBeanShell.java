//package com.beanshell;
//
//
//import bsh.EvalError;
//import bsh.Interpreter;
//
//import java.io.IOException;
//
//public class TestBeanShell {
//    public static void main(String[] args) {
//        Interpreter i = new Interpreter();
//        try {
//            Object o = i.eval(" int s(){return 1} \n 1");
//            System.out.println(o);
//        } catch (Exception evalError) {
//            evalError.printStackTrace();
//        }
//    }
//}
