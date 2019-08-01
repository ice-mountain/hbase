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
		//获取任务对象
		Job job = Job.getInstance(configuration);
		//指定driver类
		job.setJarByClass(FruitDriver.class);
		//指定mapper
		TableMapReduceUtil.initTableMapperJob("fruit", new Scan(), FruitMapper.class, 
				ImmutableBytesWritable.class, Put.class, job);
		//指定reducer
		TableMapReduceUtil.initTableReducerJob("fruit_rm1", FruitRdeuce.class, job);
		//提交
		boolean b = job.waitForCompletion(true);
		return b?1:0;				
	}
	public static void main(String[] args) throws Exception {
	   Configuration confguiation = HBaseConfiguration.create();
	   confguiation.set("hbase.zookeeper.quorum","hadoop:2181");
		ToolRunner.run(confguiation, new FruitDriver(), args);		
	}
}
