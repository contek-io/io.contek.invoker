package io.contek.invoker.kraken.api.common;

import com.google.gson.JsonArray;

public final class _BookLevel {

  public Double price;
  public Double volume;
  public Double timestamp;
  public String updateType;

  public static _BookLevel toOrderBookLevel(JsonArray jsonArray) {
    _BookLevel level = new _BookLevel();
    level.price = jsonArray.get(0).getAsDouble();
    level.volume = jsonArray.get(1).getAsDouble();
    level.timestamp = jsonArray.get(2).getAsDouble();
    if (jsonArray.size() == 4) {
      level.updateType = jsonArray.get(3).getAsString();
    }
    return level;
  }

  @Override
  public String toString() {
    return "_BookLevel{" +
            "price=" + price +
            ", volume=" + volume +
            ", timestamp=" + timestamp +
            ", updateType='" + updateType + '\'' +
            '}';
  }
}
