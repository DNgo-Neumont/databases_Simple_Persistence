package neumont;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


public class mongoDBInterface {
	
	//Everything here should work
	
	FileReadWrite fileRW = new FileReadWrite();
	
	MongoClient client = MongoClients.create();
	MongoDatabase database = client.getDatabase("db");
	
	
	public void writeRecords(String path) {
		
		if(database.getCollection("Employees") == null) {
			database.createCollection("Employees");
		}
		
		HashMap<Integer, Employee> employeeMap = fileRW.createHashMap(path);
		
		List<Document> documentList = new ArrayList<>();
		
		for(int i = 0; i < employeeMap.size(); i++) {
			Document currentDocument = new Document();
			if(employeeMap.get(i) != null) {
				currentDocument.append("id", employeeMap.get(i).getId());
				currentDocument.append("first_name", employeeMap.get(i).getFirstName());
				currentDocument.append("last_name", employeeMap.get(i).getLastName());
				currentDocument.append("hire_date", employeeMap.get(i).getHireYear());
				
				documentList.add(currentDocument);
			}
		}
		
		database.getCollection("Employees").insertMany(documentList);	
	}
	
	public void updateDocument(int employeeID, String firstName, String lastName) {
		if(database.getCollection("Employees") != null) {
			
			//Done this way because of Mongo's odd way of querying for data through drivers and methods
			//A filter document actually queries the database for the matching document and
			//the update document then is used to set the new values.
			Document documentFilter = new Document();
			documentFilter.append("id", employeeID);
			
			Document newData = new Document();
			newData.append("first_name", firstName).append("last_name", lastName);
			
			Document update = new Document();
			update.append("$set", newData);
			
			database.getCollection("Employees").updateOne(documentFilter, update);
		}else {
			System.out.println("Working collection does not exist - please ensure the Employees collection exists");
		}
	}
	
	public List<Document> findRecord(int id) {
		
		Document filter = new Document();
		filter.append("id", id);
		
		FindIterable<Document> result = database.getCollection("Employees").find(filter);
		
		if(result == null) {
			return null;
		}
		
		List<Document> matchList = new ArrayList<>();
		
		for(Document current : result) {
			matchList.add(current);
		}
		
		return matchList;
	}
	
	public List<Document> findRecord(String firstName, String lastName) {
		
		Document filter = new Document();
		filter.append("first_name", firstName).append("last_name", lastName);
		
		FindIterable<Document> result = database.getCollection("Employees").find(filter);
		
		if(result == null) {
			return null;
		}
		
		List<Document> matchList = new ArrayList<>();
		
		for(Document current : result) {
			matchList.add(current);
		}
		
		return matchList;
	}
	
	public void deleteSingleRecord(int id) {
		Document filter = new Document();
		filter.append("id", id);
		
		System.out.println(database.getCollection("Employees").deleteOne(filter).toString());		
	}
	
	public void deleteSingleRecord(String name) {
		Document filter = new Document();
		filter.append("first_name", name);
		
		System.out.println(database.getCollection("Employees").deleteOne(filter).toString());		
	}
	
	public void deleteRecords(int id) {
		Document filter = new Document();
		filter.append("id", id);
		
		System.out.println(database.getCollection("Employees").deleteMany(filter).toString());		
	}
	
	public void deleteRecords(String name) {
		Document filter = new Document();
		filter.append("first_name", name);
		
		System.out.println(database.getCollection("Employees").deleteMany(filter).toString());		
	}
	
	public void insertRecord(int id, String firstName, String lastName, int hireYear) {
		Document record = new Document();
		
		record.append("id", id).append("first_name", firstName).append("last_name", lastName).append("hire_date", hireYear);
		
		
		if(database.getCollection("Employees") != null) {
			database.getCollection("Employees").insertOne(record);
		}
	}
	
	
	
	
}
