package com.nttu.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.nttu.bean.User;
import com.nttu.config.MongoFactory;
import com.nttu.controller.Operator;

@Service("userService")
@Transactional
public class UserService extends Operator {

	private static Logger log = Logger.getLogger(UserService.class);
	private static String db_name = "THTV_BookStore", db_collection = "User";
	private static DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
	private static List<User> users = new ArrayList<User>();

	// Fetch all users from the mongo database.
	public List<User> getAll() {

		if (users.size() == 0) {

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				User user = new User();
				user.setUserID((int) dbObject.get("userID"));
				user.setUserEmail(dbObject.get("userEmail").toString());
				user.setUserPassword(dbObject.get("userPassword").toString());
				user.setUserName(dbObject.get("userName").toString());
				user.setUserRole(dbObject.get("userRole").toString());
				user.setUserAddress(dbObject.get("userAddress").toString());
				user.setUserPhoneNumber((int) dbObject.get("userPhoneNumber"));
				user.setUserState((boolean) dbObject.get("userState"));

				// Adding the user details to the list.
				users.add(user);
			}
			log.debug("Total records fetched from the mongo database are = " + users.size());
			return users;
		} else {
			return users;
		}
	}

	// Add a new user to the mongo database.
	public Boolean create(User user) {
		try {

			// Create a new object and add the new user details to this object.
			DBObject dbObject = new BasicDBObject();
			dbObject.put("userID", getAll().size() + 1);
			dbObject.put("userEmail", user.getUserEmail());
			dbObject.put("userPassword", BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt(14)));
			dbObject.put("userName", user.getUserName());
			dbObject.put("userRole", "ROLE_USER");
			dbObject.put("userAddress", "Đang cập nhật");
			dbObject.put("userPhoneNumber", 0);
			dbObject.put("userState", true);

			// Save a new user to the mongo collection.
			coll.insert(dbObject);

			users.clear();

			return true;
		} catch (Exception e) {
			log.error("An error occurred while saving a new user to the mongo database", e);
			return false;
		}
	}

	// Fetching a single user details from the mongo database.
	public User findByEmail(String userEmail) {
		User user = new User();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("userEmail", userEmail);

		DBObject dbObject = coll.findOne(query);
		if (dbObject != null) {
			user.setUserID((int) dbObject.get("userID"));
			user.setUserEmail(dbObject.get("userEmail").toString());
			user.setUserPassword(dbObject.get("userPassword").toString());
			user.setUserName(dbObject.get("userName").toString());
			user.setUserRole(dbObject.get("userRole").toString());
			user.setUserAddress(dbObject.get("userAddress").toString());
			user.setUserPhoneNumber((int) dbObject.get("userPhoneNumber"));
			user.setUserState((boolean) dbObject.get("userState"));
		}

		// Return user object.
		return user;
	}

	// Fetching a single user details from the mongo database.
	public User findByID(int userID) {
		User user = new User();

		// Fetching the record object from the mongo database.
		DBObject query = new BasicDBObject();
		query.put("userID", userID);

		DBObject dbObject = coll.findOne(query);
		if (dbObject != null) {
			user.setUserID((int) dbObject.get("userID"));
			user.setUserEmail(dbObject.get("userEmail").toString());
			user.setUserPassword(dbObject.get("userPassword").toString());
			user.setUserName(dbObject.get("userName").toString());
			user.setUserRole(dbObject.get("userRole").toString());
			user.setUserAddress(dbObject.get("userAddress").toString());
			user.setUserPhoneNumber((int) dbObject.get("userPhoneNumber"));
			user.setUserState((boolean) dbObject.get("userState"));
		}

		// Return user object.
		return user;
	}

	// Update the selected user in the mongo database.
	public Boolean update(User user, int type) {
		log.debug("Updating the existing user in the mongo database; Entered user_id is= " + user.getUserID());
		try {
			DBObject query = new BasicDBObject();
			query.put("userID", user.getUserID());
			DBObject existing = coll.findOne(query);

			// Fetching the user details.
			if (existing != null) {

				// Create a new object and assign the updated details.
				DBObject edited = new BasicDBObject();
				edited.put("userID", user.getUserID());
				edited.put("userName", convertUTF8(user.getUserName()));
				edited.put("userPhoneNumber", user.getUserPhoneNumber());
				edited.put("userAddress", convertUTF8(user.getUserAddress()));
				edited.put("userRole", user.getUserRole());
				edited.put("userEmail", user.getUserEmail());
				edited.put("userState", user.isUserState());
				if (type == 0) {
					edited.put("userPassword", BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt(14)));
				} else {
					edited.put("userPassword", user.getUserPassword());
				}

				// Update the existing user to the mongo database.
				coll.update(existing, edited);

				users.clear();

				return true;
			}
			return false;
		} catch (Exception e) {
			log.error("An error has occurred while updating an existing user to the mongo database", e);
			return false;
		}
	}
}
