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
import com.nttu.bean.Supplier;
import com.nttu.config.MongoFactory;

@Service("supplierService")
@Transactional
public class SupplierService {

	private static Logger log = Logger.getLogger(SupplierService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Supplier";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<Supplier> suppliers = new ArrayList<Supplier>();

	// Fetch all suppliers from the mongo database.
	public List<Supplier> getAll() {

		if (suppliers.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Supplier supplier = new Supplier();
				supplier.setSupplierID((int) dbObject.get("supplierID"));
				;
				supplier.setSupplierName(dbObject.get("supplierName").toString());
				supplier.setSupplierAddress(dbObject.get("supplierAddress").toString());
				supplier.setSupplierPhoneNumber((int) dbObject.get("supplierPhoneNumber"));

				// Adding the supplier details to the list.
				suppliers.add(supplier);
			}
			log.debug("Total records fetched from the mongo database are = " + suppliers.size());
			return suppliers;
		} else {
			return suppliers;
		}
	}

	// Add a new supplier to the mongo database.
	public Boolean create(Supplier supplier) {
		try {

			// Create a new object and add the new supplier details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("supplierID", getAll().size() + 1);
			dbObject.put("supplierName", supplier.getSupplierName());
			dbObject.put("supplierAddress", supplier.getSupplierAddress());
			dbObject.put("supplierPhoneNumber", supplier.getSupplierPhoneNumber());

			// Save a new supplier to the mongo collection.
			coll.insert(dbObject);

			suppliers.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new supplier to the mongo database", e);
			return false;
		}
	}

	// Fetching a single supplier details from the mongo database.
	public Supplier findByID(int supplierID) {
		Supplier supplier = new Supplier();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("supplierID", supplierID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			supplier.setSupplierID((int) dbObject.get("supplierID"));
			supplier.setSupplierName(dbObject.get("supplierName").toString());
			supplier.setSupplierPhoneNumber((int) dbObject.get("supplierPhoneNumber"));
			supplier.setSupplierAddress(dbObject.get("supplierAddress").toString());
		}

		// Return supplier object.
		return supplier;
	}

	// Update the selected supplier in the mongo database.
	public Boolean update(Supplier supplier) {
		log.debug("Updating the existing supplier in the mongo database; Entered supplier_id is= "
				+ supplier.getSupplierID());
		try {
			DBObject query = new BasicDBObject();
			query.put("supplierID", supplier.getSupplierID());
			DBObject existing = coll.findOne(query);

			// Fetching the supplier details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("supplierID", supplier.getSupplierID());
				edited.put("supplierName", supplier.getSupplierName());
				edited.put("supplierPhoneNumber", supplier.getSupplierPhoneNumber());
				edited.put("supplierAddress", supplier.getSupplierAddress());

				// Update the existing supplier to the mongo database.
				coll.update(existing, edited);

				suppliers.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing supplier to the mongo database", e);
			return false;
		}
	}

	// Delete the selected supplier in the mongo database.
	public Boolean deleteByID(int supplierID) {
		log.debug("Deleting the existing supplier in the mongo database; Entered supplier_id is= " + supplierID);
		try {
			DBObject query = new BasicDBObject();
			query.put("supplierID", supplierID);
			DBObject existing = coll.findOne(query);

			// Fetching the supplier details.
			if (existing != null) {

				// Delete the existing supplier to the mongo database.
				coll.remove(existing);

				suppliers.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing supplier to the mongo database", e);
			return false;
		}
	}
}
