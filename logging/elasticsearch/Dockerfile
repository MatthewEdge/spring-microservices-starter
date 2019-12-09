FROM docker.elastic.co/elasticsearch/elasticsearch:7.2.0

COPY ./config/elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml

# Add your elasticsearch plugins setup here
# Example: RUN elasticsearch-plugin install analysis-icu

EXPOSE 9200 9300
