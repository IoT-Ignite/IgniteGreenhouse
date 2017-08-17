# Ignite Greenhouse for Android
Ignite Greenhouse is an open-source IoT project for data collection, visualization and managing your greenhouse. You can see various sensor data from your greenhouse on your app.

For more details about project visit [Github Page](https://iot-ignite.github.io/IgniteGreenhouse).

## Getting Started

What things you need to get information of your greenhouse.

1. [Install Ignite Greenhouse for Android](https://play.google.com/store/apps/details?id=com.ardic.android.kuuklaparentalcontrol)

2. [Get Ignite Greenhouse Kit](https://www.iot-ignite.com/) (Gateway, Gateway App, Sensors)

   * Hardware that used as Gateway is Raspberry Pi
   * Gateway App perform connection transaction with cloud
   * You can choose many sensors you want, e.g., soil moisture, temperature, humidity
   
3. Register your Ignite Greenhouse Kit with using your Android device.

### Registering your Ignite Greenhouse Kit

* First, install the app.

* Log in. 

* You are at the gateway list screen now. Click add button and add your first gateway. Scan QR code on your gateway device to register it.

* The gateway will appear on your dashboard. Now you can add another gateway or go to sensor dashboard by clicking gateway.

* On sensor dashboard you can add and register new sensors by clicking the add button. The sensors will appear on sensor dashboard. 

## How It Works?

Gateway devices are at the center of the scheme. Gateways interact other elements: Sensors, Cloud and your Android devices.

1. Sensors publish and gateways receive data with using the RF protocol. 
2. The gateway sends the data to IoT-Ignite Cloud platform with using [Gateway App](https://iot-ignite.github.io/IgniteGreenhouse/gatewayapp/) that already loaded on the gateway for collecting and processing data.
3. Cloud keeps processed data. 
4. Finally, the data comes to your Android device with using Ignite Rest Client API.

## Project Dependencies

* [IoT-Ignite API](https://devzone.iot-ignite.com/knowledge-base/) - The web framework used
* [Ignite REST Client API](https://github.com/IoT-Ignite/IgniteRestClientLibrary) - Dependency Management
* [Retrofit](http://square.github.io/retrofit/) - For REST calls
* [barcodescanner](https://github.com/dm77/barcodescanner) - Using QR Scanner for register gateway and sensor
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView) - Using for loading screen
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) - Charting

## Authors

* **ARDIC R&D** - [http://www.ardictech.com/](http://www.ardictech.com/)

