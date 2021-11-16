package co.com.nequi.model.account.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class UnFreezeAccountBrokerRQ {
    private String accountNumber;
    private String reasonCode;
}
