package gvm.test.b;

public class Main {

    public void point() throws Exception {
        gvm.test.a.Main.print();
        System.out.println(Main.class.getName() + " Hello world!");

    }

}