package io.contek.invoker.kraken.api.common;

import com.google.gson.JsonArray;

public class _OrderBookLevel {
  public Double price;
  public Double volume;
  public Double timestamp;
  public String updateType;

  public static _OrderBookLevel toOrderBookLevel(JsonArray jsonArray) {
    _OrderBookLevel level = new _OrderBookLevel();
    level.price = jsonArray.get(0).getAsDouble();
    level.volume = jsonArray.get(1).getAsDouble();
    level.timestamp = jsonArray.get(2).getAsDouble();
    if (jsonArray.size() == 4) {
      level.updateType = jsonArray.get(3).getAsString();
    }
    return level;
  }
}
