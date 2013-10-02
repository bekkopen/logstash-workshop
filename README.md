
Startup
============

1. Download [Logstash](https://download.elasticsearch.org/logstash/logstash/logstash-1.2.1-flatjar.jar)
1. Save the config file below, and change the cluster name for ElasticSearch
1. Start Kibana: ```java -jar logstash-1.2.1-flatjar.jar web```
1. Start Logstash: ```java -jar logstash-1.2.1-flatjar.jar agent -v -f logstash.conf```
1. Open Kibana 3 (the dashboard) on [http://localhost:9292]


Example config (logstash.conf):
```
input {
  stdin { type => example }
}
output {
  elasticsearch {
    embedded => true
    cluster => "my-cluster-name"
  }
}
```

Logging application
===================

This project supplies an application which logs extensively, into a file called ```learning.log```. The application is started with ```mvn jetty:run```, and stopped with Ctrl-C.


Tasks
=====

1. Start logstash with the config above, write somthing in the console, and check that it shows up in Kibana.
1. Read the logfile from the application (learning.log). Hint: Use the [file-module](http://logstash.net/docs/1.2.1/inputs/file)
1. Java prints stacktraces on multiple lines. This should only be one log statement in logstash. Hint: Use the [multiline-module](http://logstash.net/docs/1.2.1/codecs/multiline)
1. We want each log part of the logfile (ie. loglevel, class, etc) in a seperate field, and to use the timestamp from each line. Hint: Use [grok-module](http://logstash.net/docs/1.2.1/filters/grok) to parse each line
  * Use the [Grok debugger](http://grokdebug.herokuapp.com/) to create patterns
1. We want to strip the DEBUG-messages from the log. Hint: Use [drop-module](http://logstash.net/docs/1.2.1/filters/drop)
1. Make a histogram in Kibana over the number of exceptions over time
1. Make a histogram in Kibana of the average response time. Hint: Use [extractnumbers-module](http://logstash.net/docs/1.2.1/filters/extractnumbers)


