package com.mr1;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.mapreduce.Mapper;

public class FruitMapper extends TableMapper<ImmutableBytesWritable, Put> {
	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected void map(ImmutableBytesWritable key, Result value,
			Mapper<ImmutableBytesWritable, Result, ImmutableBytesWritable, Put>.Context context)
					throws IOException, InterruptedException {
		//构建put对象
		Put put = new Put(key.get());	
		Cell[] cells = value.rawCells();
		for (Cell cell : cells) {
			if("name".equals(CellUtil.cloneQualifier(cell))) {
				put.add(cell);
			}	
		}
		//写出数据
		context.write(key, put);
	}
}
