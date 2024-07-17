package wxdgaming.mmo;


import wxdgaming.mmo.h2.H2ConnectPool;

public class H2Example {

    public static void main(String[] args) throws Exception {
        H2ConnectPool test = new H2ConnectPool(H2ConnectPool.DbType.MEM, "test");

    }

}
