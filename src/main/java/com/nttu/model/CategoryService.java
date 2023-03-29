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
import com.nttu.bean.Category;
import com.nttu.config.MongoFactory;

@Service("categoryService")
@Transactional
public class CategoryService {

	private static Logger log = Logger.getLogger(CategoryService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Category";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<Category> categories = new ArrayList<Category>();

	// Fetch all categories from the mongo database.
	public List<Category> getAll() {

		if (categories.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Category category = new Category();
				category.setCategoryID((int) dbObject.get("categoryID"));
				category.setCategoryName(dbObject.get("categoryName").toString());

				// Adding the category details to the list.
				categories.add(category);
			}
			log.debug("Total records fetched from the mongo database are = " + categories.size());
			return categories;
		} else {
			return categories;
		}
	}

	// Add a new category to the mongo database.
	public Boolean create(Category category) {
		try {

			// Create a new object and add the new category details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("categoryID", getAll().size() + 1);
			dbObject.put("categoryName", category.getCategoryName());

			// Save a new category to the mongo collection.
			coll.insert(dbObject);

			categories.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new category to the mongo database", e);
			return false;
		}
	}

	// Fetching a single category details from the mongo database.
	public Category findByID(int categoryID) {
		Category category = new Category();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("categoryID", categoryID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			category.setCategoryID((int) dbObject.get("categoryID"));
			category.setCategoryName(dbObject.get("categoryName").toString());
		}

		// Return category object.
		return category;
	}

	// Update the selected category in the mongo database.
	public Boolean update(Category category) {
		log.debug("Updating the existing category in the mongo database; Entered category_id is= "
				+ category.getCategoryID());
		try {
			DBObject query = new BasicDBObject();
			query.put("categoryID", category.getCategoryID());
			DBObject existing = coll.findOne(query);

			// Fetching the category details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("categoryID", category.getCategoryID());
				edited.put("categoryName", category.getCategoryName());

				// Update the existing category to the mongo database.
				coll.update(existing, edited);

				categories.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing category to the mongo database", e);
			return false;
		}
	}

	// Delete the selected category in the mongo database.
	public Boolean deleteByID(int categoryID) {
		log.debug("Deleting the existing category in the mongo database; Entered category_id is= " + categoryID);
		try {
			DBObject query = new BasicDBObject();
			query.put("categoryID", categoryID);
			DBObject existing = coll.findOne(query);

			// Fetching the category details.
			if (existing != null) {

				// Delete the existing category to the mongo database.
				coll.remove(existing);

				categories.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing category to the mongo database", e);
			return false;
		}
	}
}
