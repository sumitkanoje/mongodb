package com.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertDriver {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		DB db = (new MongoClient("localhost", 27017).getDB("studentsdb"));
		DBCollection dBCollection = db.getCollection("Student");
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("Name", "Manali Raje");
		basicDBObject.put("RollNo", "13416");
		dBCollection.insert(basicDBObject);
	
	}

}
