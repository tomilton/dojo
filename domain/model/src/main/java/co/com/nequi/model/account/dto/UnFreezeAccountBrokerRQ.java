package co.com.nequi.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class UnFreezeAccountBrokerRQ {
    private String accountNumber;
    private String reasonCode;
}
