package com.example.demo.config;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CSVMetrics {

    private MetricRegistry metricsRegistry;

    private CsvReporter reporter;

    public void initBean() {
        reporter = CsvReporter.forRegistry(metricsRegistry)
                .formatFor(Locale.US)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new File("/Users/satishpeddi/engr/temporary/demo/metrics-data/"));
        ;
        reporter.start(1, TimeUnit.SECONDS);
    }

    public void destroyBean() {
        reporter.stop();
    }

    public void setMetricsRegistry(MetricRegistry metricsRegistry) {
        this.metricsRegistry = metricsRegistry;
    }
}
