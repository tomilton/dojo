package co.com.nequi.model.account.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnFreezeAccountRqCustomData {
    private String freezeReasonCode;
}
