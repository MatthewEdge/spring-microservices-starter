FROM docker.elastic.co/logstash/logstash:7.2.0

COPY ./config/logstash.yml /usr/share/logstash/config/logstash.yml
COPY ./pipeline /usr/share/logstash/pipeline

# Add your logstash plugins setup here
# Example: RUN logstash-plugin install logstash-filter-json

EXPOSE 5000 9600
