package io.contek.invoker.ftx.api.common;

public class _Fill {
    public int id;
    public String baseCurrency;
    public String quoteCurrency;
    public String side;
    public double price;
    public double size;
    public String time;

    @Override
    public String toString() {
        return "_Fill{" +
                "id=" + id +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", quoteCurrency='" + quoteCurrency + '\'' +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", time='" + time + '\'' +
                '}';
    }
}
