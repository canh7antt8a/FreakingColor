package com.hungle.freakingcolor.tap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.hungle.freakingcolor.objects.BlockGame;
import com.hungle.freakingcolor.objects.ControlsApp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class AndroidLauncher2 extends AndroidApplication implements ControlsApp {

	private static final String AD_BANNER_UNIT_ID = "ca-app-pub-1098407154700376/9515464049";
//	protected AdView adView;
//	protected AdView bannerAd;

	private final int NOT_INSTALL_INSTAGRAM = 1;
	private final int NOT_INSTALL_TWITTER = 2;
	private final int SHOW_LOADING_FACE = 3;
	private final int DISMIS_LOADING_FACE = 4;
	private final int NOT_SHARE_FACEBOOK = 5;
	private final int NOT_CONNECTION = 6;
	private final int SHOW_TOAST = 7;
	private final int SHOW_RATE_APP = 8;
	private final int SHOW_MORE_APP = 9;

	ProgressDialog prgd;
//	private CallbackManager callbackManager;
	RelativeLayout layout;
	private View decorView;

	String packageCircleJumping = "com.hungle.circlejump.android";
	String packageName = "com.hungle.freakingcolor.tap";
	String urlGPlay = "https://play.google.com/store/apps/details?id=" + packageName;
	String urlGPlayCircleJumping = "https://play.google.com/store/apps/details?id=" + packageCircleJumping;
	String urlRate = "market://details?id=" + packageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		FacebookSdk.sdkInitialize(getApplicationContext());
//		callbackManager = CallbackManager.Factory.create();
//		LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//			@Override
//			public void onSuccess(LoginResult loginResult) {
//			}
//
//			@Override
//			public void onCancel() {
//			}
//
//			@Override
//			public void onError(FacebookException exception) {
//			}
//		});

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		View gameView = initializeForView(new BlockGame(this), config);
		setupAds();

//		layout = new RelativeLayout(this);
//		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//				ViewGroup.LayoutParams.WRAP_CONTENT);
//
//		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		params.addRule(RelativeLayout.CENTER_HORIZONTAL);

//		layout.addView(bannerAd, params);

//		decorView = getWindow().getDecorView();
//
//		setContentView(layout);

	}

//	public enum TrackerName {
//		APP_TRACKER, // Tracker used only in this app.
//		GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg:
//						// roll-up tracking.
//		ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a
//							// company.
//	}
//
//	HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
//
//	synchronized Tracker getTracker(TrackerName trackerId) {
//		if (!mTrackers.containsKey(trackerId)) {
//
//			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//			Tracker t = analytics.newTracker(R.xml.app_tracker);
//			mTrackers.put(trackerId, t);
//		}
//		return mTrackers.get(trackerId);
//	}
//
//	public void setTrackerScreenName(String name) {
//		Tracker t = getTracker(TrackerName.APP_TRACKER);
//		t.setScreenName("ANDROID_" + name);
//		t.send(new HitBuilders.ScreenViewBuilder().build());
//	}
//
//	public void setActionAnalytics(String target, String value) {
//		Tracker t = getTracker(TrackerName.APP_TRACKER);
//		t.set(target, value);
//		t.send(new HitBuilders.EventBuilder().setCategory("ANDROID_" + target).setAction("Clicked: ")
//				.setLabel("score: " + value).build());
//	}

	@Override
	protected void onStart() {
		super.onStart();
//		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
//		GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}

	@SuppressLint("InlinedApi")
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
		}
	}

	private void setupAds() {
//		bannerAd = new AdView(this);
//		bannerAd.setVisibility(View.INVISIBLE);
//		bannerAd.setBackgroundColor(color.transparent);
//		bannerAd.setAdUnitId(AD_BANNER_UNIT_ID);
//		bannerAd.setAdSize(AdSize.BANNER);
	}

	// ==================================================================

	@Override
	protected void onResume() {
		super.onResume();

//		AppEventsLogger.activateApp(this);

	}

	@Override
	protected void onPause() {
		super.onPause();

//		AppEventsLogger.deactivateApp(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

	// ==================================================================

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_LOADING_FACE: {
				if (prgd == null) {
					prgd = new ProgressDialog(AndroidLauncher2.this);
				}
				prgd.show();
				break;
			}
			case DISMIS_LOADING_FACE: {
				if (prgd != null && prgd.isShowing()) {
					prgd.dismiss();
				}
				break;
			}
			case NOT_INSTALL_TWITTER: {
				Toast.makeText(getApplicationContext(), "Twitter app is not installed", Toast.LENGTH_LONG).show();
				break;
			}
			case NOT_INSTALL_INSTAGRAM: {
				Toast.makeText(getApplicationContext(), "Instagram app is not installed", Toast.LENGTH_LONG).show();
				break;
			}
			case NOT_SHARE_FACEBOOK: {
				if (prgd != null && prgd.isShowing()) {
					prgd.dismiss();
				}
				Toast.makeText(getApplicationContext(), "Can't share Facebook", Toast.LENGTH_LONG).show();
				break;
			}
			case NOT_CONNECTION: {
				if (prgd != null && prgd.isShowing()) {
					prgd.dismiss();
				}
				Toast.makeText(getApplicationContext(), "No connection!", Toast.LENGTH_LONG).show();
				break;
			}
			case SHOW_TOAST: {
				String text = (String) msg.obj;
				if (text != null && !text.equals("")) {
					Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
				}
				break;
			}
			case SHOW_RATE_APP: {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlRate)));
				break;
			}
			case SHOW_MORE_APP: {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Dada Studio")));
				break;
			}
			}
		}
	};

	// ===============================================================================

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private Bitmap saveScreen() {
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int w = size.x;
		int h = size.y;
		layout.setDrawingCacheEnabled(true);
		return Bitmap.createBitmap(layout.getDrawingCache(),
				0,h/2-w/2,w,w);
	}

	private static File saveBitmap(Bitmap bm, String fileName) {
		final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		File file = new File(dir, fileName);
		try {
			FileOutputStream fOut = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
			fOut.flush();
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public void shareTwitter() {

		boolean haveTwitter = false;
		//File file = saveBitmap(saveScreen(), "snapshot1.png");
//		File tmpFile = new File(ScreenshotFactory.saveScreenshot(
//				Gdx.files.getExternalStoragePath()+"snapshot.png"));
		
		Intent share = new Intent(android.content.Intent.ACTION_SEND);
		share.setType("text/plain");
		share.putExtra(Intent.EXTRA_SUBJECT,"This is a crazy game #FreakingColor");
		share.putExtra(Intent.EXTRA_TEXT, urlGPlay);
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(share, 0);
		if(checkConnect(getApplicationContext() )){
			
		}
		for (final ResolveInfo app : activityList) {
			if (app.activityInfo.name.startsWith("com.twitter.android")) {

				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
				share.addCategory(Intent.CATEGORY_LAUNCHER);
				share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				share.setComponent(name);
				startActivity(share);
				haveTwitter = true;
				break;

			}
		}
		if (!haveTwitter) {
			handler.sendEmptyMessage(NOT_INSTALL_TWITTER);
		}

	}

	private void publishFeedDialog() {

//		String filePath = Gdx.files.getExternalStoragePath() + "snapshot.png";
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//		Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
//
//		SharePhoto photo = new SharePhoto.Builder().setBitmap(bitmap).build();
//		SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
//
//		ShareDialog.show(this, content);
//
//		handler.sendEmptyMessage(DISMIS_LOADING_FACE);
	}

	// ===================================================================================

	@Override
	public void showads() {
		// TODO Auto-generated method stub
//		runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				bannerAd.setVisibility(View.VISIBLE);
//				AdRequest.Builder builder = new AdRequest.Builder();
//				AdRequest ad = builder.build();
//				bannerAd.loadAd(ad);
//			}
//		});
	}

	@Override
	public void hidads() {
		// TODO Auto-generated method stub
//		runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				bannerAd.setVisibility(View.INVISIBLE);
//			}
//		});
	}

	@Override
	public void showFullAds() {
		// TODO Auto-generated method stub

	}

	@Override
	public void shareFacebook(int score) {
		// TODO Auto-generated method stub
//		if (checkConnect(getApplicationContext())) {
//			if (ShareDialog.canShow(ShareLinkContent.class)) {
//				ShareLinkContent linkContent = new ShareLinkContent.Builder()
//						.setContentUrl(Uri.parse(urlGPlay))
//						.setContentTitle("MY BEST SCORE : " + score)
//						.setImageUrl(Uri.parse("http://imgur.com/dUnUTEk.png"))
//						.setContentDescription("").build();
//				ShareDialog.show(this, linkContent);
//			} else {
//				handler.sendEmptyMessage(NOT_SHARE_FACEBOOK);
//			}
//		} else {
//			handler.sendEmptyMessage(NOT_CONNECTION);
//		}
	}

	@Override
	public void shareTwitter(int score) {
		// TODO Auto-generated method stub
		
		if (checkConnect(getApplicationContext())) {
			shareTwitter();
		} else {
			handler.sendEmptyMessage(NOT_CONNECTION);
		}
	}

	@Override
	public void rateGame() {
		// TODO Auto-generated method stub
		if (checkConnect(getApplicationContext())) {
			Message msg = handler.obtainMessage(SHOW_RATE_APP);
			handler.sendMessage(msg);
		} else {
			handler.sendEmptyMessage(NOT_CONNECTION);
		}
	}

	@Override
	public void pushScreen(String name) {
		// TODO Auto-generated method stub
//		setTrackerScreenName(name);
	}

	@Override
	public void pushActions(String name, String score) {
		// TODO Auto-generated method stub
//		setActionAnalytics(name, score);
	}

	@Override
	public void moreApp() {
		// TODO Auto-generated method stub
		if (checkConnect(getApplicationContext())) {
			Message msg = handler.obtainMessage(SHOW_MORE_APP);
			handler.sendMessage(msg);
		} else {
			handler.sendEmptyMessage(NOT_CONNECTION);
		}
	}

	@Override
	public void newgameView() {
		// TODO Auto-generated method stub
		if (checkConnect(getApplicationContext())) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlGPlayCircleJumping)));
		} else {
			handler.sendEmptyMessage(NOT_CONNECTION);
		}
	}
	
	public boolean checkConnect(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		boolean is3g = false;
		// For 3G check with device have card3g
		if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
			is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		}
		boolean isWifi = false;
		// For WiFi Check
		if (manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null) {
			isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		}

		if (!is3g && !isWifi)
			return false;
		return true;
	}

}
