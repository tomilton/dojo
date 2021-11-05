package co.com.nequi.model.account.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreezeAccountRqDto {
    private String accountNumber;
    private String reasonCode;
    private String freezeCode;
}
