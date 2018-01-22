# Concurrency Utilities (JSR 236) CDI Extension

This is a CDI portable extension for obtaining all the default utilities defined by
[JSR 236](https://www.jcp.org/en/jsr/detail?id=236) via CDI dependency injection instead of
Java EE resource injection (`@Resource`) or JNDI look-ups.

The following artifacts can be injected into any CDI bean:

```java
@Inject
private ManagedExecutorService executorService;

@Inject
private ManagedScheduledExecutorService scheduledExecutorService;

@Inject
private ManagedThreadFactory threadFactory;

@Inject
private ContextService contextService;
```

## Usage

To use this extension within your project, add the following dependency to your _pom.xml_
(the project is not deployed to Maven Central at this point, so it needs to be built from
source before):

```xml
<dependency>
    <groupId>org.morling.concurrencyutilities.cdi</groupId>
    <artifactId>concurrency-utilities-cdi-extension</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## License

This work is licensed under the Apache License version 2.0
