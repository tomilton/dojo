package co.com.nequi.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FreezeAccountRsService {
    private Boolean status;
    private String message;
}