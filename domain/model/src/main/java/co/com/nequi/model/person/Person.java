package co.com.nequi.model.person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Person {

    private String id;
    private Float balance;




}
