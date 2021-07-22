package io.contek.invoker.ftx.api.common;

public class _TransferBetweenSubAccounts {

    public Integer id;
    public String coin;
    public Integer size;
    public String time;
    public String notes;
    public String status;

    @Override
    public String toString() {
        return "_TransferBetweenSubAccounts{" +
                "id=" + id +
                ", coin='" + coin + '\'' +
                ", size=" + size +
                ", time='" + time + '\'' +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
