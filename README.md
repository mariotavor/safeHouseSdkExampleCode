safeHouse sdk library version 1.25
==================================

Demonstrates the integration of the SafeHouse service through the usage of SafeHouse sdk library.


Introduction
============

This sample shows a simple way of getting a secure vpn connection, by using a dedicated safehouse widget.
The SafeHouseConnectionButton is easily used by integrating the button into an app's xml layout.
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

0. take the sdk the resides inside the libs/ folder in the example code

1. Android manifest: add the following 

  1.tools:replace="android:allowBackup,android:icon,android:label,android:theme" (**in case of xml merging errors**:)
  
  2.Add the following permision:
  
   uses-permission android:name="android.permission.FOREGROUND_SERVICE"

2. module build.gradle
add the following dependencies:

dependencies {
//under libs folder we will put the sdk as an aar library
implementation fileTree(dir: 'libs', include: ['*.jar,*.aar'])

//add the dependency to the safehouseLib-release@aar
 implementation(':safehouseLib-release_latest@aar'){transitive=true}

//add the dependencies below (temporary and will be remove shortly)
implementation("androidx.appcompat:appcompat:1.1.0")
implementation("androidx.annotation:annotation:1.1.0'")
implementation("androidx.cardview:cardview:1.0.0")
implementation("androidx.constraintlayout:constraintlayout:1.1.3")
implementation("androidx.core:core-ktx:+")
implementation("com.jakewharton.timber:timber:4.7.1")
implementation("com.google.firebase:firebase-messaging:20.1.0")
implementation("com.contrarywind:Android-PickerView:4.1.8")


3. To access VPN feature, please follow the below mentioned steps:

  //Initialise SafeHouseConnectionButton object.
  
  SafeHouseConnectionButton safeHouseConnectionButton = new SafeHouseConnectionButton(context);
  
  //Register callback to recieve VPN status
  
  safeHouseConnectionButton.registerToSafeHouseVpnStatus(SafeHouseConnectionCallbacks). After this you will find runtime VPN     Connection status like LEVEL_CONNECTED, LEVEL_NOTCONNECTED and so on. 
  
  //This method is used to find all available country
  
   getAllAvailableServerLocation();

  //This method is used to pass the user selection location to the sdk.
  
   setAvailableLocation(getAllAvailableServerLocation().get(0));
   
   Note: It's necessay to pass any location before start VPN connection.
   
   //Check if VPN is alreadyConnected
   
   isVPNConnected()
   
   //To connect VPN

   connectToVPN()
   
   //To disconnect VPN
   
   disconnectVPNConnection()
   
   // To retry
   
   retryConnection()
  

Known issues
------------
- xml merging issues may appear as shown above
- in case of conflict hard to resolve please contact your SafeHouse representative
    
Screenshot
----------

![2020-04-01](https://user-images.githubusercontent.com/27682184/78152594-a6f0d800-7457-11ea-81f9-dbba94163788.jpg)
  
