package net.nueca.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImonggoDBManager extends SQLiteOpenHelper {
	
	public static final int VERSION = 1;
	public static final String DATABASE_NAME = "imonggo";
	
	/** Products Table **/
	public static final String PRODUCTS_TABLE_NAME = "products";
	public static final String PRODUCT_ID = "id";
	public static final String STOCK_NO = "stock_no";
	public static final String NAME = "name";
	public static final String COST = "cost";
	public static final String RETAIL_PRICE = "retail_price";
	public static final String DESCRIPTION = "description";
	public static final String ALLOW_DECIMAL_QUANTITIES = "allow_decimal_quantities";
	public static final String DISABLE_DISCOUNT = "disable_discount";
	public static final String DISABLE_INVENTORY = "disable_inventory";
	public static final String ENABLE_OPEN_PRICE = "enable_open_price";
	public static final String TAX_EXEMPT = "tax_exempt";
	public static final String TAG_LIST = "tag_list";
	public static final String BARCODE_LIST = "barcode_list";
	public static final String THUMBNAIL_URL = "thumbnail_url";
	public static final String BRANCH_PRICES = "branch_prices";
	public static final String TAX_RATES = "tax_rates";
	public static final String STATUS = "status";
	
	private static final String PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " +PRODUCTS_TABLE_NAME + " ("
			+ PRODUCT_ID +  " INTEGER PRIMARY KEY, "
			+ STOCK_NO + " TEXT, "
			+ NAME + " TEXT, "
			+ COST + " TEXT, "
			+ RETAIL_PRICE + " TEXT, "
			+ DESCRIPTION + " TEXT, "
			+ ALLOW_DECIMAL_QUANTITIES + " TEXT, "
			+ DISABLE_DISCOUNT + " TEXT, "
			+ DISABLE_INVENTORY + " TEXT, "
			+ ENABLE_OPEN_PRICE + " TEXT, "
			+ TAX_EXEMPT + " TEXT, "
			+ TAG_LIST + " TEXT, "
			+ BARCODE_LIST + " TEXT, "
			+ THUMBNAIL_URL + " TEXT, "
			+ BRANCH_PRICES + " TEXT, "
			+ TAX_RATES + " TEXT, "
			+ STATUS + " TEXT); ";
	
	/** Settings table **/
	public static final String SETTINGS_TABLE_NAME = "settings";
	public static final String SETTINGS_ID = "id";
	public static final String SETTINGS_VALUE = "value";
	public static final String SETTINGS_NAME = "name";
	
	private static final String SETTINGS_TABLE = "CREATE TABLE IF NOT EXISTS " + SETTINGS_TABLE_NAME + " ("
			+ SETTINGS_ID + " INTEGER PRIMARY KEY, "
			+ SETTINGS_VALUE + " TEXT, "
			+ SETTINGS_NAME + " TEXT); ";
	
	/** Tax Settings table **/
	public static final String TAX_SETTINGS_TABLE_NAME = "tax_settings";
	public static final String TAX_SETTINGS_ID = "id";
	public static final String TAX_SETTINGS_TOCOMPUTETAX = "compute_tax";
	public static final String TAX_SETTINGS_ISTAXINCLUSIVE = "tax_inclusive";
	public static final String TAX_SETTINGS_TAXRATES = "tax_rates";
	
	public ImonggoDBManager(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PRODUCTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + PRODUCTS_TABLE_NAME);
		onCreate(db);
	}
}
