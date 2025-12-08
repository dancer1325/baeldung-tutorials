https://www.baeldung.com/micrometer

* goal
  * how to use 
    * Micrometer | Atlas
    * Micrometer + Spring

# Introduction

* [Micrometer](https://micrometer.io/)
  * provides
    * simple facade / abstract [MULTIPLE monitoring systems](https://docs.micrometer.io/micrometer/reference/implementations.html) (Atlas, Datadog, Graphite, Ganglia, Influx, JMX, and Prometheus)

# how to use?

* _Example:_ Micrometer implementation | [Netflix Atlas](https://github.com/Netflix/atlas)
  * | Maven
    ```xml
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-atlas</artifactId>
        <version>1.16.0</version>
    </dependency>
    ```

# `MeterRegistry`

* `MeterRegistry`
  * == Micrometer's core component
  * uses
    * register meters
    * create time series
  * _Example:_ [givenRegistry_whenIterateMeters_thenTimeSeriesGenerated()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

* `SimpleMeterRegistry`
  * == the simplest registry 

* `AtlasMeterRegistry`
  * == MeterRegistry -- for -- our monitoring system (Atlas)

* `CompositeMeterRegistry`
  * allows
    * adding MULTIPLE registries
    * publish SIMULTANEOUSLY application metrics | MULTIPLE supported monitoring systems
  * _Example:_ [givenCompositeRegistries_whenRecordMeter_thenAllRegistriesRecorded()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

* `Metrics.globalRegistry`
  * == `static CompositeMeterRegistry`
  * _Example:_ [givenGlobalRegistry_whenIncrementAnywhere_thenCounted()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

# Tags & Meters
## Tags

* Meter's identifier
  * == name + tags
    * naming convention -- `word1` + `.` + `word2` --
      * Reason:🧠guarantee the metric names portability | MULTIPLE monitoring systems🧠
    * tags
      * uses
        * filter in OR out the metrics
  * _Example:_ [check_tags()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

* Registry's common tags

## Meters
### Counter

* Counter
  * == count / specific application's property 
  * ways to create
    * `Counter.builder()`
    * `someRegistry.counter()`
  * _Example:_ [givenCounter_whenIncrement_thenValueChanged()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

### Timers

* Timers
  * uses
    * measure 
      * latencies -- `.totalTime()` --
      * frequency of events -- `.count()` --
      * percentiles -- `.takeSnapshot().percentileValues()` --
  * provided objects
    * `Timer`
      * `.record()`
    * `LongTaskTimer`
      * `.start()` & `.stop()`
      * uses
        * long time running events
  * _Example:_ 
    * [givenTimer_whenWrapTasks_thenTimeRecorded()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)
    * [givenLongTimer_whenRunTasks_thenTimerRecorded()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)
    * [givenTimer_whenEnrichWithPercentile_thenPercentilesComputed()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

### Gauge

* Gauge
  * uses
    * shows the meter's current value ONLY | observe them 
  * use cases
    * | monitor cache's stats OR collections' stats
  * _Example:_ [givenGauge_whenMeterListSize_thenCurrentSizeMonitored()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

### DistributionSummary

* `DistributionSummary`
  * provide
    * distribution of events -- `.takeSnapshot().percentileValues()` and `.takeSnapshot().histogramCounts()` --
    * simple summary -- `.count()`, `.totalAmount()`, `.mean()`, `.max()` --
    * histograms
      * service-level objective -- `.serviceLevelObjectives()` --
        * == group records / EACH bucket
      * time-scaled -- `.sla(durations)` --
  * _Example:_ 
    * [givenDistributionSummary_whenRecord_thenSummarized()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)
    * [givenDistributionSummary_whenEnrichWithHistograms_thenDataAggregated()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)
    * [givenTimer_whenEnrichWithTimescaleHistogram_thenTimeScaleDataCollected()](../src/test/java/com/baeldung/metrics/micrometer/MicrometerAtlasManualTest.java)

# Micrometer's binders

* Micrometer's built-in binders
  * allows
    * monitor
      * JVM,
        * _Examples:_ 
          * class loader metrics (ClassLoaderMetrics),
          * JVM memory pool (JvmMemoryMetrics)
          * GC metrics (JvmGcMetrics),
          * thread and CPU utilization (JvmThreadMetrics, ProcessorMetrics)
      * caches
        * requirements ABOUT supported ones
          * instrument -- with -- GuavaCacheMetrics, EhCache2Metrics, HazelcastCacheMetrics, and CaffeineCacheMetrics
      * ExecutorService,
      * logging services
        * steps
          * bind `LogbackMetrics` -- to -- any valid registry

            ```java
            new LogbackMetrics().bind(registry);
            ```

# Spring Integration

* TODO:
The Spring Boot Actuator provides dependency management and auto-configuration for Micrometer. Now it’s supported in Spring Boot 2.0/1.x and Spring Framework 5.0/4.x.

We’ll need the following dependency (the latest version can be found here):

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-spring-legacy</artifactId>
    <version>1.3.20</version>
</dependency>

Without any further changes to the existing code, we’ve enabled Spring support with the Micrometer. JVM memory metrics of our Spring application will be automatically registered in the global registry and published to the default atlas endpoint: http://localhost:7101/api/v1/publish.

There are several configurable properties available to control metrics exporting behaviors, starting with spring.metrics.atlas.*. Check AtlasConfig to see a full list of configuration properties for Atlas publishing.

If we need to bind more metrics, only add them as @Bean to the application context.

Say we need the JvmThreadMetrics:

@Bean
JvmThreadMetrics threadMetrics(){
return new JvmThreadMetrics();
}

As for web monitoring, it’s auto-configured for every endpoint in our application, yet manageable via a configuration property, spring.metrics.web.autoTimeServerRequests.

The default implementation provides four dimensions of metrics for endpoints: HTTP request method, HTTP response code, endpoint URI, and exception information.

When requests are responded, metrics relating to the request method (GET, POST, etc.) will be published in Atlas.

With Atlas Graph API, we can generate a graph to compare the response time for different methods:
methods

By default, response codes of 20x, 30x, 40x, 50x will also be reported:
status

We can also compare different URIs :
uri

Or check exception metrics:
exception

Note that we can also use @Timed on the controller class or specific endpoint methods to customize tags, long task, quantiles, and percentiles of the metrics:

@RestController
@Timed("people")
public class PeopleController {

    @GetMapping("/people")
    @Timed(value = "people.all", longTask = true)
    public List<String> listPeople() {
        //...
    }

}

Based on the code above, we can see the following tags by checking Atlas endpoint http://localhost:7101/api/v1/tags/name:

["people", "people.all", "jvmBufferCount", ... ]

Micrometer also works in the function web framework introduced in Spring Boot 2.0. We can enable metrics by filtering the RouterFunction:

RouterFunctionMetrics metrics = new RouterFunctionMetrics(registry);
RouterFunctions.route(...)
.filter(metrics.timer("server.requests"));

We can also collect metrics from the data source and scheduled tasks. Check the official documentation for more details.
