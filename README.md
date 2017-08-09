# Ignite Greenhouse for Android
Ignite Greenhouse is an open-source IoT autonomous greenhouse project for data collection, visualization, and device management. You can see sensor datas from your greenhouse on your Android device.

## Getting Started

Gateway devices are at center of schema. Gateways interact other elements: Sensors, Cloud and your Android devices.

1. Sensors publish and gateways receive data with using the RF protocol. 
2. Gateway send the datas to IoT Ignite Cloud platform with using [Gateway App](https://github.com/freeloki/GreenhousePrivate/wiki) for collecting and processing data.
3. Cloud returns processed data to gateway. 
4. Finally the data comes from your Android device with using IoT Ignite Rest Client.

For more technical details about APIs that used in this application, visit [Wiki](https://github.com/freeloki/GreenhousePrivate/wiki) page.

### Prerequisite

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
