# Building the client
Before proceeding, install a [JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) (must be Java 8 or later) and [Apache Maven](https://maven.apache.org/install.html).

Ensure `JAVA_HOME` is set correctly and the `mvn` executable is available on your PATH.

There are two approaches to building the client.
1. You can compile just the client and publish to a repository that handles dependency management like MavenCentral.  (Recommended)
2. You can build a "fat jar" that contains all required dependencies; this jar can be added to your customers' classpath.

## 1. Building a single jar
Run the following command in a terminal/console.
```bash
mvn package
```

This compiles the client into a jar located at `./target/first-api-sdk-1.0.0.jar`. Note that this jar does not include any dependencies.

### Distributing the client package
It's recommended to publish your client to a repository ([Maven](https://maven.apache.org/) or [Ivy](http://ant.apache.org/ivy/) for example) and have your customers declare a dependency on the project in their build system.

To depend on this project in Gradle (after the package is published to an accessible repository), add the following to your build.gradle file.
```perl
dependencies {
    compile 'firstapi-apig-api:first-api-sdk:1.0.0'
}
```

To depend on this project in Apache Maven (after the package published to an accessible repository), add the following to your pom.xml file.
```xml
<dependencies>
    <dependency>
        <groupId>firstapi-apig-api</groupId>
        <artifactId>first-api-sdk</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

For more information on managing dependencies with Maven and publishing artifacts, see:
* [https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
* [http://central.sonatype.org/pages/ossrh-guide.html](http://central.sonatype.org/pages/ossrh-guide.html)

## 2. Building a Standalone Jar

If your customers aren't using Maven or Gradle and you would prefer to distribute a single JAR with all dependencies, you can use the following command to build a fat jar.

```bash
mvn clean package -Pstandalone-jar
```

This will compile the client and package all dependencies into a jar located at `./target/first-api-sdk-1.0.0.jar`.

# Developer Guide

## Creating a client
To create a client, use the client builder. You can obtain an instance of the builder via a static factory method located on the client interface.

```java
FirstApiSDKClientBuilder builder = FirstApiSDK.builder();
```

The builder exposes many fluent configuration methods that can be chained to configure a service client. Here's a simple example that sets a few optional configuration options and then builds the service client.
```java
FirstApiSDK client = FirstApiSDK.builder()
    .connectionConfiguration(new ConnectionConfiguration()
	    .maxConnections(100)
	    .connectionMaxIdleMillis(1000))
    .timeoutConfiguration(new TimeoutConfiguration()
	    .httpRequestTimeout(3000)
	    .totalExecutionTimeout(10000)
	    .socketTimeout(2000))
    .build();
```

### Client Lifecycle
Note that each client created has its own connection pool. It's recommended to treat the clients as long-lived objects. Clients are immutable and thread safe.
Clients clean up their resources when garbage collected but if you want to explicitly shut down the client you can do the following:
```java
FirstApiSDK client = FirstApiSDK.builder().build();
client.shutdown();
// Client is now unusable
```


## Making requests
After a client is configured and created, you can make a request to the service. A method on the client interface (`FirstApiSDK`) is created for all actions (resource + method) in your API.

For each API method, classes are generated that represent the request and response of that API. The request class has setters for any path parameters, query parameters, headers, and payload model that are defined in the API. The response class exposes getters for any modeled headers and for the modeled payload.
```java
FirstApiSDK client = FirstApiSDK.builder().build();
GetEchoResult result = client.getEcho(new GetEchoRequest());
```

### Request Configuration
In addition to client-level configuration configured by the builder, each request class exposes configuration methods that are scoped to that request alone. Request level configuration takes precedence over client level configuration.

The request config also allows adding headers and query parameters that aren't modeled by the API.

```java
FirstApiSDK client = FirstApiSDK.builder().build();
client.getEcho(new GetEchoRequest().sdkRequestConfig(
    SdkRequestConfig.builder()
	.httpRequestTimeout(1500)
	.totalExecutionTimeout(5000)
	.customHeader("CustomHeaderName", "foo")
	.customQueryParam("CustomQueryParamName", "bar")
	.build()
));
```

### Response Metadata
In addition to the modeled data present in result objects, the SDK exposes access to additional HTTP metadata. This metadata is useful for debugging issues or accessing unmodeled data from the HTTP response.
```java
GetEchoResult result = client.getEcho(new GetEchoRequest());
System.out.println(result.sdkResponseMetadata().requestId());
System.out.println(result.sdkResponseMetadata().httpStatusCode());
// Full access to all HTTP headers (including modeled ones)
result.sdkResponseMetadata().header("Content-Length").ifPresent(System.out::println);
```

## Exception Handling

Service exceptions and client exceptions can be handled separately. Any exceptions modeled in the API will be a subtype of FirstApiSDKException.
```java
try {
    client.getEcho(...);
} catch(FirstApiSDKException e) {
   // All service exceptions will extend from FirstApiSDKException.
   // Any unknown or unmodeled service exceptions will be represented as a FirstApiSDKException.
} catch(SdkClientException e) {
   // Client exceptions include timeouts, IOExceptions, or any other exceptional situation where a response
   // is not received from the service.
}
```

All exceptions that can be thrown by the SDK are a subtype of SdkBaseException. To handle any exception in the same way, you can directly catch this exception. This covers both client and service exceptions.
```java
try {
    client.getEcho(...);
} catch(SdkBaseException e) {
    // All exceptions thrown from the client will be a subtype of SdkBaseException.
}
```

All service exceptions expose metadata about the HTTP response for logging or debugging purposes.
```java
try {
    client.getEcho(...);
} catch(FirstApiSDKException e) {
    int statusCode = e.sdkHttpMetadata().httpStatusCode();
    String requestId = e.sdkHttpMetadata().requestId();
    Optional<String> contentLength = e.sdkHttpMetadata().header("Content-Length");
    ByteBuffer responseContent = e.sdkHttpMetadata().responseContent();
}
```

Some client exceptions thrown are subtypes of SdkClientException. This provides greater granularity to deal with client-side exceptions.
```java
try {
    client.getEcho(...);
} catch(ClientExecutionTimeoutException e) {
    // Specific client exception thrown when the totalExecutionTimeout is triggered.
} catch(AbortedException e) {
    // Thrown when the client thread is interrupted while making a request.
} catch(SdkClientException e) {
    // All other exceptions can be handled here.
}
```

## Retries
Out of the box, the generated client retries on throttling errors (HTTP status code 429) and connection exceptions. If a different retry policy is desired, a custom one can be set via the client builder.

The easiest way to create a custom retry policy is to use the RetryPolicyBuilder. It provides a declarative API to specify when to retry.

```java
/**
 * The policy below will retry if the cause of the failed request matches any of the exceptions
 * given OR if the HTTP response from the service has one of the provided status codes.
 */
FirstApiSDK client = FirstApiSDK.builder()
        .retryPolicy(RetryPolicyBuilder.standard()
                             .retryOnExceptions(SocketTimeoutException.class)
                             .retryOnStatusCodes(429, 500)
                             .maxNumberOfRetries(10)
                             .fixedBackoff(100)
                             .build())
        .build();
```

You can also directly implement the RetryPolicy interface to define your own implementation. RetryPolicyContext contains useful metadata about the state of the failed request that can be used to drive dynamic retry decisions or compute backoff delays.

```java
/**
 * Simple implementation of {@link com.amazonaws.retry.v2.RetryPolicy}
 */
public static class CustomRetryPolicy implements RetryPolicy {

    @Override
    public long computeDelayBeforeNextRetry(RetryPolicyContext context) {
        return 100;
    }

    @Override
    public boolean shouldRetry(RetryPolicyContext context) {
        return context.retriesAttempted() < 3;
    }
}
// Using a custom retry policy via the builder
FirstApiSDK client = FirstApiSDK.builder()
        .retryPolicy(new CustomRetryPolicy())
        .build();
```

You can implement a RetryCondition and BackoffStrategy separately and combine them into a single policy.

```java
/**
 * Retry on 429 status codes. It's important to note that status code may be null if no response was returned from the
 * service. See {@link com.amazonaws.retry.v2.RetryCondition}
 */
public static class RetryOnThrottlingCondition implements RetryCondition {

    @Override
    public boolean shouldRetry(RetryPolicyContext context) {
        return context.httpStatusCode() != null && context.httpStatusCode() == 429;
    }
}

/**
 * Simple implementation of {@link BackoffStrategy} that always backs off 100 milliseconds.
 */
public static class Backoff100MillisecondsStrategy implements BackoffStrategy {
    @Override
    public long computeDelayBeforeNextRetry(RetryPolicyContext context) {
        return 100;
    }
}

/**
 * Uses {@link com.amazonaws.retry.v2.SimpleRetryPolicy} to combine a separately implemented RetryCondition and BackoffStrategy.
 */
FirstApiSDK client = FirstApiSDK.builder()
        .retryPolicy(new SimpleRetryPolicy(new RetryOnThrottlingCondition(), new Backoff100MillisecondsStrategy()))
        .build();

/**
 * The RetryCondition and BackoffStrategy interfaces are functional interfaces so lambda expressions and method
 * references may also be used. This is equivalent to the above example.
 */
FirstApiSDK client = FirstApiSDK.builder()
        .retryPolicy(new SimpleRetryPolicy(c -> c.httpStatusCode() != null && c.httpStatusCode() == 429,
                                           c -> 100))
        .build();
```
