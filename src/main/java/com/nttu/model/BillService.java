package com.nttu.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.nttu.bean.Bill;
import com.nttu.bean.BillDetail;
import com.nttu.bean.Cart;
import com.nttu.bean.User;
import com.nttu.config.MongoFactory;
import com.nttu.controller.Operator;

@Service("billService")
@Transactional
public class BillService extends Operator {

	private static Logger log = Logger.getLogger(BillService.class);
	private static String db_name1 = "THTV_BookStore", db_collection1 = "Bill";
	private static DBCollection coll1 = MongoFactory.getCollection(db_name1, db_collection1);

	private static String db_name2 = "THTV_BookStore", db_collection2 = "BillDetail";
	private static DBCollection coll2 = MongoFactory.getCollection(db_name2, db_collection2);
	private static List<Bill> bills = new ArrayList<Bill>();
	public static List<BillDetail> billDetails = new ArrayList<BillDetail>();

	// Fetch all users from the mongo database.
	public List<Bill> getAll() {

		if (bills.size() == 0) {
			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll1.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Bill bill = new Bill();
				bill.setUserID((int) dbObject.get("userID"));
				bill.setBillID((int) dbObject.get("billID"));
				bill.setBillDate((String) dbObject.get("billDate"));
				bill.setBillDeliveryAddress((String) dbObject.get("billDeliveryAddress"));
				bill.setBillNote((String) dbObject.get("billNote"));
				bill.setBillPaymentMethod((String) dbObject.get("billPaymentMethod"));
				bill.setBillPhoneNumber((int) dbObject.get("billPhoneNumber"));
				bill.setBillTotal((int) dbObject.get("billTotal"));
				bill.setBillState((String) dbObject.get("billState"));

				// Adding the bill details to the list.
				bills.add(bill);
			}
			log.debug("Total records fetched from the mongo database are = " + bills.size());
			return bills;
		} else {
			return bills;
		}
	}

	// Fetch all users from the mongo database.
	public List<Bill> getAll2(int userID) {

		List<Bill> bills = new ArrayList<Bill>();

		DBObject query = new BasicDBObject();
		query.put("userID", userID);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll1.find(query);
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Bill bill = new Bill();
			bill.setUserID((int) dbObject.get("userID"));
			bill.setBillID((int) dbObject.get("billID"));
			bill.setBillDate((String) dbObject.get("billDate"));
			bill.setBillDeliveryAddress((String) dbObject.get("billDeliveryAddress"));
			bill.setBillNote((String) dbObject.get("billNote"));
			bill.setBillPaymentMethod((String) dbObject.get("billPaymentMethod"));
			bill.setBillPhoneNumber((int) dbObject.get("billPhoneNumber"));
			bill.setBillTotal((int) dbObject.get("billTotal"));
			bill.setBillState((String) dbObject.get("billState"));

			// Adding the bill details to the list.
			bills.add(bill);
		}
		log.debug("Total records fetched from the mongo database are = " + bills.size());
		return bills;
	}

	// Add a new bill to the mongo database.
	public Boolean create(User bill) {
		try {

			// Create a new object and add the new bill details to this object.
			int thisBillID = coll1.find().count() + 1;
			DBObject dbObject = new BasicDBObject();
			dbObject.put("billID", thisBillID);
			dbObject.put("userID", bill.getUserID());
			dbObject.put("billDeliveryAddress", bill.getUserAddress());
			dbObject.put("billPhoneNumber", bill.getUserPhoneNumber());
			dbObject.put("billNote", "Cẩn thận, hàng dễ vỡ!!!");
			dbObject.put("billPaymentMethod", "Thanh toán khi giao hàng");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			dbObject.put("billDate", dtf.format(now));
			List<Cart> carts = Cart.getCart();
			int total = 0;
			for (Cart cart : carts) {
				total = total + cart.getCost() * cart.getQuantity();
			}
			dbObject.put("billTotal", total);
			dbObject.put("billState", "Chưa duyệt");

			// Save a new bill to the mongo collection.
			coll1.insert(dbObject);

			for (Cart cart : carts) {
				DBObject dbObject1 = new BasicDBObject();
				dbObject1.put("billID", thisBillID);
				dbObject1.put("productID", cart.getProductID());
				dbObject1.put("productCost", cart.getCost());
				dbObject1.put("productQuantity", cart.getQuantity());
				coll2.insert(dbObject1);
			}

			Cart.getCart().clear();
			bills.clear();
			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new bill to the mongo database", e);
			return false;
		}
	}

	// Fetching a single bill details from the mongo database.
	public Bill findByID(int billID) {
		Bill bill = new Bill();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("billID", billID);
		DBObject dbObject = coll1.findOne(query);

		int thisBillID = 0;

		if (dbObject != null) {
			thisBillID = (int) dbObject.get("billID");
			bill.setBillID(thisBillID);
			bill.setBillDate((String) dbObject.get("billDate"));
			bill.setBillDeliveryAddress((String) dbObject.get("billDeliveryAddress"));
			bill.setBillNote((String) dbObject.get("billNote"));
			bill.setBillPaymentMethod((String) dbObject.get("billPaymentMethod"));
			bill.setBillPhoneNumber((int) dbObject.get("billPhoneNumber"));
			bill.setUserID((int) dbObject.get("userID"));
			bill.setBillTotal((int) dbObject.get("billTotal"));
		}

		if (thisBillID != 0) {
			billDetails.clear();
			DBObject query1 = new BasicDBObject();
			query1.put("billID", thisBillID);
			DBCursor cursor = coll2.find(query1);
			while (cursor.hasNext()) {
				DBObject dbObject1 = cursor.next();
				BillDetail billDetail = new BillDetail();
				billDetail.setBillID(thisBillID);
				billDetail.setProductCost((int) dbObject1.get("productCost"));
				billDetail.setProductID((int) dbObject1.get("productID"));
				billDetail.setProductQuantity((int) dbObject1.get("productQuantity"));
				billDetails.add(billDetail);
			}
		}

		// Return bill object.
		return bill;
	}

	// Update the selected bill in the mongo database.
	public Boolean update(Bill bill) {
		log.debug("Updating the existing bill in the mongo database; Entered bill_id is= " + bill.getBillID());
		try {
			DBObject query = new BasicDBObject();
			query.put("billID", bill.getBillID());
			DBObject existing = coll1.findOne(query);

			// Fetching the bill details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("billID", bill.getBillID());
				edited.put("userID", bill.getUserID());
				edited.put("billDate", bill.getBillDate());
				edited.put("billPaymentMethod", bill.getBillPaymentMethod());
				edited.put("billDeliveryAddress", convertUTF8(bill.getBillDeliveryAddress()));
				edited.put("billPhoneNumber", bill.getBillPhoneNumber());
				edited.put("billTotal", bill.getBillTotal());
				edited.put("billNote", convertUTF8(bill.getBillNote()));
				edited.put("billState", convertUTF8(bill.getBillState()));

				// Update the existing bill to the mongo database.
				coll1.update(existing, edited);

				bills.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing bill to the mongo database", e);
			return false;
		}
	}
}
