# Ignite Greenhouse for Android
Ignite Greenhouse is an open-source IoT autonomous greenhouse project for data collection, visualization, and device management. You can see various sensor data from your greenhouse on your Android device.

## Getting Started

What things you need to get information of your greenhouse.

1. [Install Ignite Greenhouse for Android](https://play.google.com/store/apps/details?id=com.ardic.android.kuuklaparentalcontrol)

2. [Get Ignite Greenhouse Kit](https://www.iot-ignite.com/) (Gateway, Gateway App, Sensors)

   * Hardware that used as Gateway is Raspberry Pi
   * Gateway App perform connection transaction with cloud
   * You can choose many sensors you want, e.g., soil moisture, temperature, humidity
   
  :zap: Or you can make your own Greenhouse Kit by following [step by step series of examples](www.iot-ignite.com) that tell you how to get a development kit. :zap:
   
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
2. The gateway sends the data to IoT-Ignite Cloud platform with using [Gateway App](https://github.com/freeloki/GreenhousePrivate/wiki) that already loaded on the gateway for collecting and processing data.
3. Cloud keeps processed data. 
4. Finally, the data comes to your Android device with using Ignite Rest Client API.

For more technical details about APIs that used in this application, visit [Wiki](https://github.com/freeloki/GreenhousePrivate/wiki) page.

## Project Dependencies

* [IoT-Ignite API](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Ignite REST Client API](https://maven.apache.org/) - Dependency Management
* [Retrofit](http://square.github.io/retrofit/) - For REST calls
* [barcodescanner](https://github.com/dm77/barcodescanner) - Using QR Scanner for register gateway and sensor
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView) - Using for loading screen
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) - Charting

## Authors

* **ARDIC R&D** - [http://www.ardictech.com/](http://www.ardictech.com/)

