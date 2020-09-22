# io.contek.invoker
A Java library to connect cryptocurrency exchanges.


## Goals

This project aims to provide a neat solution to connect cryptocurrency exchanges via their REST and WebSocket APIs.

It handles tedious things that are common in many exchanges, for example: rate limit, authentication, reconnection etc.


## Non Goals
This project does not make money for you. It does not contain any logic that predicts the market.

This project does not explain the usages of API endpoints. It is absolutely necessary to read the official API document before using an endpoint.


## Audiences
Data scientists who want to (freely and legally) download historical market data from exchanges.

Traders who want to interact with the market programmatically.


## Contribution Guidelines
### Read before you write
Please make sure you are familiar with the existing code base before coding. This helps keeping the code style and project structure consistent.

### Format your code
We highly recommend IntelliJ as your Java IDE for this project. In addition, we also recommend using plugin "google-java-format" and plugin "Save Actions" to format your code on the fly.

### Do not ignore warnings
Warnings significantly reduce the help you get from your IDE. Also, the more warnings you have in your existing code base, the more likely you will introduce new warnings in the future. Do your best to avoid warnings.

### No comment
Your code should be clear enough to understand without any comment. You can (usually) achieve this by writing more descriptive variable names and method names. We also recommend you keep methods short and avoid nested loops whenever possible.

### Only send required data
Do not include data/argument that is unnecessary for your purpose. It usually happens when you copy and paste code and forget to remove some fields. It may lead to rejection or unexpected outcomes.

### Minimum assumptions
Endpoint implementations shall always follow their official API documents. We shall make the best effort to avoid introducing random constant values that are not described in the API documents.

**Example 1:**\
*Q:* I can't find a rate limit restriction for XXX endpoint, but I think there must be a limit. Shall I cap it to 10 times per minute?\
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
