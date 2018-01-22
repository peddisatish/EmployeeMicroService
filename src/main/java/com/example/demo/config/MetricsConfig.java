package com.example.demo.config;

import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMetrics
public class MetricsConfig extends MetricsConfigurerAdapter {

    @Autowired
    MetricRegistry metricRegistry;

    @Bean (name = "jmxMetrics", initMethod = "initBean", destroyMethod = "destroyBean")
    public JMXMetrics getMetricsManager() {
        JMXMetrics metricsManager = new JMXMetrics();
        metricsManager.setMetricsRegistry(metricRegistry);
        metricsManager.setDomainName("demo-metrics");

        return metricsManager;
    }


    @Bean (name = "csvMetrics", initMethod = "initBean", destroyMethod = "destroyBean")
    public CSVMetrics getCSVMetricsManager() {
        CSVMetrics csvMetrics = new CSVMetrics();
        csvMetrics.setMetricsRegistry(metricRegistry);
        return csvMetrics;
    }
}