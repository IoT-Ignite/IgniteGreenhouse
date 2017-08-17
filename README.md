# Overview

Ignite Greenhouse is an open-source IoT project for data collection, visualization and mobile management of a greenhouse. Data from sensors can be monitored on [this app](https://github.com/IoT-Ignite/IgniteGreenhouse/releases) or [web console](http://ignitegreenhouse.com/).

Github : [github.com/IoT-Ignite/IgniteGreenhouse](https://github.com/IoT-Ignite/IgniteGreenhouse)

# How to use

[![Watch the video](http://img.youtube.com/vi/w6tWYsvksHo/0.jpg)](https://www.youtube.com/watch?v=w6tWYsvksHo&feature=youtu.be)

# Getting Started

What things you need to get information of your greenhouse.

## 1. [Install Ignite Greenhouse for Android](https://github.com/IoT-Ignite/IgniteGreenhouse/releases)

## 2. [Get Ignite Greenhouse Kit](https://www.iot-ignite.com/contact) (Gateway, Gateway App, Sensors)
   * Hardware that used as Gateway is Raspberry Pi
   * Gateway App perform connection transaction with cloud
   * You can choose many sensors you want, e.g., soil moisture, temperature, humidity

## 3. Register your Ignite Greenhouse Kit with using your Android device.

* First, install the app.

* Log in.

* You are at the gateway list screen now. Click add button and add your first gateway. Scan QR code on your gateway device to register it.

* The gateway will appear on your dashboard. Now you can add another gateway or go to sensor dashboard by clicking gateway.

* On sensor dashboard you can add and register new sensors by clicking the add button. The sensors will appear on sensor dashboard.

# Technical Structure

Gateway devices are at the center of the scheme. Gateways interact other elements: Sensors, Cloud and your Android devices.

1. Sensors publish and gateways receive data with using the RF protocol.
2. The gateway sends the data to IoT-Ignite Cloud platform with using [Gateway App](https://github.com/IoT-Ignite/IgniteGreenhouseGateway/releases/tag/v1.0.0) that already loaded on the gateway for collecting and processing data.
3. Cloud keeps processed data.
4. Finally, the data comes to your Android device with using Ignite Rest Client API.

For more technical details about our IoT projects and APIs visit [IoT-Ignite Documentations](https://devzone.iot-ignite.com/documents).

# Project Dependencies

* [IoT-Ignite API](https://devzone.iot-ignite.com/documents) - IoT-Ignite platform connections
* [Ignite REST Client API](https://github.com/IoT-Ignite/IgniteRestClientLibrary) - Retrofit based Android library
* [Retrofit](http://square.github.io/retrofit/) - For REST calls
* [barcodescanner](https://github.com/dm77/barcodescanner) - Using QR Scanner for register gateway and sensor
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView) - Using for loading screen
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) - Charting

# Authors

* **ARDIC R&D** - [http://www.ardictech.com/](http://www.ardictech.com/)

