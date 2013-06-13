package net.nueca.database;

import java.sql.SQLException;

import net.nueca.imonggo.enums.DatabaseOperations;
import net.nueca.imonggo.enums.Modules;
import net.nueca.imonggo.objects.Inventory;
import net.nueca.imonggo.objects.Product;
import net.nueca.imonggo.objects.Session;
import net.nueca.imonggo.objects.Setting;
import net.nueca.imonggo.objects.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * 
 * This is the class that manages the Data Center of Imonggo/IRetailCloud. It uses <a href="http://ormlite.com/sqlite_java_android_orm.shtml">OrmLite</a> Technology.
 * 
 * @author Rhymart P. Manchus
 * @version 10.0.1
 * @since June 13, 2013
 *
 */
public class ImonggoDBHelper extends OrmLiteSqliteOpenHelper {

	/**
	 * Database Name
	 */
	private static final String DATABASE_NAME = "imonggo.db";
	/**
	 * Database Version
	 */
	private static final int DATABASE_VERSION = 10;
	
	/*
	 * Table's contents.
	 */
	private Dao<Product, Integer> productsTable = null;
	private Dao<Setting, Integer> settingsTable = null;
	private Dao<Session, Integer> sessionTable = null;
	private Dao<User, Integer> usersTable = null;
	private Dao<Inventory, Integer> inventoryTable = null;

	/**
	 * Constructor of ImonggoDBHelper. It creates table for the following modules:
	 * <ul>
	 * 	<li>Products</li>
	 * 	<li>Settings</li>
	 * 	<li>Users</li>
	 * 	<li>Inventories</li>
	 * </ul>
	 * and creates a table for the user's Session.
	 * 
	 * @param context
	 */
	public ImonggoDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Product.class);
			TableUtils.createTable(connectionSource, Setting.class);
			TableUtils.createTable(connectionSource, Session.class);
			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, Inventory.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Product.class, true);
			TableUtils.dropTable(connectionSource, Setting.class, true);
			TableUtils.dropTable(connectionSource, Session.class, true);
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, Inventory.class, true);
			onCreate(db, connectionSource);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * Retrieve all products from the database.
	 * 
	 * @return list of products of type <b>Dao</b>
	 * @throws SQLException
	 */
	public Dao<Product, Integer> getProducts() throws SQLException {
		if(productsTable == null)
			productsTable = getDao(Product.class);
		return productsTable;
	}

	/**
	 * 
	 * Retrieve all settings from the database.
	 * 
	 * @return list of products of type <b>Dao</b>
	 * @throws SQLException
	 */
	public Dao<Setting, Integer> getSettings() throws SQLException {
		if(settingsTable == null)
			settingsTable = getDao(Setting.class);
		return settingsTable;
	}
	
	/**
	 * 
	 * Retrieve user's session from the database.
	 * 
	 * @return list of products of type <b>Dao</b>
	 * @throws SQLException
	 */
	public Dao<Session, Integer> getSession() throws SQLException {
		if(sessionTable == null)
			sessionTable = getDao(Session.class);
		return sessionTable;
	}
	
	/**
	 * 
	 * Retrieve all users from the database.
	 * 
	 * @return list of products of type <b>Dao</b>
	 * @throws SQLException
	 */
	public Dao<User, Integer> getUser() throws SQLException {
		if(usersTable == null)
			usersTable = getDao(User.class);
		return usersTable;
	}
	
	/**
	 * 
	 * Retrieve all inventories from the database.
	 * 
	 * @return list of products of type <b>Dao</b>
	 * @throws SQLException
	 */
	public Dao<Inventory, Integer> getInventories() throws SQLException {
		if(inventoryTable == null)
			inventoryTable = getDao(Inventory.class);
		return inventoryTable;
	}
	
	/**
	 * 
	 * Drop a table from the database.
	 * Possible Module values:
	 * <ul>
	 * 	<li>PRODUCTS</li>
	 * 	<li>SETTINGS</li>
	 * 	<li>SESSION</li>
	 * 	<li>USERS</li>
	 * 	<li>INVENTORIES</li>
	 * </ul>
	 * 
	 * @param modules
	 * @throws SQLException
	 */
	public void dropTable(Modules modules) throws SQLException {
		switch(modules) {
			case PRODUCTS: {
				TableUtils.dropTable(getConnectionSource(), Product.class, true);
			} break;
			case SETTINGS: {
				TableUtils.dropTable(getConnectionSource(), Setting.class, true);
			} break;
			case SESSION: {
				TableUtils.dropTable(getConnectionSource(), Session.class, true);
			} break;
			case USERS: {
				TableUtils.dropTable(getConnectionSource(), User.class, true);
			} break;
			case INVENTORIES: {
				TableUtils.dropTable(getConnectionSource(), Inventory.class, true);
			} break;
		}
	}
	
	/**
	 * 
	 * Perform a specific database operation for the table <b>Products</b>. DatabaseOperations possible values are:
	 * <ul>
	 * 	<li><b><i>INSERT</i></b>: Insert a new product item on the table.</li>
	 * 	<li><b><i>DELETE</i></b>: Delete a product item on the table.</li>
	 * 	<li><b><i>DELETEALL</i></b>: Delete all products on the table.</li>
	 * 	<li><b><i>UPDATE</i></b>: Update a product item on the table.</li>
	 * </ul>
	 * 
	 * @param product
	 * @param databaseOperations
	 */
	public void operationsProduct(Product product, DatabaseOperations databaseOperations) {
		try {
			Dao<Product, Integer> daoProducts = getProducts();
			switch(databaseOperations) {
				case INSERT: {
					daoProducts.create(product);					
				} break;
				case DELETE: {
					daoProducts.delete(product);
				} break;
				case DELETEALL: {
					daoProducts.deleteBuilder().delete();
				} break;
				case UPDATE: {
					daoProducts.update(product);
				} break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Perform a specific database operation for the table <b>Settings</b>. DatabaseOperations possible values are:
	 * <ul>
	 * 	<li><b><i>INSERT</i></b>: Insert a new settings item on the table.</li>
	 * 	<li><b><i>DELETE</i></b>: Delete a settings item on the table.</li>
	 * 	<li><b><i>DELETEALL</i></b>: Delete all settings on the table.</li>
	 * 	<li><b><i>UPDATE</i></b>: Update a settings item on the table.</li>
	 * </ul>
	 * 
	 * @param product
	 * @param databaseOperations
	 */
	public void operationsSettings(Setting setting, DatabaseOperations databaseOperations) {
		try {
			Dao<Setting, Integer> daoSettings = getSettings();
			switch(databaseOperations) {
				case INSERT: {
					daoSettings.create(setting);		
				} break;
				case DELETE: {
					daoSettings.delete(setting);
				} break;
				case DELETEALL: {
					daoSettings.deleteBuilder().delete();
				} break;
				case UPDATE: {
					daoSettings.update(setting);
				} break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Perform a specific database operation for the table <b>Session</b>. DatabaseOperations possible values are:
	 * <ul>
	 * 	<li><b><i>INSERT</i></b>: Insert a session on the table.</li>
	 * 	<li><b><i>DELETE</i></b>: Delete a session on the table.</li>
	 * 	<li><b><i>DELETEALL</i></b>: Delete all sessions on the table.</li>
	 * 	<li><b><i>UPDATE</i></b>: Update a session on the table.</li>
	 * </ul>
	 * 
	 * @param product
	 * @param databaseOperations
	 */
	public void operationsSession(Session session, DatabaseOperations databaseOperations) {
		try {
			Dao<Session, Integer> daoSession = getSession();
			switch(databaseOperations) {
				case INSERT: {
					daoSession.create(session);		
				} break;
				case DELETE: {
					daoSession.delete(session);
				} break;
				case DELETEALL: {
					daoSession.deleteBuilder().delete();
				} break;
				case UPDATE: {
					daoSession.update(session);
				} break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Perform a specific database operation for the table <b>User</b>. DatabaseOperations possible values are:
	 * <ul>
	 * 	<li><b><i>INSERT</i></b>: Insert a user on the table.</li>
	 * 	<li><b><i>DELETE</i></b>: Delete a user on the table.</li>
	 * 	<li><b><i>DELETEALL</i></b>: Delete all users on the table.</li>
	 * 	<li><b><i>UPDATE</i></b>: Update a user on the table.</li>
	 * </ul>
	 * 
	 * @param product
	 * @param databaseOperations
	 */
	public void operationsUser(User user, DatabaseOperations databaseOperations) {
		try {
			Dao<User, Integer> daoUser = getUser();
			switch(databaseOperations) {
				case INSERT: {
					daoUser.create(user);
				} break;
				case DELETE: {
					daoUser.delete(user);
				} break;
				case DELETEALL: {
					daoUser.deleteBuilder().delete();
				} break;
				case UPDATE: {
					daoUser.update(user);
				} break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Perform a specific database operation for the table <b>Inventory</b>. DatabaseOperations possible values are:
	 * <ul>
	 * 	<li><b><i>INSERT</i></b>: Insert a inventory item on the table.</li>
	 * 	<li><b><i>DELETE</i></b>: Delete a inventory item on the table.</li>
	 * 	<li><b><i>DELETEALL</i></b>: Delete all inventories on the table.</li>
	 * 	<li><b><i>UPDATE</i></b>: Update a inventory on the table.</li>
	 * </ul>
	 * 
	 * @param product
	 * @param databaseOperations
	 */
	public void operationsInventory(Inventory inventory, DatabaseOperations databaseOperations) {
		try {
			Dao<Inventory, Integer> daoInventory = getInventories();
			switch(databaseOperations) {
				case INSERT: {
					daoInventory.create(inventory);
				} break;
				case DELETE: {
					daoInventory.delete(inventory);
				} break;
				case DELETEALL: {
					daoInventory.deleteBuilder().delete();
				} break;
				case UPDATE: {
					daoInventory.update(inventory);
				} break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Delete table values of all tables on the database.
	 * <br/><br/>
	 * Hint: <i>You can use this when you logged out/deleted sessions.</i>
	 * 
	 */
	public void deleteAllDatabaseValues() {
		try {
			getInventories().deleteBuilder().delete();
			getProducts().deleteBuilder().delete();
			getSession().deleteBuilder().delete();
			getSettings().deleteBuilder().delete();
			getUser().deleteBuilder().delete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
