package co.com.nequi.model.account.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UnFreezeAccountRq {
    private UnFreezeAccountRqCustomData accountUnFreezeRq_Customdata;
    private String bankId;
    private String accountId;

}
