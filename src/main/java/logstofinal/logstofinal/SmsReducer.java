package logstofinal.logstofinal;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SmsReducer extends Reducer<Text, Text, Text, NullWritable> {

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		// process values
		Text t=key;
	//	Text u=values;
		
/*		 while(values.hasNext())
		 {
			 try {
				   context.write(t,values);
				  } catch (InterruptedException e) {
				   e.printStackTrace();
				  } 
			 break;
		 }
		 
	*/	 
		String mob=" ",at=" ",v=" ",pr=" ",s=" ",da=" ";
		int new_priority=100;
		for(Text tw : values)	
		{
			 String line=tw.toString();
			 String arr[]=line.split("\\t");
			 String smsid=arr[4];
			 String mobile=arr[0];
			 String date=arr[5];
			 String attribute=arr[1];
			 String val=arr[2];
			 String priority=arr[3];
			 int i = Integer.parseInt(priority);
			 if(i<=new_priority)
			 {
				 new_priority=i;
				 mob=mobile;
				 at=attribute;
				 v=val;
				 pr=priority;
				 s=smsid;
				 da=date;
			 }	 
			
		}
		new_priority=0;
		 try {
			   context.write(new Text(mob+"\t"+at+"\t"+v+"\t"+pr+"\t"+s+"\t"+da), NullWritable.get());
			  } catch (IOException e) {
			   e.printStackTrace();
			  } catch (InterruptedException e) {
			   e.printStackTrace();
			  }
		
	}

}
