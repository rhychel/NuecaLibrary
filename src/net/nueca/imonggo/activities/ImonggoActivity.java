package net.nueca.imonggo.activities;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import net.nueca.database.ImonggoDBHelper;
import android.app.Activity;
import android.content.Context;

public class ImonggoActivity extends Activity {
	
	private ImonggoDBHelper dbHelper;
	
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.memoryCacheSize(5 * 1024 * 1024) // 5 Mb
			.denyCacheImageMultipleSizesInMemory()
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.tasksProcessingOrder(QueueProcessingType.LIFO)
			.enableLogging() // Not necessary in common
			.build();
		
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		
	}
	
	@Override
	protected void onDestroy() {
		if(dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
		super.onDestroy();
	}
	
	protected ImonggoDBHelper getHelper() {
		if(dbHelper == null)
			dbHelper = OpenHelperManager.getHelper(this, ImonggoDBHelper.class);
		return dbHelper;
	}

}
