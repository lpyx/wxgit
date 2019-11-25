import java.io.FileInputStream;

public class TestClass {

    static Object o = new Object();
    public static void main(String[] args) {
        MyRun myRun = new MyRun();
        MyRun2 myRun2 = new MyRun2();
        Thread thread = new Thread(myRun);
        Thread thread2 = new Thread(myRun2);

        thread.start();
        thread2.start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (o) {
            System.out.println("before main notiry");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            o.notify();
            System.out.println("after main notiry");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after  main sleep");

        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // FileInputStream fis = new FileInputStream("ff");
    }

    static class MyRun implements Runnable{
        @Override
        public void run() {
            try {
                synchronized (o) {
                    System.out.println("before MyRun wait");

                    o.wait();
                   // o.notify();
                   // Thread.sleep(1000);
                    System.out.println("after MyRun sleep1");

                }
                System.out.println("after MyRun wait");

                Thread.sleep(1000);

                System.out.println("after MyRUn sleep");
            }catch (Exception e){

            }
        }
    }

    static class MyRun2 implements Runnable{
        @Override
        public void run() {
            try {
                synchronized (o) {
                    System.out.println("before MyRun2 wait");

                    o.wait();
                 //   o.notify();
                   // Thread.sleep(1000);
                    System.out.println("after MyRun2 sleep2");
                }
                System.out.println("after MyRun2 wait");

                Thread.sleep(1000);
                System.out.println("after MyRun2 sleep");
            }catch (Exception e){

            }
        }
    }

    private static void testSync(){

        try {
            o.wait();
        }catch (Exception e){

        }

    }
}
