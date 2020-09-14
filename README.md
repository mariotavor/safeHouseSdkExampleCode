safeHouse sdk library
==================================

Demonstrates the integration of the SafeHouse service through the usage of SafeHouse sdk library.


Introduction
============

This sample shows a simple way of getting a secure vpn connection, by using a dedicated safehouse library.
Through simple steps, the service can be integrated.

Permissions for the Vpn will be requested upon tapping the connect button.

To run this sample, **internet connection must be available**.


Prerequisites
--------------
- Android API Level >v19

Getting Started
---------------

First and foremost, download this git repo example code.
Then, open it in Android Studio and follow the example code in the main activity.

Then, go through the following steps in order to get the example working
(Please note that we are currently in optimization phases and therefore some of the next steps are temporary and will be change promtly).

0. take the following AAR which resides inside the libs/ folder in the example code
   app, config, crypto, native, util


1. Modify your application/project gradle to the gradle as shown in this example.

2. Android manifest: add the following

  1. tools:replace="android:allowBackup,android:icon,android:label,android:theme,android:name" (**in case of xml merging errors**:)


3. module build.gradle
add the following dependencies:

dependencies {
//under libs folder we will put the sdk as an aar library
 implementation fileTree(dir: 'libs', include: ['*.jar'])

//add the dependency to the safehouseLib-release@aar
   api files('libs/app.aar')
   api files('libs/util.aar')
   api files('libs/crypto.aar')
   api files('libs/config.aar')
   api files('libs/native.aar')

//add the dependencies below (temporary and will be remove shortly)
    implementation 'net.sourceforge.streamsupport:android-retrofuture:1.7.1'
    implementation 'com.jakewharton.timber:timber:4.7.1'
    implementation 'com.contrarywind:Android-PickerView:4.1.8'
    implementation "androidx.core:core-ktx:1.3.0-alpha01"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"
    implementation 'androidx.slice:slice-builders:1.1.0-alpha01'
    implementation 'androidx.slice:slice-builders-ktx:1.0.0-alpha07'
    implementation 'androidx.slice:slice-core:1.1.0-alpha01'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    implementation 'com.squareup.okhttp3:okhttp:3.4.1'
    api 'com.squareup.retrofit2:retrofit:2.9.0'
    api 'com.squareup.retrofit2:converter-gson:2.9.0'
    api 'com.jakewharton.threetenabp:threetenabp:1.2.2'
    implementation 'androidx.multidex:multidex:2.0.1'
    implementation 'javax.inject:javax.inject:1'
    implementation 'androidx.annotation:annotation:1.1.0'
    implementation 'com.google.dagger:dagger:2.26'
    kapt 'com.google.dagger:dagger-compiler:2.26'


3. To access VPN feature, please follow the below mentioned steps:

  //Extend your Application class to com.wireguard.android.SafeHouseSDK.Application.
  
  public class Master extends Application
  
  //Call SafeHouse SDK init
  
  SafeHouseSDK.init(getApplicationContext(), BuildConfig.APPLICATION_ID, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME)
  
  //Extend your activity with com.wireguard.android.activity.BaseActivity
  
   MainActivity extends BaseActivity

  //Implement SafeHouseConnectionCallback on your Activity. You will receive override methods of it. Also, register this callback on onCreate.

   onVPNConnect, onVPNDisconnect, onConnectionError

   OnCreate(){registerToSafeHouseVpnStatus(this)}

   //Implement SafeHouseGetServersCallBack on your Activity in order to fetch list of available Servers. Also, register this callback on onCreate.

    onGetRegionSuccessfully, onGetRegionError

    OnCreate(){registerToSafeHouseServerStatus(this)}

   
   Note: It's necessay to set Region before start VPN connection. You will get Region list on onGetRegionSuccessfully callback.
   
   //To Check if VPN is alreadyConnected
   
   isVPNConnected()
   
   //To connect VPN

   connectVPN()
   
   //To disconnect VPN
   
   disconnectVPN()

  //To get Server regions.

   getServerRegions()

   //To set Server region

    setServerRegion(serverRegion)
  
Note: You may need to change your app folder to some other name.


      For Server regions we simplified it to 2 regions for now due to security concerns.

Known issues
------------
- xml merging issues may appear as shown above
- in case of conflict hard to resolve please contact your SafeHouse representative
    
Screenshot
----------

![2020-04-01](https://user-images.githubusercontent.com/27682184/78152594-a6f0d800-7457-11ea-81f9-dbba94163788.jpg)
  
