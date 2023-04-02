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
import com.nttu.bean.Product;
import com.nttu.config.MongoFactory;
import com.nttu.controller.Operator;

@Service("productService")
@Transactional
public class ProductService extends Operator {

	private static Logger log = Logger.getLogger(ProductService.class);
	private static String db_name = "THTV_BookStore", db_collection = "Product";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	public static List<String> listCost = new ArrayList<String>();
	private static List<Product> products = new ArrayList<Product>();

	// Fetch all products from the mongo database.
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {

		if (products.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Product product = new Product();
				product.setProductID((int) dbObject.get("productID"));
				product.setCategoryID((int) dbObject.get("categoryID"));
				product.setGenreID((List<Integer>) dbObject.get("genreID"));
				product.setSeriesID((int) dbObject.get("seriesID"));
				product.setAuthorID((List<Integer>) dbObject.get("authorID"));
				product.setSupplierID((int) dbObject.get("supplierID"));
				product.setPublisherID((int) dbObject.get("publisherID"));
				product.setProductName(dbObject.get("productName").toString());
				product.setProductDescription(dbObject.get("productDescription").toString());
				product.setProductImage((List<String>) dbObject.get("productImage"));
				product.setProductCost((int) dbObject.get("productCost"));
				product.setProductYear((int) dbObject.get("productYear"));
				product.setProductLevel(dbObject.get("productLevel").toString());
				product.setProductStock((int) dbObject.get("productStock"));

				listCost.add(convertCost((int) dbObject.get("productCost")));

				// Adding the product details to the list.
				products.add(product);
			}
			log.debug("Total records fetched from the mongo database are = " + products.size());
			return products;
		} else {
			return products;
		}
	}

	// Add a new product to the mongo database.
	public Boolean create(Product product) {
		try {

			// Create a new object and add the new product details to this object.
			DBObject dbObject = new BasicDBObject();

			dbObject.put("productID", getAll().size() + 1);
			dbObject.put("productName", product.getProductName());
			dbObject.put("categoryID", product.getCategoryID());
			dbObject.put("genreID", product.getGenreID());
			dbObject.put("seriesID", product.getSeriesID());
			dbObject.put("authorID", product.getAuthorID());
			dbObject.put("supplierID", product.getSupplierID());
			dbObject.put("publisherID", product.getPublisherID());
			dbObject.put("productDescription", product.getProductDescription());
			dbObject.put("productCost", product.getProductCost());
			dbObject.put("productYear", product.getProductYear());
			dbObject.put("productLevel", product.getProductLevel());
			dbObject.put("productStock", product.getProductStock());
			List<String> images = new ArrayList<String>();
			images.add("/webcomic/images/default-image.png");
			dbObject.put("productImage", images);

			// Save a new product to the mongo collection.
			coll.insert(dbObject);

			products.clear();

			listCost.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new product to the mongo database", e);
			return false;
		}
	}

	// Fetching a single product details from the mongo database.
	@SuppressWarnings("unchecked")
	public Product findByID(int productID) {
		Product product = new Product();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("productID", productID);
		DBObject dbObject = coll.findOne(query);

		if (dbObject != null) {
			product.setProductID((int) dbObject.get("productID"));
			product.setProductName(dbObject.get("productName").toString());
			product.setCategoryID((int) dbObject.get("categoryID"));
			product.setGenreID((List<Integer>) dbObject.get("genreID"));
			product.setSeriesID((int) dbObject.get("seriesID"));
			product.setAuthorID((List<Integer>) dbObject.get("authorID"));
			product.setSupplierID((int) dbObject.get("supplierID"));
			product.setPublisherID((int) dbObject.get("publisherID"));
			product.setProductDescription(dbObject.get("productDescription").toString());
			product.setProductImage((List<String>) dbObject.get("productImage"));
			product.setProductCost((int) dbObject.get("productCost"));
			product.setProductYear((int) dbObject.get("productYear"));
			product.setProductLevel(dbObject.get("productLevel").toString());
			product.setProductStock((int) dbObject.get("productStock"));
		}

		// Return product object.
		return product;
	}

	// Update the selected product in the mongo database.
	public Boolean update(Product product) {
		log.debug("Updating the existing product in the mongo database; Entered product_id is= "
				+ product.getProductID());
		try {
			DBObject query = new BasicDBObject();
			query.put("productID", product.getProductID());
			DBObject existing = coll.findOne(query);

			// Fetching the product details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("productID", product.getProductID());
				edited.put("productName", product.getProductName());
				edited.put("categoryID", product.getCategoryID());
				edited.put("genreID", product.getGenreID());
				edited.put("seriesID", product.getSeriesID());
				edited.put("authorID", product.getAuthorID());
				edited.put("supplierID", product.getSupplierID());
				edited.put("publisherID", product.getPublisherID());
				edited.put("productDescription", product.getProductDescription());
				edited.put("productCost", product.getProductCost());
				edited.put("productYear", product.getProductYear());
				edited.put("productLevel", product.getProductLevel());
				edited.put("productStock", product.getProductStock());
				edited.put("productImage", product.getProductImage());

				// Update the existing product to the mongo database.
				coll.update(existing, edited);

				products.clear();

				listCost.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing product to the mongo database", e);
			return false;
		}
	}

	// Delete the selected product in the mongo database.
	public Boolean deleteByID(int productID) {
		log.debug("Deleting the existing product in the mongo database; Entered product_id is= " + productID);
		try {
			DBObject query = new BasicDBObject();
			query.put("productID", productID);
			DBObject existing = coll.findOne(query);

			// Fetching the product details.
			if (existing != null) {

				// Delete the existing product to the mongo database.
				coll.remove(existing);

				products.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing product to the mongo database", e);
			return false;
		}
	}

	public int getTotalRow(int categoryID, String search) {
		DBObject query = new BasicDBObject();
		if (categoryID != 0) {
			query.put("categoryID", categoryID);
		} else if (search != null && search != "") {
			query.put("productName", search);
		}
		return coll.find(query).count();
	}

	// Fetch all products from the mongo database.
	@SuppressWarnings("unchecked")
	public List<Product> getAll2(int pageNum, int pageSize, int categoryID, String search) {

		if (products.size() != 0) {
			products.clear();
			listCost.clear();
		}

		products = new ArrayList<Product>();
		int skip = (pageNum - 1) * pageSize;
		DBObject query = new BasicDBObject();
		if (categoryID != 0) {
			query.put("categoryID", categoryID);
		} else if (search != null && search != "") {
			query.put("productName", search);
		}
		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find(query).skip(skip).limit(pageSize);

		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Product product = new Product();
			product.setProductID((int) dbObject.get("productID"));
			product.setCategoryID((int) dbObject.get("categoryID"));
			product.setGenreID((List<Integer>) dbObject.get("genreID"));
			product.setSeriesID((int) dbObject.get("seriesID"));
			product.setAuthorID((List<Integer>) dbObject.get("authorID"));
			product.setSupplierID((int) dbObject.get("supplierID"));
			product.setPublisherID((int) dbObject.get("publisherID"));
			product.setProductName(dbObject.get("productName").toString());
			product.setProductDescription(dbObject.get("productDescription").toString());
			product.setProductImage((List<String>) dbObject.get("productImage"));
			product.setProductCost((int) dbObject.get("productCost"));
			product.setProductYear((int) dbObject.get("productYear"));
			product.setProductLevel(dbObject.get("productLevel").toString());
			product.setProductStock((int) dbObject.get("productStock"));

			listCost.add(convertCost((int) dbObject.get("productCost")));

			// Adding the product details to the list.
			products.add(product);
		}
		log.debug("Total records fetched from the mongo database are = " + products.size());
		return products;
	}
}
