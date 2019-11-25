public class TestDumpClass {
    public static void main(String[] args) {
        while(true){
            System.out.println("111");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
