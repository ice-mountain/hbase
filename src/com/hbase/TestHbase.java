package com.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;



public class TestHbase{
	private static Connection connection;
	private static Admin admin;
	private static Configuration conf;
	static {
		//获取配置文件的对象
		conf=HBaseConfiguration.create();
		//添加zookeeper的入口
		conf.set("hbase.zookeeper.quorum","hadoop:2181");
		//获取连接对象	
		try {
			connection = ConnectionFactory.createConnection(conf);
			//获取管理对象
			admin = connection.getAdmin();

		} catch (IOException e) {
			e.printStackTrace();
		}						
	}
	static void close(Connection connection,Admin admin) {
		if(connection!=null) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(admin!=null) {
			try {
				admin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//判断表是否存在
	//旧的api
	@SuppressWarnings("deprecation")
	public static boolean tableExist(String tableName) throws IOException {
		//获取配置文件的对象
		HBaseConfiguration conf=new HBaseConfiguration();
		//添加zookeeper的入口
		conf.set("hbase.zookeeper.quorum","hadoop:2181");
		//获取hbase的管理对象
		HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
		//执行
		boolean tableExists = hBaseAdmin.tableExists(tableName);
		//关闭资源
		hBaseAdmin.close();
		return tableExists;
	}
	public static boolean newtableExist(String tableName) throws IOException {
		boolean tableExists = admin.tableExists(TableName.valueOf(tableName));
		admin.close();
		return tableExists;					
	}
	//创建表
	public static void createTable(String tableName,String...cfs) throws IOException {
		if(tableExist(tableName)) {
			System.out.println("表已经存在了");
			return;
		}
		//创建表描述器
		HTableDescriptor hTableDescriptornew=new HTableDescriptor(TableName.valueOf(tableName));
		for(String cf:cfs ) {
			HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
			hTableDescriptornew.addFamily(hColumnDescriptor);	
		}
		//创建表操作
		try {
			admin.createTable(hTableDescriptornew);
			System.out.println("表创建成功");
			close(connection, admin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//删除表
	public static void deleteTable(String tableName) throws IOException {
		if(!newtableExist(tableName)) {
			System.out.println("删除的表不存在");
			return;
		}
		try {
			//使表不可用
			admin.disableTable(TableName.valueOf(tableName));
			//删除表
			admin.deleteTable(TableName.valueOf(tableName));
			System.out.println("表删除成功");
			close(connection, admin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//增/改
	public static void putData(String tableName,String rowKey,String cf,String cn,String value) throws IOException {
		//获取表对象
		//HTable hTable = new HTable(conf, TableName.valueOf(tableName));
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
		//添加数据
		put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
		//添加操作
		hTable.put(put);
		System.out.println("添加数据成功");
	}
	//删除数据
	public static void deleteData(String tableName,String rowKey,String cf,String cn) throws IOException {
		if(!newtableExist(tableName)) {
			System.out.println("删除表不存在");
			return;
		}
		//获取表对象
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		//或取删除对象
		Delete delete = new Delete(Bytes.toBytes(rowKey));
		//删除所有版本
		delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));
		//删除指定的版本如果不指定则删除时间戳最大的一个
		//delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
		//执行删除操作
		hTable.delete(delete);
		System.out.println("数据删除成功");
		hTable.close();
		close(connection, admin);
	}
	//扫描全表并打印数据
	public static void scanTable(String tableName) throws IOException {
		//获取表对象
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		//构建扫描器
		Scan scan = new Scan();
		ResultScanner results = hTable.getScanner(scan);
		for( Result result : results) {
			Cell[] cells = result.rawCells();
			for (Cell cell : cells) {
				System.out.println("RK:"+Bytes.toString(CellUtil.cloneRow(cell))+
						"\tCF:"+Bytes.toString(CellUtil.cloneFamily(cell))+
						"\tCN:"+Bytes.toString(CellUtil.cloneQualifier(cell))+
						"\tVALUE:"+Bytes.toString(CellUtil.cloneValue(cell)));
			}
		}	
		hTable.close();
		close(connection, admin);
	}
	//获取指定列族和列的数据
	public static void getData(String tableName,String rowKey,String cf,String cn) throws IOException {
		//获取表对象
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		//创建一个get对象
		Get get=new Get(Bytes.toBytes(rowKey));
		//指定列族和列名
		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
		//get.setMaxVersions(2); //指定版本
		Result results = hTable.get(get);
		Cell[] cells = results.rawCells();
		for (Cell cell : cells) {
			System.out.println("RK:"+Bytes.toString(CellUtil.cloneRow(cell))+
					"\tCF:"+Bytes.toString(CellUtil.cloneFamily(cell))+
					"\tCN:"+Bytes.toString(CellUtil.cloneQualifier(cell))+
					"\tVALUE:"+Bytes.toString(CellUtil.cloneValue(cell)));
		}
		hTable.close();
		close(connection, admin);
	}			
	public static void main(String[] args) throws IOException {
		//System.out.println( tableExist("test"));
		//System.out.println( tableExist("stu"));
		System.out.println( newtableExist("test"));
		System.out.println( newtableExist("stu"));
		//createTable("fruit","info");
		//deleteTable("test03");
		//putData("fruit","1001", "info", "name", "Apple");
		//deleteData("test", "1001", "cf", "name");
		//scanTable("fruit_mr");
		//getData("test", "row1", "cf", "name");
	}
}
