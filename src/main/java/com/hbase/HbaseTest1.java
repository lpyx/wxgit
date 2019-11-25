package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HbaseTest1 {

    static Connection staticConnection;

    static Table getHTable(String namespace, String tableName) throws IOException {
        Connection conn = getConnection();
        TableName table = TableName.valueOf(Bytes.toBytes(namespace), Bytes.toBytes(tableName));
        return conn.getTable(table);
    }

    static BufferedMutator getBufferedMutator(String namespace, String tableName) throws IOException {
        Connection conn = getConnection();
        TableName table = TableName.valueOf(Bytes.toBytes(namespace), Bytes.toBytes(tableName));
        return conn.getBufferedMutator(table);
    }

    static Connection getConnection() throws IOException {
        if (staticConnection == null) {

            //配置文件
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "192.168.56.101");  //hbase 服务地址
            conf.set("hbase.zookeeper.property.clientPort", "2181"); //端口号

            Connection conn = ConnectionFactory.createConnection(conf);
            staticConnection = conn;
        }
        return staticConnection;
    }

    static byte[] increRow = Bytes.toBytes("20190103");
    static byte[] increFamily = Bytes.toBytes("daily");
    static byte[] increColumn = Bytes.toBytes("hits");

    public static void main(String[] args) throws IOException {
        //       Table table = getHTable("ns1","t1");
//        Delete delete=  new Delete(ROW1);
//        delete.addFamily(FAMILY1,1545789995139L);
//        table.delete(delete);

//        testScan();
        //Table table = getHTable("ns1","counters");
        //table.incrementColumnValue(increRow,increFamily,increColumn,3L);
        //    testIncrement();
        //   Get get = new Get(increRow);
        //  get.addFamily(increFamily);
        testTable();
    }

    static void testTable() throws IOException {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();
        NamespaceDescriptor ns2Des = NamespaceDescriptor.create("ns2").build();
        TableName table = TableName.valueOf(Bytes.toBytes("ns2"), Bytes.toBytes("testTable"));

        TableDescriptorBuilder td = TableDescriptorBuilder.newBuilder(table);
        ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(FAMILY2).build();
        ColumnFamilyDescriptor columnFamilyDescriptor2 = ColumnFamilyDescriptorBuilder.newBuilder(FAMILY1).build();
        List<ColumnFamilyDescriptor> list = new ArrayList<>();
        list.add(columnFamilyDescriptor);
        list.add(columnFamilyDescriptor2);
        td.setColumnFamilies(list);
        //admin.createTable(td.build());
        //admin.modifyTable(td.build());

       // admin.deleteColumnFamily(table,FAMILY2);
      //  admin.disableTable(table);
       // admin.deleteTable(table);

     //   admin.getMaster

    }



    static void testIncrement() throws IOException {
        Table table = getHTable("ns1", "counters");
        Increment increment = new Increment(increRow);
        increment.addColumn(Bytes.toBytes("daily"), Bytes.toBytes("hits"), 3L);
        table.increment(increment);

    }


    static void testScan() throws IOException {
        Table table = getHTable("ns1", "t1");
        Scan scan = new Scan();
        scan.setCaching(2);
        //scan.withStartRow(ROW1,false);
        //scan.readVersions(2);
        scan.setBatch(1);
        ResultScanner scanner = table.getScanner(scan);
        Result result = scanner.next();
        while (result != null) {
            System.out.println(result);
            result = scanner.next();
        }


    }


    static void testBatch() {
        try {
            Table table = getHTable("ns1", "t1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void testGet() {
        try {
            Table table = getHTable("ns1", "t1");
            Get get = new Get(ROW1);
            //get.addFamily(FAMILY1);
            List list = new ArrayList();
            list.add(get);
            get.setTimeRange(0, new Date().getTime());

            Get get2 = new Get(ROW2);
            get2.addFamily(COLUMN1);
            // list.add(get2);
            //Result result = table.get(get);
            Result[] result = table.get(list);
            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }
            //  System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static byte[] ROW1 = Bytes.toBytes("row1");
    static byte[] ROW2 = Bytes.toBytes("row2");
    static byte[] FAMILY1 = Bytes.toBytes("FAMILY1");
    static byte[] FAMILY2 = Bytes.toBytes("FAMILY2");
    static byte[] COLUMN1 = Bytes.toBytes("column1");
    static byte[] COLUMN2 = Bytes.toBytes("column2");

    static void testPut() {
        try {


            Table table = getHTable("ns1", "t1");
            System.out.println();
            Put put1 = new Put(ROW1);
            Put put2 = new Put(ROW2);
            put1.addColumn(FAMILY1, COLUMN1, Bytes.toBytes("5value2"));
            put1.addColumn(FAMILY2, COLUMN2, Bytes.toBytes("5value3"));
            put2.addColumn(FAMILY1, COLUMN1, Bytes.toBytes("5value4"));
            put2.addColumn(FAMILY2, COLUMN2, Bytes.toBytes("5value5"));
            List list = new ArrayList<Put>();
            list.add(put1);
            list.add(put2);

            // CAS
//            Table.CheckAndMutateBuilder builder = table.checkAndMutate(ROW1,FAMILY1);
//            CompareOperator compare = CompareOperator.EQUAL;
//            builder.qualifier(COLUMN1);
//            builder.ifEquals(Bytes.toBytes("3value2"));
//            builder.thenPut(put1);


            //
            BufferedMutator mutator = getBufferedMutator("ns1", "t1");
            mutator.mutate(put1);

            mutator.flush();

            Thread.sleep(10000);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
