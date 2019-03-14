package robo.systems.terminal


import com.codahale.metrics.ConsoleReporter
import com.codahale.metrics.MetricRegistry
import robo.utils.Az

import java.util.concurrent.TimeUnit

class Metrics(period: Long) {

    private val metrics: MetricRegistry

    init {
        val p = if (period < 1) 1 else period
        this.metrics = MetricRegistry()
        val reporter = ConsoleReporter.forRegistry(metrics)
            .convertRatesTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build()
        reporter.start(p, TimeUnit.SECONDS)
        Az.prLn("MetricsInterval: $p seconds")
    }

}
