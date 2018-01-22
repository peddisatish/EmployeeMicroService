package com.example.demo.config;


import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

public class JMXMetrics {
    private MetricRegistry metricsRegistry;

    private JmxReporter reporter;
    private String domainName = "metrics";

    public void initBean(){
        reporter = JmxReporter.forRegistry(metricsRegistry).inDomain(domainName).build();
        reporter.start();
    }

    public void destroyBean(){
        reporter.stop();
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setMetricsRegistry(MetricRegistry metricsRegistry) {
        this.metricsRegistry = metricsRegistry;
    }
}
