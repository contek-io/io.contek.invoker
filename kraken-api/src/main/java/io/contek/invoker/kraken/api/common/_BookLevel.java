package io.contek.invoker.kraken.api.common;

import io.vertx.core.json.JsonArray;

public final class _BookLevel {

  public Double price;
  public Double volume;
  public Double timestamp;
  public String updateType;

  public static _BookLevel toOrderBookLevel(JsonArray jsonArray) {
    _BookLevel level = new _BookLevel();
    level.price = jsonArray.getDouble(0);
    level.volume = jsonArray.getDouble(1);
    level.timestamp = jsonArray.getDouble(2);
    if (jsonArray.size() == 4) {
      level.updateType = jsonArray.getString(3);
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
