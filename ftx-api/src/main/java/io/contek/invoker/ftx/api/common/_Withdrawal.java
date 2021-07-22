package io.contek.invoker.ftx.api.common;

public class _Withdrawal {
    public Integer id;
    public String coin;
    public String address;
    public String tag;
    public String txid;
    public Integer size;
    public Integer fee;
    public String status;
    public String time;
    public Boolean fastWithdrawal;
    public Integer fastWithdrawalFee;

    @Override
    public String toString() {
        return "_Withdraw{" +
                "id=" + id +
                ", coin='" + coin + '\'' +
                ", address='" + address + '\'' +
                ", tag='" + tag + '\'' +
                ", txid='" + txid + '\'' +
                ", size=" + size +
                ", fee=" + fee +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", fastWithdrawal=" + fastWithdrawal +
                ", fastWithdrawalFee=" + fastWithdrawalFee +
                '}';
    }
}
