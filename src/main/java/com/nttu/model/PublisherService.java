package com.nttu.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.nttu.bean.Publisher;
import com.nttu.config.MongoFactory;

@Service("publisherService")
@Transactional
public class PublisherService {

	private static Logger log = Logger.getLogger(PublisherService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Publisher";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<Publisher> publishers = new ArrayList<Publisher>();

	// Fetch all publishers from the mongo database.
	public List<Publisher> getAll() {

		if (publishers.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Publisher publisher = new Publisher();
				publisher.setPublisherID((int) dbObject.get("publisherID"));
				;
				publisher.setPublisherName(dbObject.get("publisherName").toString());

				// Adding the publisher details to the list.
				publishers.add(publisher);
			}
			log.debug("Total records fetched from the mongo database are = " + publishers.size());
			return publishers;
		} else {
			return publishers;
		}
	}

	// Add a new publisher to the mongo database.
	public Boolean create(Publisher publisher) {
		try {

			// Create a new object and add the new publisher details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("publisherID", getAll().size() + 1);
			dbObject.put("publisherName", publisher.getPublisherName());

			// Save a new publisher to the mongo collection.
			coll.insert(dbObject);

			publishers.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new publisher to the mongo database", e);
			return false;
		}
	}

	// Fetching a single publisher details from the mongo database.
	public Publisher findByID(int publisherID) {
		Publisher publisher = new Publisher();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("publisherID", publisherID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			publisher.setPublisherID((int) dbObject.get("publisherID"));
			publisher.setPublisherName(dbObject.get("publisherName").toString());
		}

		// Return publisher object.
		return publisher;
	}

	// Update the selected publisher in the mongo database.
	public Boolean update(Publisher publisher) {
		log.debug("Updating the existing publisher in the mongo database; Entered publisher_id is= "
				+ publisher.getPublisherID());
		try {
			DBObject query = new BasicDBObject();
			query.put("publisherID", publisher.getPublisherID());
			DBObject existing = coll.findOne(query);

			// Fetching the publisher details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("publisherID", publisher.getPublisherID());
				edited.put("publisherName", publisher.getPublisherName());

				// Update the existing publisher to the mongo database.
				coll.update(existing, edited);

				publishers.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing publisher to the mongo database", e);
			return false;
		}
	}

	// Delete the selected publisher in the mongo database.
	public Boolean deleteByID(int publisherID) {
		log.debug("Deleting the existing publisher in the mongo database; Entered publisher_id is= " + publisherID);
		try {
			DBObject query = new BasicDBObject();
			query.put("publisherID", publisherID);
			DBObject existing = coll.findOne(query);

			// Fetching the publisher details.
			if (existing != null) {

				// Delete the existing publisher to the mongo database.
				coll.remove(existing);

				publishers.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing publisher to the mongo database", e);
			return false;
		}
	}
}
