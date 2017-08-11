# Ignite Greenhouse for Android
Ignite Greenhouse is an open-source IoT autonomous greenhouse project for data collection, visualization, and device management. You can see various sensor datas from your greenhouse on your Android device.

## How is it works?

Gateway devices are at center of schema. Gateways interact other elements: Sensors, Cloud and your Android devices.

1. Sensors publish and gateways receive data with using the RF protocol. 
2. Gateway send the data to IoT-Ignite Cloud platform with using [Gateway App](https://github.com/freeloki/GreenhousePrivate/wiki) that already loaded on gateway for collecting and processing data.
3. Cloud returns processed data to gateway. 
4. Finally the data comes to your Android device with using Ignite Rest Client API.

For more technical details about APIs that used in this application, visit [Wiki](https://github.com/freeloki/GreenhousePrivate/wiki) page.

## Getting Started

What things you need to get information of your greenhouse.

1. [Install Ignite Greenhouse for Android](https://play.google.com/store/apps/details?id=com.ardic.android.kuuklaparentalcontrol)

2. [Have Ignite Greenhouse Kit](https://www.iot-ignite.com/) (Gateway, Gateway App, Sensors)

   * Hardware that used as Gateway is Raspberry PI
   * Gateway App perform connection transaction with cloud and your mobile device
   * You can choose many sensor you want, e.g., soil moisture, temperature, humidity
   
  :zap: Or you can make your own Greenhouse Kit by following [step by step series of examples](www.iot-ignite.com) that tell you have to get a development enviornment. :zap:
   
3. Register your Ignite Greenhouse Kit with using your Android device.

### Registering your Ignite Greenhouse Kit

* First, you have to log in. 

* You are at the gateway list screen now. Click add button and add your first gateway. Scan QR code on your gateway device to register it.

* The gateway will appear on your dashboard. Now you can add another gateway or going to sensor dashboard by clicking gateway.

* On sensor dashboard you can add and register new sensors by clicking add button. Then sensors will appear on sensor dashboard. 

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [IoT-Ignite API](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Ignite REST Client API](https://maven.apache.org/) - Dependency Management
* [Retrofit](http://square.github.io/retrofit/) - For REST calls
* [barcodescanner](https://github.com/dm77/barcodescanner) - Using QR Scanner for register gateway and sensor
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView) - Using for loading screen

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **ARDIC R&D** - [http://www.ardictech.com/](http://www.ardictech.com/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
