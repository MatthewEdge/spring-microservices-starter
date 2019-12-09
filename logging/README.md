# ELK Stack in Docker

Spins up instances for Elasticsearch, Logstash, and Kibana. 

Note: This Logstash instance is configured as an "All Sink" TCP Server. Simply pipe logs to it in the expected format. For more app-specific
filtering / etc you can either setup your own Logstash app instance or add the appropriate filters to the `logstash/pipeline` folder

## Docker Compose

A `docker-compose.yml` file is included to simplify spinning up the stack locally. Run with:

```
docker-compose build
docker-compose up -d && docker-compose logs -f
```

## Kubernetes

Kubernetes YAML files are also included. They rely on a single secret value:

```
kubectl create secret generic elasticsearch --from-literal="username=USERNAME" --from-literal="password=SUPERSECRET"
```

Then you can start the deployment:

```
kubectl apply -f ./kube/elasticsearch.yml
kubectl apply -f ./kube/logstash.yml
kubectl apply -f ./kube/kibana.yml
```
