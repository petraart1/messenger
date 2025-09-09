FROM grafana/grafana:latest

ENV GF_SECURITY_ADMIN_PASSWORD=admin

COPY dashboards/ /var/lib/grafana/dashboards/

COPY provisioning/ /etc/grafana/provisioning/
