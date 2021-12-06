package co.com.nequi.dynamodb.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DefaultDataKeyProperty {
    String name;
    Integer defaultDataId;
    String serviceId;
}
