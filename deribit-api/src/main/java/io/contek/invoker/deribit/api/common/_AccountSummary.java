package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _AccountSummary {

  public double options_gamma;
  public double projected_maintenance_margin;
  public String system_name;
  public double margin_balance;
  public boolean tfa_enabled;
  public double options_value;
  public String username;
  public _Limits limits;
  public double equity;
  public double futures_pl;
  public List<_Fee> fees;
  public double options_session_upl;
  public int id;
  public double options_vega;
  public String referrer_id;
  public String currency;
  public boolean login_enabled;
  public String type;
  public double futures_session_rpl;
  public double options_theta;
  public boolean portfolio_margining_enabled;
  public double projected_delta_total;
  public double session_rpl;
  public double delta_total;
  public double options_pl;
  public double available_withdrawal_funds;
  public double maintenance_margin;
  public double initial_margin;
  public boolean interuser_transfers_enabled;
  public double futures_session_upl;
  public double options_session_rpl;
  public double available_funds;
  public String email;
  public long creation_timestamp;
  public double session_upl;
  public double total_pl;
  public double options_delta;
  public double balance;
  public double projected_initial_margin;
  public String deposit_address;

  @Override
  public String toString() {
    return "_AccountSummary{" +
            "options_gamma=" + options_gamma +
            ", projected_maintenance_margin=" + projected_maintenance_margin +
            ", system_name='" + system_name + '\'' +
            ", margin_balance=" + margin_balance +
            ", tfa_enabled=" + tfa_enabled +
            ", options_value=" + options_value +
            ", username='" + username + '\'' +
            ", limits=" + limits +
            ", equity=" + equity +
            ", futures_pl=" + futures_pl +
            ", fees=" + fees +
            ", options_session_upl=" + options_session_upl +
            ", id=" + id +
            ", options_vega=" + options_vega +
            ", referrer_id='" + referrer_id + '\'' +
            ", currency='" + currency + '\'' +
            ", login_enabled=" + login_enabled +
            ", type='" + type + '\'' +
            ", futures_session_rpl=" + futures_session_rpl +
            ", options_theta=" + options_theta +
            ", portfolio_margining_enabled=" + portfolio_margining_enabled +
            ", projected_delta_total=" + projected_delta_total +
            ", session_rpl=" + session_rpl +
            ", delta_total=" + delta_total +
            ", options_pl=" + options_pl +
            ", available_withdrawal_funds=" + available_withdrawal_funds +
            ", maintenance_margin=" + maintenance_margin +
            ", initial_margin=" + initial_margin +
            ", interuser_transfers_enabled=" + interuser_transfers_enabled +
            ", futures_session_upl=" + futures_session_upl +
            ", options_session_rpl=" + options_session_rpl +
            ", available_funds=" + available_funds +
            ", email='" + email + '\'' +
            ", creation_timestamp=" + creation_timestamp +
            ", session_upl=" + session_upl +
            ", total_pl=" + total_pl +
            ", options_delta=" + options_delta +
            ", balance=" + balance +
            ", projected_initial_margin=" + projected_initial_margin +
            ", deposit_address='" + deposit_address + '\'' +
            '}';
  }
}
