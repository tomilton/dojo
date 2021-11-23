package co.com.nequi.model.oracle;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultCache implements Serializable {
    private String bankId;
    private String customerId;
}
