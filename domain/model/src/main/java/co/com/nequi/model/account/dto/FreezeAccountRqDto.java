package co.com.nequi.model.account.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreezeAccountRqDto {
    private String accountNumber;
    private Integer reasonCode;
    private String freezeCode;
}
