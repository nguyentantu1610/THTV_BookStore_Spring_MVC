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
import com.nttu.bean.Genre;
import com.nttu.config.MongoFactory;

@Service("genreService")
@Transactional
public class GenreService {

	private static Logger log = Logger.getLogger(GenreService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Genre";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<Genre> genres = new ArrayList<Genre>();

	// Fetch all genres from the mongo database.
	public List<Genre> getAll() {

		if (genres.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Genre genre = new Genre();
				genre.setGenreID((int) dbObject.get("genreID"));
				;
				genre.setGenreName(dbObject.get("genreName").toString());

				// Adding the genre details to the list.
				genres.add(genre);
			}
			log.debug("Total records fetched from the mongo database are = " + genres.size());
			return genres;
		} else {
			return genres;
		}
	}

	// Add a new genre to the mongo database.
	public Boolean create(Genre genre) {
		try {

			// Create a new object and add the new genre details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("genreID", getAll().size() + 1);
			dbObject.put("genreName", genre.getGenreName());

			// Save a new genre to the mongo collection.
			coll.insert(dbObject);

			genres.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new user to the mongo database", e);
			return false;
		}
	}

	// Fetching a single genre details from the mongo database.
	public Genre findByID(int genreID) {
		Genre genre = new Genre();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("genreID", genreID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			genre.setGenreID((int) dbObject.get("genreID"));
			genre.setGenreName(dbObject.get("genreName").toString());
		}

		// Return genre object.
		return genre;
	}

	// Update the selected genre in the mongo database.
	public Boolean update(Genre genre) {
		log.debug("Updating the existing genre in the mongo database; Entered genre_id is= " + genre.getGenreID());
		try {
			DBObject query = new BasicDBObject();
			query.put("genreID", genre.getGenreID());
			DBObject existing = coll.findOne(query);

			// Fetching the genre details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("genreID", genre.getGenreID());
				edited.put("genreName", genre.getGenreName());

				// Update the existing genre to the mongo database.
				coll.update(existing, edited);

				genres.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing genre to the mongo database", e);
			return false;
		}
	}

	// Delete the selected genre in the mongo database.
	public Boolean deleteByID(int genreID) {
		log.debug("Deleting the existing genre in the mongo database; Entered genre_id is= " + genreID);
		try {
			DBObject query = new BasicDBObject();
			query.put("genreID", genreID);
			DBObject existing = coll.findOne(query);

			// Fetching the genre details.
			if (existing != null) {

				// Delete the existing genre to the mongo database.
				coll.remove(existing);

				genres.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing genre to the mongo database", e);
			return false;
		}
	}
}
