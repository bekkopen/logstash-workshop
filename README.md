
Startup
============

1. Download [Logstash](https://download.elasticsearch.org/logstash/logstash/logstash-1.2.1-flatjar.jar)
1. Start Kibana: ```java -jar logstash-1.2.1-flatjar.jar web```
1. Start Logstash: ```java -jar logstash-1.2.1-flatjar.jar agent -v -f logstash.conf```
1. Open Kibana 3 (the dashboard) on [http://localhost:9292]



[Grok patterns](https://github.com/logstash/logstash/tree/master/patterns)
[Grok debugger](http://grokdebug.herokuapp.com/)


Example config (logstash.conf):
```json
input {
  stdin { type => example }
}
output {
  elasticsearch {
    embedded => true
  }
}
```
