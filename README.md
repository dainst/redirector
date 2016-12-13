# Redirector

A tool written to maintain the permanent redirects from Arachne 3 to Arachne 4.

## Build 

```
$ cp config/config.properties.template config/config.properties # adjust if necessary
$ cp config/redirects.csv.template config/redirects.csv # adjust if necessary
$ gradle clean shadowJar
```

## Start 

```
$ java -jar build/libs/redirector.jar
```

starts the server, which listens on `localhost` on the `serverPort`
configured in [config.properties](config/config.properties.template).
Using `Ctrl-C` leads to a graceful application shutdown.

## Rest Api

[Reference](docs/rest-api-reference.md)