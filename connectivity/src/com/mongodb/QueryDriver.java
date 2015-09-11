package com.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class QueryDriver {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		DB db = (new MongoClient("localhost", 27017).getDB("studentsdb"));
		DBCollection dBCollection = db.getCollection("Student");
		BasicDBObject basicdBObject = new BasicDBObject();
		basicdBObject.put("Name", "Sumit Knoje");
		DBCursor dbCursor = dBCollection.find(basicdBObject);
		while(dbCursor.hasNext()) System.out.println(dbCursor.next());
		
		

	}

}
