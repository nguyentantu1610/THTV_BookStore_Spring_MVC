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
import com.nttu.bean.Series;
import com.nttu.config.MongoFactory;

@Service("seriesService")
@Transactional
public class SeriesService {

	private static Logger log = Logger.getLogger(CategoryService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Series";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<Series> listSeries = new ArrayList<Series>();

	// Fetch all series from the mongo database.
	public List<Series> getAll() {

		if (listSeries.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Series series = new Series();
				series.setSeriesID((int) dbObject.get("seriesID"));
				series.setSeriesName(dbObject.get("seriesName").toString());

				// Adding the series details to the list.
				listSeries.add(series);
			}
			log.debug("Total records fetched from the mongo database are = " + listSeries.size());
			return listSeries;
		} else {
			return listSeries;
		}
	}

	// Add a new series to the mongo database.
	public Boolean create(Series series) {
		try {

			// Create a new object and add the new series details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("seriesID", getAll().size() + 1);
			dbObject.put("seriesName", series.getSeriesName());

			// Save a new series to the mongo collection.
			coll.insert(dbObject);

			listSeries.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new series to the mongo database", e);
			return false;
		}
	}

	// Fetching a single series details from the mongo database.
	public Series findByID(int seriesID) {
		Series series = new Series();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("seriesID", seriesID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			series.setSeriesID((int) dbObject.get("seriesID"));
			series.setSeriesName(dbObject.get("seriesName").toString());
		}

		// Return series object.
		return series;
	}

	// Update the selected series in the mongo database.
	public Boolean update(Series series) {
		log.debug("Updating the existing series in the mongo database; Entered series_id is= " + series.getSeriesID());
		try {
			DBObject query = new BasicDBObject();
			query.put("seriesID", series.getSeriesID());
			DBObject existing = coll.findOne(query);

			// Fetching the series details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("seriesID", series.getSeriesID());
				edited.put("seriesName", series.getSeriesName());

				// Update the existing series to the mongo database.
				coll.update(existing, edited);

				listSeries.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing series to the mongo database", e);
			return false;
		}
	}

	// Delete the selected series in the mongo database.
	public Boolean deleteByID(int seriesID) {
		log.debug("Deleting the existing series in the mongo database; Entered series_id is= " + seriesID);
		try {
			DBObject query = new BasicDBObject();
			query.put("seriesID", seriesID);
			DBObject existing = coll.findOne(query);

			// Fetching the series details.
			if (existing != null) {

				// Delete the existing series to the mongo database.
				coll.remove(existing);

				listSeries.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing series to the mongo database", e);
			return false;
		}
	}
}
