package net.nueca.imonggo.operations;

import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import net.nueca.database.ImonggoDBHelper;
import net.nueca.exceptions.RequestException;
import net.nueca.http.HttpRequestProperties;
import net.nueca.http.HttpsRequestProperties;
import net.nueca.http.RequestResult;
import net.nueca.imonggo.enums.DatabaseOperations;
import net.nueca.imonggo.enums.Modules;
import net.nueca.imonggo.enums.Server;
import net.nueca.imonggo.interfaces.OnHttpRequestor;
import net.nueca.imonggo.objects.Inventory;
import net.nueca.imonggo.objects.Product;
import net.nueca.imonggo.objects.Session;
import net.nueca.imonggo.objects.Setting;
import net.nueca.imonggo.objects.User;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * This is a service that sync modules from the imonggo/iretail cloud server.
 * <br/><br/>
 * Modules synced are: 
 * <ul>
 * 	<li>Users</li>
 * 	<li>Products</li>
 * 	<li>Customers</li>
 * 	<li>Inventories</li>
 * 	<li>Settings</li>
 * </ul>
 * @author rhymart
 *
 */
public class SyncModulesService extends Service implements OnHttpRequestor, HttpsRequestProperties, HttpRequestProperties {
	
	private ImonggoDBHelper dbHelper;
	private Session session;
	private Server server;
	private int page = 1;
	private boolean shouldSyncAll = true;
	private Messenger messenger_product, messenger_users, messenger_inventory, messenger_settings;
	
	/**
	 * Handler flag when the sync service has finished syncing products.
	 */
	public static final String HANDLER_KEY_PRODUCT = "handler_product";

	/**
	 * Handler flag when the sync service has finished syncing users.
	 */
	public static final String HANDLER_KEY_USERS = "handler_users";

	/**
	 * Handler flag when the sync service has finished syncing inventory.
	 */
	public static final String HANDLER_KEY_INVENTORY = "handler_inventory";

	/**
	 * Handler flag when the sync service has finished syncing settings.
	 */
	public static final String HANDLER_KEY_SETTINGS = "handler_settings";
	
	
	
	/**
	 * Set the ordinal value of Server enum. VALUE: {IMONGGO.ordinal(), IRETAILCLOUD.ordinal()}
	 */
	public static final String PARAMS_KEY_SERVER = "server";
	/**
	 * Set a boolean value if the request should be secured or not. VALUE: {true, false}
	 */
	public static final String PARAMS_KEY_ISSECURED = "is_secured";
	/**
	 * Set a boolean value if to sync all modules. [true, false]
	 */
	public static final String PARAMS_SYNC_ALL = "sync_all";
	/**
	 * Set the ordinal value of Modules enum. VALU: {PRODUCTS.ordinal(), USERS.ordinal(), CUSTOMERS.ordinal(), INVENTORY.ordinal(), SETTINGS.ordinal()}
	 */
	public static final String PARAMS_MODULE = "module";
	
	
	/*
	 * Do syncing of all the modules from imonggo.
	 * 
	 * 1. Users
	 * 2. Products
	 * 3. Customers
	 * 4. Inventory
	 * 5. Settings
	 * 
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			List<Session> sessionTable = getHelper().getSession().queryForAll();
			session = sessionTable.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle bundle = intent.getExtras();
		server = Server.values()[bundle.getInt(PARAMS_KEY_SERVER)];
		boolean isSecured = bundle.getBoolean(PARAMS_KEY_ISSECURED);
		shouldSyncAll = bundle.getBoolean(PARAMS_SYNC_ALL, true);
		
		if(shouldSyncAll)
			SyncModules.getUsers(this, session, this, this, this, server, page);
		else {
			Modules moduleToSync = Modules.values()[bundle.getInt(PARAMS_MODULE)];
			switch(moduleToSync) {
				case PRODUCTS: {
					page = 1;
					SyncModules.getProducts(this, session, this, this, this, server, page);
				} break;
				case INVENTORIES: {
					page = 1;
					SyncModules.getInventories(this, session, this, this, this, server, page);
				} break;
			}
		}
		
		if(((Messenger)bundle.get(HANDLER_KEY_PRODUCT)) != null)
			messenger_product = (Messenger) bundle.get(HANDLER_KEY_PRODUCT);
		if(((Messenger)bundle.get(HANDLER_KEY_USERS)) != null)
			messenger_users = (Messenger) bundle.get(HANDLER_KEY_USERS);
		if(((Messenger)bundle.get(HANDLER_KEY_INVENTORY)) != null)
			messenger_inventory = (Messenger) bundle.get(HANDLER_KEY_INVENTORY);
		if(((Messenger)bundle.get(HANDLER_KEY_SETTINGS)) != null)
			messenger_settings = (Messenger) bundle.get(HANDLER_KEY_SETTINGS);
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		if(dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
		super.onDestroy();
	}
	
	/**
	 * 
	 * Data retriever for Imonggo Database Data Center.
	 * 
	 * @return
	 */
	protected ImonggoDBHelper getHelper() {
		if(dbHelper == null)
			dbHelper = OpenHelperManager.getHelper(this, ImonggoDBHelper.class);
		return dbHelper;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void preExecuteOperation(int module) {
		Modules enumModules = Modules.values()[module];
		switch(enumModules) {
			case USERS: {
				
			} break;
			case PRODUCTS: {
				
			} break;
		}
	}

	@Override
	public void postExecuteOperation(int module, String result,
			boolean hasResponse) {
		if(hasResponse) {
			RequestResult reqResult = new RequestResult(result);
			try {
				Modules enumModules = Modules.values()[module];
				switch(enumModules) {
					case USERS: {
						List<User> users = null;
						try {
							users = getHelper().getUser().queryForAll();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						JSONArray arrUsers = new JSONArray(reqResult.getData());
						if(arrUsers.length() > 0) {
							if(users.size() == 0) {
								for(int i = 0;i < arrUsers.length();i++) {
									User user = new User(arrUsers.getJSONObject(i));
									dbHelper.operationsUser(user, DatabaseOperations.INSERT);
								}
							}
							else {
								for(int i = 0;i < arrUsers.length();i++) {
									User user = new User(arrUsers.getJSONObject(i));
									List<User> aUser = null;
									try {
										aUser = getHelper().getUser().queryBuilder().where().eq("id", user.getId()).query();
									} catch (SQLException e) {
										e.printStackTrace();
									}
									if(aUser.size() == 0) {
										dbHelper.operationsUser(user, DatabaseOperations.INSERT);
									}
									else if(aUser.size() > 0) {
										dbHelper.operationsUser(user, DatabaseOperations.UPDATE);
									}
								}
							}
							page++;
							SyncModules.getUsers(this, session, this, this, this, server, page);
						}
						else {
							sendMessage(Modules.USERS, true);
							page = 1;
							SyncModules.getProducts(this, session, this, this, this, server, page);
						}
					} break;
					case PRODUCTS: {
						List<Product> products = null;
						try {
							products = getHelper().getProducts().queryForAll();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JSONArray arrProducts = new JSONArray(reqResult.getData());
						if(arrProducts.length() > 0) {
							if(products.size() == 0) {
								for(int i = 0;i < arrProducts.length();i++) {
									Product product = new Product(arrProducts.getJSONObject(i));
									dbHelper.operationsProduct(product, DatabaseOperations.INSERT);
								}
							}
							else {
								for(int i = 0;i < arrProducts.length();i++) {
									Product product = new Product(arrProducts.getJSONObject(i));
									List<Product> aProduct = null;
									try {
										aProduct = getHelper().getProducts().queryBuilder().where().eq("id", product.getId()).query();
									} catch (SQLException e) {
										e.printStackTrace();
									}
									if(aProduct.size() == 0) {
										dbHelper.operationsProduct(product, DatabaseOperations.INSERT);
									}
									else if(aProduct.size() > 0) {
										dbHelper.operationsProduct(product, DatabaseOperations.UPDATE);
									}
								}
							}
							page++;
							SyncModules.getProducts(this, session, this, this, this, server, page);
						}
						else {
							sendMessage(Modules.PRODUCTS, true);
							page = 1;
							SyncModules.getInventories(this, session, this, this, this, server, page);
						}
					} break;
					case INVENTORIES: {
						List<Inventory> inventories = null;
						try {
							inventories = getHelper().getInventories().queryForAll();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						JSONArray arrInventories = new JSONArray(reqResult.getData());
						if(arrInventories.length() > 0) {
							if(inventories.size() == 0) {
								for(int i = 0;i < arrInventories.length();i++) {
									Inventory inventory = new Inventory(arrInventories.getJSONObject(i));
									dbHelper.operationsInventory(inventory, DatabaseOperations.INSERT);
								}
							}
							else {
								for(int i = 0;i < arrInventories.length();i++) {
									Inventory inventory = new Inventory(arrInventories.getJSONObject(i));
									List<Inventory> aInventory = null;
									try {
										aInventory = getHelper().getInventories().queryBuilder().where().eq("product_id", inventory.getProduct_id()).query();
									} catch (SQLException e) {
										e.printStackTrace();
									}
									if(aInventory.size() == 0) {
										dbHelper.operationsInventory(inventory, DatabaseOperations.INSERT);
									}
									else if(aInventory.size() > 0) {
										dbHelper.operationsInventory(inventory, DatabaseOperations.UPDATE);
									}
								}
							}
							page++;
							SyncModules.getInventories(this, session, this, this, this, server, page);
						}
						else {
							sendMessage(Modules.INVENTORIES, true);
							page = 1;
							if(shouldSyncAll)
								SyncModules.getSettings(this, session, this, this, this, server);
							else
								stopSelf();
						}
					} break;
					case SETTINGS: {
						List<Setting> settings = null;
						try {
							settings = getHelper().getSettings().queryForAll();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JSONArray arrSettings = new JSONArray(reqResult.getData());
						if(settings.size() == 0) {
							for(int i = 0;i < arrSettings.length();i++) {
								Setting setting = new Setting(arrSettings.getJSONObject(i));
								dbHelper.operationsSettings(setting, DatabaseOperations.INSERT);
							}
						}
						else {
							for(int i = 0;i < arrSettings.length();i++) {
								Setting setting = new Setting(arrSettings.getJSONObject(i));
								List<Setting> aSetting = null;
								try {
									aSetting = getHelper().getSettings().queryBuilder().where().eq("name", setting.getName()).query();
								} catch (SQLException e) {
									e.printStackTrace();
								}
								if(aSetting.size() == 0) {
									dbHelper.operationsSettings(setting, DatabaseOperations.INSERT);
								}
								else if(aSetting.size() > 0) {
									dbHelper.operationsSettings(setting, DatabaseOperations.UPDATE);
								}
							}
						}
						sendMessage(Modules.SETTINGS, true);
						stopSelf();
					} break;
				}
			} catch(RequestException e) {
				sendMessage(Modules.ALL, false);
				stopSelf();
				e.printStackTrace();
			} catch(JSONException e) {
				sendMessage(Modules.ALL, true);
				stopSelf();
				e.printStackTrace();
			}
		}
		else {
			sendMessage(Modules.ALL, true);
			stopSelf();
			Log.e("Sync module has ", "no response."+result);
			page = 1;
		}
	}
	
	private void sendMessage(Modules module, boolean isRequestOk) {
		switch(module) {
			case PRODUCTS: {
				if(messenger_product != null) {
					Bundle data = new Bundle();
					data.putBoolean("request_ok", isRequestOk);
					Message msg = Message.obtain();
					msg.setData(data);
					try {
						messenger_product.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} break;
			case INVENTORIES: {
				if(messenger_inventory != null) {
					Bundle data = new Bundle();
					data.putBoolean("request_ok", isRequestOk);
					Message msg = Message.obtain();
					msg.setData(data);
					try {
						messenger_inventory.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} break;
			case SETTINGS: {
				if(messenger_settings != null) {
					Bundle data = new Bundle();
					data.putBoolean("request_ok", isRequestOk);
					Message msg = Message.obtain();
					msg.setData(data);
					try {
						messenger_settings.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} break;
			case USERS: {
				if(messenger_users != null) {
					Bundle data = new Bundle();
					data.putBoolean("request_ok", isRequestOk);
					Message msg = Message.obtain();
					msg.setData(data);
					try {
						messenger_users.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} break;
			case ALL: {
				Bundle data = new Bundle();
				data.putBoolean("request_ok", isRequestOk);
				Message msg = Message.obtain();
				msg.setData(data);
				if(messenger_product != null) {
					try {
						messenger_product.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else if(messenger_inventory != null) {
					try {
						messenger_inventory.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else if(messenger_settings != null) {
					try {
						messenger_settings.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} else if(messenger_users != null) {
					try {
						messenger_product.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			} break;
		}
	}

	@Override
	public void requestGetAddProperty(HttpsURLConnection conn) {
		conn.setRequestProperty("Authorization", "Basic "+session.getApiAuthentication());
	}

	@Override
	public void requestPostAddProperty(HttpsURLConnection conn) {
		conn.setRequestProperty("Authorization", "Basic "+session.getApiAuthentication());
	}

	@Override
	public void requestPutAddProperty(HttpsURLConnection conn) { }

	@Override
	public void requestDeleteAddProperty(HttpsURLConnection conn) { }

	
	@Override
	public void requestGetAddProperty(HttpURLConnection conn) {
		conn.setRequestProperty("Authorization", "Basic "+session.getApiAuthentication());
	}

	@Override
	public void requestPostAddProperty(HttpURLConnection conn) {
		conn.setRequestProperty("Authorization", "Basic "+session.getApiAuthentication());
	}

	@Override
	public void requestPutAddProperty(HttpURLConnection conn) { }

	@Override
	public void requestDeleteAddProperty(HttpURLConnection conn) { }

}
