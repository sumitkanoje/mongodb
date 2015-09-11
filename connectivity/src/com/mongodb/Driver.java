package com.mongodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class Driver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DB db = (new MongoClient("localhost", 27017).getDB("studentsdb"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = false;
		while (!flag) {
			flag = auth(db, bufferedReader);
		}
	}
	private static boolean auth(DB db, BufferedReader bufferedReader) throws IOException {
		boolean flag = true;
		System.out.println("Username: ");
		String user = bufferedReader.readLine();
		System.out.println("Password: ");
		String password = bufferedReader.readLine();
		if(db.authenticate(user, password.toCharArray()))
		{
			DBCollection dbCollection = db.getCollection("Student");
			String command = null;
			while(true){
				System.out.println("What you Want to Do? : findall | insertJSON | insert | update | delete");
				command = bufferedReader.readLine();
				if(command.equals("exit")) break;
				else if(command.equals("findall")) findall(dbCollection);
				else if(command.equals("insertJSON")) insertJSON(bufferedReader, dbCollection);
				else if(command.equals("insert")) insert(bufferedReader,dbCollection);
				else if(command.equals("update")) update(bufferedReader,dbCollection);
				else if(command.equals("delete")) delete(bufferedReader,dbCollection);
			}
		}	else{
			System.out.println("Invalid Username or Password");
			flag = false;
		}
		return flag;		
	}
	private static void delete(BufferedReader bufferedReader,
			DBCollection dbCollection) throws IOException {
		System.out.println("What??");
		DBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("Name", bufferedReader.readLine());
		dbCollection.remove(basicDBObject);
	}
	private static void update(BufferedReader bufferedReader,
			DBCollection dbCollection) throws IOException {
		System.out.println("Update from Name:");
		DBObject fromDBObject = new BasicDBObject();
		fromDBObject.put("Name", bufferedReader.readLine());
		System.out.println("Update to Name:");
		DBObject toDBObject = new BasicDBObject();
		toDBObject.put("Name", bufferedReader.readLine());
		DBObject updateObject = new BasicDBObject();
		updateObject.put("$set", toDBObject);
		dbCollection.update(fromDBObject, updateObject);
		
	}
	private static void insert(BufferedReader bufferedReader,
			DBCollection dbCollection) throws IOException {
		System.out.println("Student Name:");
		DBObject studDBObject = new BasicDBObject();
		studDBObject.put("Name", bufferedReader.readLine());
		System.out.println("Roll No:");
		studDBObject.put("RollNo", Integer.parseInt(bufferedReader.readLine()));
		dbCollection.insert(studDBObject);
	}
	private static void insertJSON(BufferedReader bufferedReader,
			DBCollection dbCollection) throws IOException {
		System.out.println("JSON:");
		dbCollection.insert((DBObject) JSON.parse(bufferedReader.readLine()));
		
	}
	private static void findall(DBCollection dbCollection) {
		DBCursor dbCursor = dbCollection.find();
		while (dbCursor.hasNext()) {
			System.out.println(dbCursor.next());
		}
		
	}

}
