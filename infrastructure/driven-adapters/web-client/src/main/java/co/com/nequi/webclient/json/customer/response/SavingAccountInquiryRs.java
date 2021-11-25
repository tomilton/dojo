package co.com.nequi.webclient.json.customer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingAccountInquiryRs {
   @JsonProperty("CustomerId")
   private String customerId;
   @JsonProperty("ProductCode")
   private String productCode;
   @JsonProperty("AccountCurrency")
   private String accountCurrency;
   @JsonProperty("AccountName")
   private String accountName;
   @JsonProperty("AccountShortName")
   private  String accountShortName;
   @JsonProperty("AccountOpenDate")
   private String accountOpenDate;
   @JsonProperty("AccountFreezeStatus")
   private  String accountFreezeStatus;
   @JsonProperty("AccountStatus")
   private  String accountStatus;
   @JsonProperty("AccountAPY")
   private  int accountAPY;
   @JsonProperty("AccountCloseFlag")
   private  String accountCloseFlag;
   @JsonProperty("InterestCreditAccountIndicator")
   private String interestCreditAccountIndicator;
   @JsonProperty("InterestCreditAccountId")
   private  String interestCreditAccountId;
   @JsonProperty("EffectiveAvailableBalance")
   private  int effectiveAvailableBalance;
   @JsonProperty("AvailableBalance")
   private int availableBalance;
   @JsonProperty("ClearBalance")
   private int clearBalance;
   @JsonProperty("LienBalance")
   private int lienBalance;
   @JsonProperty("LienBalanceCurrency")
   private String lienBalanceCurrency;


}
