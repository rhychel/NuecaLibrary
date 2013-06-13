package net.nueca.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ImonggoContentProvider extends ContentProvider {
	
	private ImonggoDBManager dbManager;
	
	public static final String AUTH = "net.nueca.database.providers.imonggocontentprovider";
	public static final Uri CONTENT_URI_PRODUCTS = Uri.parse("content://" + AUTH + "." + ImonggoConstants.CP_PRODUCTS);
	public static final Uri CONTENT_URI_SETTINGS = Uri.parse("content://" + AUTH + "." + ImonggoConstants.CP_SETTINGS);
	public static final Uri CONTENT_URI_CUSTOMERS = Uri.parse("content://" + AUTH + "." + ImonggoConstants.CP_CUSTOMERS);
	public static final Uri CONTENT_URI_TOKENS = Uri.parse("content://" + AUTH + "." + ImonggoConstants.CP_TOKENS);
	public static final Uri CONTENT_URI_TAXSETTINGS = Uri.parse("content://" + AUTH + "." + ImonggoConstants.CP_TAXSETTINGS);
	
	@Override
	public boolean onCreate() {
		dbManager = new ImonggoDBManager(getContext());
		return false;
	}

	@Override
	public int delete(Uri uri, String where, String[] args) {
		String table = uri.getLastPathSegment();
		SQLiteDatabase database = dbManager.getWritableDatabase();
		return database.delete(table, where, args);
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String table = uri.getLastPathSegment();
		SQLiteDatabase database = dbManager.getWritableDatabase();
		long value = database.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String table = uri.getLastPathSegment();
		SQLiteDatabase database = dbManager.getReadableDatabase();
		Cursor cursor = database.query(table, projection, selection, selectionArgs, null, null, sortOrder);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] args) {
		String table = uri.getLastPathSegment();
		SQLiteDatabase database = dbManager.getWritableDatabase();  
		return database.update(table, values, where, args);
	}

}
