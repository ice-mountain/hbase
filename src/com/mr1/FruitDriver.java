package com.mr1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FruitDriver extends Configuration implements Tool{
	private Configuration configuration=null;

	@Override
	public Configuration getConf() {
		return configuration;
	}

	@Override
	public void setConf(Configuration configuration) {
		this.configuration=configuration;
	}

	@Override
	public int run(String[] arg0) throws Exception {
		//��ȡ�������
		Job job = Job.getInstance(configuration);
		//ָ��driver��
		job.setJarByClass(FruitDriver.class);
		//ָ��mapper
		TableMapReduceUtil.initTableMapperJob("fruit", new Scan(), FruitMapper.class, 
				ImmutableBytesWritable.class, Put.class, job);
		//ָ��reducer
		TableMapReduceUtil.initTableReducerJob("fruit_rm1", FruitRdeuce.class, job);
		//�ύ
		boolean b = job.waitForCompletion(true);
		return b?1:0;				
	}
	public static void main(String[] args) throws Exception {
	   Configuration confguiation = HBaseConfiguration.create();
	   confguiation.set("hbase.zookeeper.quorum","hadoop:2181");
		ToolRunner.run(confguiation, new FruitDriver(), args);		
	}
}
