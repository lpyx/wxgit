package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class HBaseTest {
    static Configuration conf=  HBaseConfiguration.create();
    {

        conf.set("hbase.zookeeper.quorum","master");  //hbase 服务地址
        conf.set("hbase.zookeeper.property.clientPort","2181"); //端口号
    }
    public static void main(String[] args) {
        testGet();
    }

    static void testGet(){
        Table htable = null;
        try{
            Connection conn = ConnectionFactory.createConnection(conf);
            TableName tableName = TableName.valueOf("testtable");
            htable = conn.getTable(tableName);

            Get get = new Get("row1".getBytes());
            get.addFamily("fam1".getBytes());
          //  get.addColumn(Bytes.toBytes("fam1"))

            Result result = htable.get(get);
            System.out.println(result);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                htable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static void testPut(){
        Table htable = null;
        try {
            Connection conn = ConnectionFactory.createConnection(conf);
            TableName tableName = TableName.valueOf("testtable");
            htable = conn.getTable(tableName);

            Put put = new Put("row1".getBytes());

            //   put.addColumn("fam1".getBytes(),"column1".getBytes(),"value1".getBytes());
            //   put.addColumn("fam1".getBytes(),"column2".getBytes(),"value2".getBytes());

            List<Cell> cells =  put.get("fam1".getBytes(),"column1".getBytes());

            htable.put(put);

            System.out.println("添加成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                htable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
