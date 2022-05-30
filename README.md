# Exchange Connector

A Java library to **connect cryptocurrency exchanges** with **[Vertx](https://vertx.io/)** ([Event Loop IO](https://alexey-soshin.medium.com/understanding-vert-x-event-loop-46373115fb3e) and [reactive programming](https://www.techtarget.com/searchapparchitecture/definition/reactive-programming)).

This is a fork of **[io.contek.invoker](https://github.com/contek-io/io.contek.invoker)**

## Actual version

```1.0.0```

## Project java version

```17```

## Required Maven Dependencies

``` xml
<dependency>
    <groupId>com.github.fenrur</groupId>
    <artifactId>exchange-connector-commons</artifactId>
    <version>1.0.0</version>
</dependency>

<dependency>
    <groupId>com.github.fenrur</groupId>
    <artifactId>exchange-connector-security</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Required Gradle Dependencies

``` kotlin
implementation("com.github.fenrur:exchange-connector-commons:1.0.0")
implementation("com.github.fenrur:exchange-connector-security:1.0.0")
```

## All available Exchanges API

| api name        | artifact id                            |
|-----------------|----------------------------------------|
| binancedelivery | exchange-connector-binancedelivery-api |
| binancefutures  | exchange-connector-binancefutures-api  |
| binancespot     | exchange-connector-binancespot-api     |
| bitmex          | exchange-connector-bitmex-api          |
| bitstamp        | exchange-connector-bitstamp-api        |
| bybit           | exchange-connector-bybit-api           |
| bybitlinear     | exchange-connector-bybitlinear-api     |
| coinbasepro     | exchange-connector-coinbasepro-api     |
| deribit         | exchange-connector-deribit-api         |
| ftx             | exchange-connector-ftx-api             |
| hbdminverse     | exchange-connector-hbdminverse-api     |
| kraken          | exchange-connector-kraken-api          |
| okx             | exchange-connector-okx-api             |

## Examples with FTX

### Setup Dependencies

``` kotlin
implementation("com.github.fenrur:exchange-connector-commons:1.0.0")
implementation("com.github.fenrur:exchange-connector-security:1.0.0")
implementation("com.github.fenrur:exchange-connector-ftx-api:1.0.0")
```

### Setup Vertx

``` java
Vertx vertx = Vertx.vertx();
vertx.getOrCreateContext();
```

### Public Rest Api

``` java
final MarketRestApi restMarket = ApiFactory
  .getMainNet()
  .rest()
  .market(vertx);

restMarket
  .getSingleMarket()
  .setMarket("BTC-PERP")
  .submit()
  .onSuccess(response -> {
    final _Market result = response.result;
    System.out.println(result.price);
  });
```

### Private Rest Api

``` java
final ApiKey apiKey = ApiKey.newBuilder()
  .setId("id")
  .setSecret("secret")
  .setProperties(Map.of("FTX_SUBACCOUNT_KEY", "subaccount"))
  .build();

final UserRestApi restUser = ApiFactory
  .getMainNet()
  .rest()
  .user(vertx, apiKey);

  restUser
  .postOrders()
  .setType(OrderTypeKeys._market)
  .setSide(SideKeys._buy)
  .setSize(0.5)
  .submit()
  .onSuccess(response -> {
    final _Order result = response.result;
  });
```

### Public Websocket channel

``` java
final MarketWebSocketApi wsMarket = ApiFactory
  .getMainNet()
  .ws()
  .market(vertx);

wsMarket
  .getTradesChannel(TradesChannel.Id.of("BTC/USD"))
  .addConsumer(new ISubscribingConsumer<>() {
    @Override
    public void onStateChange(SubscriptionState subscriptionState) {

    }

    @Override
    public void onNext(TradesChannel.Message message) {
      System.out.println(message.data);
    }

    @Override
    public ConsumerState getState() {
      return ConsumerState.ACTIVE;
    }
  });
```

### Private Websocket channel

``` java
final ApiKey apiKey = ApiKey.newBuilder()
  .setId("id")
  .setSecret("secret")
  .setProperties(Map.of("FTX_SUBACCOUNT_KEY", "subaccount"))
  .build();

final UserWebSocketApi wsUser = ApiFactory
  .getMainNet()
  .ws()
  .user(vertx, apikey);

wsUser
  .getOrderUpdateChannel()
  .addConsumer(new ISubscribingConsumer<>() {
    @Override
    public void onStateChange(SubscriptionState subscriptionState) {

    }

    @Override
    public void onNext(OrdersChannel.Message message) {
      final OrdersChannel.Data data = message.data;
    }

    @Override
    public ConsumerState getState() {
      return ConsumerState.ACTIVE;
    }
  });
```

## Goals

* Non-blocking IO call

* High Performance

* This project aims to provide a neat solution to connect cryptocurrency exchanges via their REST and WebSocket APIs.

* It handles tedious things that are common in many exchanges, for example: rate limit, authentication, reconnection etc.

* [Modularized java project](https://openjdk.java.net/jeps/261)

* Keep the same conventions as the parent project (file name, packages...)


## Non-goals

* This project does not make money for you. It does not contain any logic that predicts the market.

* This project does not explain the usages of API endpoints. It is absolutely necessary to read the official API document
before using an endpoint.

## Audiences

* Data scientists who want to (freely and legally) download historical market data from exchanges.

* Traders who want to interact with the market programmatically.

## Contribution Guidelines

### Read before you write

Please make sure you are familiar with the existing code base before coding. This helps keeping the code style and
project structure consistent.

### Format your code

We highly recommend IntelliJ as your Java IDE for this project. In addition, we also recommend using plugin "
google-java-format" and plugin "Save Actions" to format your code on the fly.

### Do not ignore warnings

Warnings significantly reduce the help you get from your IDE. Also, the more warnings you have in your existing code
base, the more likely you will introduce new warnings in the future. Do your best to avoid warnings.

### No comment

Your code should be clear enough to understand without any comment. You can (usually) achieve this by writing more
descriptive variable names and method names. We also recommend you keep methods short and avoid nested loops whenever
possible.

### Only send required data

Do not include data/argument that is unnecessary for your purpose. It usually happens when you copy and paste code and
forget to remove some fields. It may lead to rejection or unexpected outcomes.

### Minimum assumptions

Endpoint implementations shall always follow their official API documents. We shall make the best effort to avoid
introducing random constant values that are not described in the API documents.
