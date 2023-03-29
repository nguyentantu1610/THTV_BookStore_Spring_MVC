package com.nttu.config;

import org.apache.log4j.Logger;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

@SuppressWarnings("deprecation")
public class MongoFactory {

	private static Logger log = Logger.getLogger(MongoFactory.class);
	private static MongoClient mongoClient;

	private MongoFactory() {
	}

	private static MongoClient getMongoClient() {
		try {
			if (mongoClient == null) {
				 mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			}

		} catch (MongoException ex) {
			log.error("Failed to create MongoClient", ex);
		}
		return mongoClient;
	}

	// Fetches the mongo database.
	private static DB getDB(String db_name) {
		return getMongoClient().getDB(db_name);
	}

	// Fetches the collection from the mongo database.
	public static DBCollection getCollection(String db_name, String db_collection) {
		return getDB(db_name).getCollection(db_collection);
	}
}