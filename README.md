# io.contek.invoker

A Java library to connect cryptocurrency exchanges.

## Maven

``` xml
<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>binancedelivery-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>binancefutures-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>bitmex-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>bitstamp-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>bybit-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>coinbasepro-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>deribit-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>ftx-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>hbdmlinear-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>hbdminverse-api</artifactId>
    <version>2.15.0</version>
</dependency>

<dependency>
    <groupId>io.contek.invoker</groupId>
    <artifactId>kraken-api</artifactId>
    <version>2.15.0</version>
</dependency>
```

## Examples

### Binance Futures Main Net Get Order Book

``` java
MarketRestApi api = ApiFactory.getMainNet().rest().market();
GetDepth.Response response = api.getDepth().setSymbol("BTCUSDT").setLimit(100).submit();
double bestBid = response.bids.get(0).get(0);
double bestAsk = response.asks.get(0).get(0);
System.out.println("Best bid: " + bestBid + ", best ask: " + bestAsk);
```

### Binance Futures Test Net Place Order

``` java
ApiKey key = ApiKey.newBuilder().setId("foo").setSecret("bar").build();
UserRestApi api = ApiFactory.getTestNet().rest().user(key);
PostOrder.Response response =
    api.postOrder()
        .setSymbol("BTCUSDT")
        .setSide(OrderSides.BUY)
        .setType(OrderTypes.LIMIT)
        .setPrice(9981.05d)
        .setQuantity(0.03d)
        .submit();
System.out.println("My order ID is: " + response.orderId);
```

### Binance Futures Main Net Subscribe Trades

``` java
ISubscribingConsumer<Message> consumer =
    new ISubscribingConsumer<Message>() {
      @Override
      public void onNext(AggTradeChannel.Message message) {
        double price = message.data.p;
        double quantity = message.data.q;
        System.out.println("New trade price: " + price + ", quantity: " + quantity);
      }

      @Override
      public void onStateChange(SubscriptionState state) {
        if (state == SubscriptionState.SUBSCRIBED) {
          System.out.println("Start receiving trade data");
        }
      }

      @Override
      public ConsumerState getState() {
        return ConsumerState.ACTIVE;
      }
    };
MarketWebSocketApi api = ApiFactory.getMainNet().ws().market();
api.getAggTradeChannel("BTCUSDT").addConsumer(consumer);
```

## Goals

This project aims to provide a neat solution to connect cryptocurrency exchanges via their REST and WebSocket APIs.

It handles tedious things that are common in many exchanges, for example: rate limit, authentication, reconnection etc.

## Non-goals

This project does not make money for you. It does not contain any logic that predicts the market.

This project does not explain the usages of API endpoints. It is absolutely necessary to read the official API document
before using an endpoint.

## Audiences

Data scientists who want to (freely and legally) download historical market data from exchanges.

Traders who want to interact with the market programmatically.

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

**Example 1:**\
*Q:* I can't find a rate limit restriction for XXX endpoint, but I think there must be a limit. Shall I cap it to 10
times per minute?\
*A:* No, because you are likely wrong.

**Example 2:**\
*Q:* I found XXX endpoint accepts XXX argument, which is not specified in the API document. Shall I add it?\
*A:* No, because it may cause unexpected outcomes.

**Example 3:**\
*Q:* The example in the official document suggests the field name is XXX but in fact it is YYY. What shall I do?\
*A:* We probably don't want to implement this endpoint yet. Wait for them to fix it first.

## FAQs

### Is this the official API client for XXX exchange?

No.

### Why is there no test for XXX?

We do not have the resources to write those tests (yet).

### Is XXX stable?

We do not know and we suggest you find it out in their official API document.

### Why is XXX not up to date?

The current version is (probably) good enough for us already. However, feel free to update it and submit pull requests.

### Why is XXX not implemented?

We are probably not using this endpoint. However, feel free to implement it and submit pull requests.
