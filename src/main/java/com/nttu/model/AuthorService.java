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
import com.nttu.bean.Author;
import com.nttu.config.MongoFactory;

@Service("authorService")
@Transactional
public class AuthorService {

	private static Logger log = Logger.getLogger(AuthorService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Author";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<Author> authors = new ArrayList<Author>();

	// Fetch all authors from the mongo database.
	public List<Author> getAll() {

		if (authors.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Author author = new Author();
				author.setAuthorID((int) dbObject.get("authorID"));

				author.setAuthorName(dbObject.get("authorName").toString());

				// Adding the author details to the list.
				authors.add(author);
			}
			log.debug("Total records fetched from the mongo database are = " + authors.size());
			return authors;
		} else {
			return authors;
		}
	}

	// Add a new author to the mongo database.
	public Boolean create(Author author) {
		try {

			// Create a new object and add the new author details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("authorID", getAll().size() + 1);
			dbObject.put("authorName", author.getAuthorName());

			// Save a new author to the mongo collection.
			coll.insert(dbObject);

			authors.clear();
			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new author to the mongo database", e);
			return false;
		}
	}

	// Fetching a single author details from the mongo database.
	public Author findByID(int authorID) {
		Author author = new Author();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("authorID", authorID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			author.setAuthorID((int) dbObject.get("authorID"));
			author.setAuthorName(dbObject.get("authorName").toString());
		}

		// Return author object.
		return author;
	}

	// Update the selected author in the mongo database.
	public Boolean update(Author author) {
		log.debug("Updating the existing author in the mongo database; Entered author_id is= " + author.getAuthorID());
		try {
			DBObject query = new BasicDBObject();
			query.put("authorID", author.getAuthorID());
			DBObject existing = coll.findOne(query);

			// Fetching the author details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("authorID", author.getAuthorID());
				edited.put("authorName", author.getAuthorName());

				// Update the existing author to the mongo database.
				coll.update(existing, edited);

				authors.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing author to the mongo database", e);
			return false;
		}
	}

	// Delete the selected author in the mongo database.
	public Boolean deleteByID(int authorID) {
		log.debug("Deleting the existing author in the mongo database; Entered author_id is= " + authorID);
		try {
			DBObject query = new BasicDBObject();
			query.put("authorID", authorID);
			DBObject existing = coll.findOne(query);

			// Fetching the author details.
			if (existing != null) {

				// Delete the existing author to the mongo database.
				coll.remove(existing);

				authors.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing author to the mongo database", e);
			return false;
		}
	}
}
