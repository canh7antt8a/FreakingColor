package com.hungle.freakingcolor;

import java.util.Arrays;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSErrorException;
import org.robovm.apple.foundation.NSMutableDictionary;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.social.SLComposeViewController;
import org.robovm.apple.social.SLServiceType;
import org.robovm.apple.uikit.UIAlertAction;
import org.robovm.apple.uikit.UIAlertActionStyle;
import org.robovm.apple.uikit.UIAlertController;
import org.robovm.apple.uikit.UIAlertControllerStyle;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIGraphics;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.pods.google.GGLContext;
import org.robovm.pods.google.analytics.GAI;
import org.robovm.pods.google.analytics.GAIDictionaryBuilder;
import org.robovm.pods.google.analytics.GAIFields;
import org.robovm.pods.google.analytics.GAILogLevel;
import org.robovm.pods.google.analytics.GAITracker;
import org.robovm.pods.google.mobileads.GADAdSize;
import org.robovm.pods.google.mobileads.GADBannerView;
import org.robovm.pods.google.mobileads.GADBannerViewDelegateAdapter;
import org.robovm.pods.google.mobileads.GADRequest;
import org.robovm.pods.google.mobileads.GADRequestError;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.ptr.BytePtr;
import org.robovm.rt.bro.ptr.IntPtr;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.badlogic.gdx.utils.Logger;
import com.hungle.freakingcolor.objects.BlockGame;
import com.hungle.freakingcolor.objects.ControlsApp;

public class FreakingColor extends IOSApplication.Delegate implements ControlsApp{

	private static final Logger log = new Logger(FreakingColor.class.getName(),
			Application.LOG_DEBUG);

	private IOSApplication iosApplication;
	private static final boolean USE_TEST_DEVICES = false;
    private String AdsID = "ca-app-pub-1098407154700376/9515464049";
    private String AnalyticsID = "UA-72259596-4";
	private GADBannerView adview;
	private boolean adsInitialized = false;
	private static final String GAME_URI_NEW = 
			"https://itunes.apple.com/us/app/freaking-of-color/id1075888275?ls=1&mt=8";
	private static final String CIRCLEJUMPING_URI_NEW = 
			"https://itunes.apple.com/us/app/circle-jumping/id1073449106?ls=1&mt=8";
	private static final String STUDIO_URI_NEW = 
			"https://itunes.apple.com/us/developer/dada-studio/id1073449105";

	@Override
	protected IOSApplication createApplication() {
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		config.orientationLandscape = false;
		config.orientationPortrait = true;
		iosApplication = new IOSApplication(new BlockGame(this), config);
		return iosApplication;
	}

	public static void main(String[] argv) {
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(argv, null, FreakingColor.class);
		pool.close();

	}
	//https://play.google.com/store/apps/details?id=com.drksft.cubedash&hl=en

	@Override
	public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
		try {
            GGLContext.getSharedInstance().configure();
        } catch (NSErrorException e) {
            System.err.println("Error configuring the Google context: " + e.getError());
        }

        // Optional: configure GAI options.
        GAI gai = GAI.getSharedInstance();
        gai.enableCrashReporting();
        gai.getLogger().setLogLevel(GAILogLevel.Verbose);
        
		boolean finished = super.didFinishLaunching(application, launchOptions);
		//FBSDKApplicationDelegate.getSharedInstance().didFinishLaunching(application, launchOptions);
		return finished;
	}

//	@Override
//	public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSPropertyList annotation) {
//		return FBSDKApplicationDelegate.getSharedInstance().openURL(application, url, sourceApplication, annotation);
//	}
//
//	@Override
//	public void didBecomeActive(UIApplication application) {
//		super.didBecomeActive(application);
//		FBSDKAppEvents.activateApp();
//	}
	
	@Override
	public void showads() {
		// TODO Auto-generated method stub
		initializeAds();
		final CGSize screenSize = UIScreen.getMainScreen().getBounds()
				.getSize();
		double screenWidth = screenSize.getWidth();

		final CGSize adSize = adview.getBounds().getSize();
		double adWidth = adSize.getWidth();
		double adHeight = adSize.getHeight();

		adview.setFrame(new CGRect((screenWidth / 2) - adWidth / 2, 0,
				adWidth, adHeight));
	}

	@Override
	public void hidads() {
		// TODO Auto-generated method stub
		initializeAds();
		final CGSize screenSize = UIScreen.getMainScreen().getBounds()
				.getSize();
		double screenWidth = screenSize.getWidth();

		final CGSize adSize = adview.getBounds().getSize();
		double adWidth = adSize.getWidth();
		double adHeight = adSize.getHeight();

		adview.setFrame(new CGRect((screenWidth / 2) - adWidth / 2, -adHeight,
				adWidth, adHeight));
	}

	@Override
	public void showFullAds() {
	}
	
	@Override
	public void shareTwitter(int score) {
		// TODO Auto-generated method stub
		if(checkNetwork()){
			UIViewController uiv = new UIViewController();
			UIApplication.getSharedApplication().getKeyWindow().
					getRootViewController().addChildViewController(uiv);
			
			if(SLComposeViewController.isAvailable(SLServiceType.Twitter)){
				SLComposeViewController slcc = new SLComposeViewController(SLServiceType.Twitter);
				slcc.setInitialText("MY BEST SCORE : "+score+"  | Link game: "+GAME_URI_NEW );
				//slcc.addURL(new NSURL(GAME_URI_NEW));
				slcc.addImage(new UIImage(new NSData(takeScreenshot())));
				uiv.presentViewController(slcc, true, null);
			}else{
				showMessage("Twitter have not installed!!","Share on Twitter");
			}
		}else{
			showMessage("Not Connection!", "Connect");
		}
	}
	
	public void showMessage( String message,String title) {
		
		UIAlertController alert = new UIAlertController(
					title,message,UIAlertControllerStyle.Alert);
		
		UIAlertAction okAction = new UIAlertAction("OK",UIAlertActionStyle.Default,
				new VoidBlock1<UIAlertAction>() {
			            @Override
			            public void invoke(UIAlertAction a) {
			                    System.err.println("Error: " + a.toString());
			            }
				});
		alert.addAction(okAction);
		
		UIViewController uiv = new UIViewController();
		UIApplication.getSharedApplication().getKeyWindow().
				getRootViewController().addChildViewController(uiv);
		uiv.presentViewController(alert, true, null);

	}

	@Override
	public void shareFacebook(int score) {
		// TODO Auto-generated method stub
		if(checkNetwork()){
			UIViewController uiv = new UIViewController();
			UIApplication.getSharedApplication().getKeyWindow().
					getRootViewController().addChildViewController(uiv);
			
			if(SLComposeViewController.isAvailable(SLServiceType.Facebook)){
				SLComposeViewController slcc = new SLComposeViewController(SLServiceType.Facebook);
				slcc.setInitialText("MY BEST SCORE : "+score+"    | Link game  ");
				slcc.addURL(new NSURL(GAME_URI_NEW));
				slcc.addImage(new UIImage(new NSData(takeScreenshot())));
				uiv.presentViewController(slcc, true, null);
			}else{
				showMessage("Facebook have not installed!!","Share on Facebook");
			}
		}else{
			showMessage("Not Connection!", "Connect");
		}
	}

	public byte[] takeScreenshot(){
		UIImage newImage;
		UIGraphics.beginImageContext(new CGSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth()));
		iosApplication.getUIViewController().getView().drawViewHierarchy(
		      new CGRect(0,-Gdx.graphics.getHeight()/2+Gdx.graphics.getWidth()/2,
		    		  Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) , true);
		newImage = UIGraphics.getImageFromCurrentImageContext();
		UIGraphics.endImageContext();  

		NSData data = newImage.toJPEGData(.6);
		newImage.dispose();

		return data.getBytes();
		}
	
	@Override
	public void rateGame() {
		// TODO Auto-generated method stub
		if(checkNetwork()){
			UIApplication
			.getSharedApplication()
			.openURL(
				new NSURL(GAME_URI_NEW));
		}else{
			showMessage("Not Connection!", "Connect");
		}
	}
	
	@Override
	public void moreApp() {
		// TODO Auto-generated method stub
		if(checkNetwork()){
			UIApplication
			.getSharedApplication()
			.openURL(
				new NSURL(STUDIO_URI_NEW));
		}else{
			showMessage("Not Connection!", "Connect");
		}
	}
	
	@Override
	public void newgameView() {
		// TODO Auto-generated method stub
		if(checkNetwork()){
			UIApplication
			.getSharedApplication()
			.openURL(
				new NSURL(CIRCLEJUMPING_URI_NEW));
		}else{
			showMessage("Not Connection!", "Connect");
		}
	}
	
	private boolean checkNetwork(){
		long targetRef = Connect.SCNetworkReachabilityCreateWithName(0, BytePtr.toBytePtrAsciiZ("www.google.com"));
		IntPtr flagsPtr = new IntPtr();
		if (Connect.SCNetworkReachabilityGetFlags(targetRef, flagsPtr)) {
		  int flags = flagsPtr.get();
		  if ((flags & Connect.Flags.Reachable) != 0) {
		     return true;
		  }
		}
		return false;
	}

	@Override
	public void pushScreen(String name) {
		// TODO Auto-generated method stub
		GAITracker tracker = GAI.getSharedInstance().getTracker(AnalyticsID);
        tracker.put(GAIFields.ScreenName(),"IOS_"+ name);
        tracker.send(GAIDictionaryBuilder.createScreenView().build());
	}

	@Override
	public void pushActions(String name, String score) {
		// TODO Auto-generated method stub
		GAITracker tracker = GAI.getSharedInstance().getTracker(AnalyticsID);
        NSMutableDictionary<?, ?> event = GAIDictionaryBuilder.createEvent(
        		"IOS", "Clicked", score, null).build();
        tracker.send(event);
	}
	
	public void initializeAds() {
		if (!adsInitialized) {

			adsInitialized = true;

			adview = new GADBannerView(GADAdSize.Banner());
			adview.setBackgroundColor(new UIColor(0, 0, 0, 0));
			adview.setAdUnitID(AdsID); // put your secret key here
			adview.setRootViewController(iosApplication.getUIViewController());
			iosApplication.getUIViewController().getView().addSubview(adview);

			final GADRequest request = new GADRequest();
			 if (USE_TEST_DEVICES) {
	                request.setTestDevices(Arrays.asList(GADRequest.getSimulatorID()));
	                Foundation.log("Test devices: " + request.getTestDevices());
	            }

			adview.setDelegate(new GADBannerViewDelegateAdapter() {
				@Override
				public void didReceiveAd(GADBannerView view) {
					super.didReceiveAd(view);
					log.debug("didReceiveAd");
				}
				@Override
				public void didFailToReceiveAd(GADBannerView view,
						GADRequestError error) {
					super.didFailToReceiveAd(view, error);
					log.debug("didFailToReceiveAd:" + error);
				}
				
			});

			adview.loadRequest(request);

			log.debug("Initalizing ads complete.");
		}
	}
}

