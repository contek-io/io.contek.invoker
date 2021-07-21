package io.contek.invoker.ftx.api.common;

public class _Withdrawal {
    public int id;
    public String coin;
    public String address;
    public String tag;
    public String txid;
    public int size;
    public int fee;
    public String status;
    public String time;
    public boolean fastWithdrawal;
    public int fastWithdrawalFee;

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
