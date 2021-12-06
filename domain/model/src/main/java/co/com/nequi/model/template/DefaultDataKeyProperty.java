package co.com.nequi.model.template;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DefaultDataKeyProperty {
    String name;
    Integer defaultDataId;
    String serviceId;
}
