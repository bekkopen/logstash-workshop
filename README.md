
Startup
============

1. Download [Logstash](https://download.elasticsearch.org/logstash/logstash/logstash-1.2.1-flatjar.jar)
1. Logstash i started with: ```java -jar logstash-1.2.1-flatjar.jar agent -f logstash.conf -- web```


Example config (logstash.conf):
```json
input {
  stdin { type => example }
}
output {
  stdout {
    codec => rubydebug
  }
  elasticsearch {
    embedded => true
  }
}
```
