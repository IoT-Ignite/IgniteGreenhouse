# Overview

Ignite Greenhouse is an open-source IoT project for data collection, visualization and managing your greenhouse. You can see various sensor data from your greenhouse on your app.

Github : [github.com/IoT-Ignite/IgniteGreenhouse](https://github.com/IoT-Ignite/IgniteGreenhouse)

For our IoT project documentation visit [www.iot-ignite.com/documents](https://devzone.iot-ignite.com/documents)

## How to use

[![Watch the video](http://img.youtube.com/vi/RPMDYocyliY/0.jpg)](https://www.youtube.com/watch?v=RPMDYocyliY)

## Getting Started

What things you need to get information of your greenhouse.

### 1. [Install Ignite Greenhouse for Android](https://github.com/IoT-Ignite/IgniteGreenhouse/releases)

### 2. [Get Ignite Greenhouse Kit](https://arikovani.com/projeler/akilli-sera/detay) (Gateway, Gateway App, Sensors)
   * Hardware that used as Gateway is Raspberry Pi
   * Gateway App perform connection transaction with cloud
   * You can choose many sensors you want, e.g., soil moisture, temperature, humidity

   :cactus: You can buy a kit or can find step by step instructions to create your own kit on [here](https://iot-ignite.github.io/IgniteGreenhouse/gatewayapp/) :cactus:


### 3. Register your Ignite Greenhouse Kit with using your Android device.

* First, install the app.

* Log in.

* You are at the gateway list screen now. Click add button and add your first gateway. Scan QR code on your gateway device to register it.

* The gateway will appear on your dashboard. Now you can add another gateway or go to sensor dashboard by clicking gateway.

* On sensor dashboard you can add and register new sensors by clicking the add button. The sensors will appear on sensor dashboard.

## Technical Structure

Gateway devices are at the center of the scheme. Gateways interact other elements: Sensors, Cloud and your Android devices.

1. Sensors publish and gateways receive data with using the RF protocol.
2. The gateway sends the data to IoT-Ignite Cloud platform with using [Gateway App](https://github.com/freeloki/GreenhousePrivate/wiki) that already loaded on the gateway for collecting and processing data.
3. Cloud keeps processed data.
4. Finally, the data comes to your Android device with using Ignite Rest Client API.


## Project Dependencies

* [IoT-Ignite API](http://www.dropwizard.io/1.0.2/docs/) - IoT-Ignite platform connections
* [Ignite REST Client API](https://maven.apache.org/) - Retrofit based Android library
* [Retrofit](http://square.github.io/retrofit/) - For REST calls
* [barcodescanner](https://github.com/dm77/barcodescanner) - Using QR Scanner for register gateway and sensor
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView) - Using for loading screen
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) - Charting

