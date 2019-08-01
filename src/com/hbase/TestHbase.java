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
		//��ȡ�����ļ��Ķ���
		conf=HBaseConfiguration.create();
		//���zookeeper�����
		conf.set("hbase.zookeeper.quorum","hadoop:2181");
		//��ȡ���Ӷ���	
		try {
			connection = ConnectionFactory.createConnection(conf);
			//��ȡ�������
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
	//�жϱ��Ƿ����
	//�ɵ�api
	@SuppressWarnings("deprecation")
	public static boolean tableExist(String tableName) throws IOException {
		//��ȡ�����ļ��Ķ���
		HBaseConfiguration conf=new HBaseConfiguration();
		//���zookeeper�����
		conf.set("hbase.zookeeper.quorum","hadoop:2181");
		//��ȡhbase�Ĺ������
		HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
		//ִ��
		boolean tableExists = hBaseAdmin.tableExists(tableName);
		//�ر���Դ
		hBaseAdmin.close();
		return tableExists;
	}
	public static boolean newtableExist(String tableName) throws IOException {
		boolean tableExists = admin.tableExists(TableName.valueOf(tableName));
		admin.close();
		return tableExists;					
	}
	//������
	public static void createTable(String tableName,String...cfs) throws IOException {
		if(tableExist(tableName)) {
			System.out.println("���Ѿ�������");
			return;
		}
		//������������
		HTableDescriptor hTableDescriptornew=new HTableDescriptor(TableName.valueOf(tableName));
		for(String cf:cfs ) {
			HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
			hTableDescriptornew.addFamily(hColumnDescriptor);	
		}
		//���������
		try {
			admin.createTable(hTableDescriptornew);
			System.out.println("�����ɹ�");
			close(connection, admin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//ɾ����
	public static void deleteTable(String tableName) throws IOException {
		if(!newtableExist(tableName)) {
			System.out.println("ɾ���ı�����");
			return;
		}
		try {
			//ʹ������
			admin.disableTable(TableName.valueOf(tableName));
			//ɾ����
			admin.deleteTable(TableName.valueOf(tableName));
			System.out.println("��ɾ���ɹ�");
			close(connection, admin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��/��
	public static void putData(String tableName,String rowKey,String cf,String cn,String value) throws IOException {
		//��ȡ�����
		//HTable hTable = new HTable(conf, TableName.valueOf(tableName));
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
		//�������
		put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
		//��Ӳ���
		hTable.put(put);
		System.out.println("������ݳɹ�");
	}
	//ɾ������
	public static void deleteData(String tableName,String rowKey,String cf,String cn) throws IOException {
		if(!newtableExist(tableName)) {
			System.out.println("ɾ��������");
			return;
		}
		//��ȡ�����
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		//��ȡɾ������
		Delete delete = new Delete(Bytes.toBytes(rowKey));
		//ɾ�����а汾
		delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));
		//ɾ��ָ���İ汾�����ָ����ɾ��ʱ�������һ��
		//delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
		//ִ��ɾ������
		hTable.delete(delete);
		System.out.println("����ɾ���ɹ�");
		hTable.close();
		close(connection, admin);
	}
	//ɨ��ȫ����ӡ����
	public static void scanTable(String tableName) throws IOException {
		//��ȡ�����
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		//����ɨ����
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
	//��ȡָ��������е�����
	public static void getData(String tableName,String rowKey,String cf,String cn) throws IOException {
		//��ȡ�����
		Table hTable = connection.getTable(TableName.valueOf(tableName));
		//����һ��get����
		Get get=new Get(Bytes.toBytes(rowKey));
		//ָ�����������
		//get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
		//get.setMaxVersions(2); //ָ���汾
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
