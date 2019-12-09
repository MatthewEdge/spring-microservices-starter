FROM grafana/grafana

COPY ./provisioning /etc/grafana/provisioning
COPY ./dashboards /var/lib/grafana/dashboards
COPY ./config.ini /etc/grafana/config.ini

EXPOSE 3000
