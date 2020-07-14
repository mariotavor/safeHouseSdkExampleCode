safeHouse sdk library version
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
Than, open it in Android Studio and follow the example code in the main activity.

Then, go through the following steps in order to get the example working
(Please note that we are currently in optimization phases and therefore some of the next steps are temporary and will be change promtly).

0. take the sdk wireguard_sdk which resides inside the libs/ folder in the example code
1. Add the following files to your project at root folder as shown in this example
   dependencies.gradle
   spotless.gradle
   spotless.license
   spotless.root.gradle

2. Modify your application/project gradle to the gradle as shown in this example. Also, change your main module name at line 49 in Project Gradle.

3. Remove apply plugin: 'com.android.application' from your main gradle as same already been implemented in new project gradle.

4. Android manifest: add the following

  1. tools:replace="android:allowBackup,android:icon,android:label,android:theme,android:name" (**in case of xml merging errors**:)


5. module build.gradle
add the following dependencies:

dependencies {
//under libs folder we will put the sdk as an aar library
 implementation fileTree(dir: 'libs', include: ['*.jar'])

//add the dependency to the safehouseLib-release@aar
  implementation files('libs/wireguard_sdk.aar')

//add the dependencies below (temporary and will be remove shortly)
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
implementation 'com.contrarywind:Android-PickerView:4.1.8'
implementation 'com.google.android.material:material:1.1.0-alpha09'


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

   
   Note: It's necessay to pass any location before start VPN connection.
   
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
  
