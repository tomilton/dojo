package co.com.nequi.dynamodb.repository;

import co.com.nequi.dynamodb.entity.LockRSEntity;
import co.com.nequi.model.template.LockRS;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

@Repository
public class LockDynamoDBRepository {

    private final DynamoDbAsyncTable<LockRSEntity> lockRSDynamoDbAsyncTable;

    public LockDynamoDBRepository(DynamoDbAsyncTable<LockRSEntity> templateDynamoDbAsyncTable) {
        this.lockRSDynamoDbAsyncTable = templateDynamoDbAsyncTable;
    }


    public CompletableFuture<LockRSEntity> save(LockRSEntity lock) {
        return lockRSDynamoDbAsyncTable.updateItem(lock);
    }

    public LockRS getByID(String custId) {
        LockRS lockRS = new LockRS();
        lockRS.setCustId("1");
        lockRS.setLastName("Vega");
        lockRS.setFirstName("Andrea");
        lockRS.setMiddleName("Carolina");
        lockRS.setName("Cuenta de Andrea");
        lockRS.setTitlePrefix("Cuentra ahorros");
        lockRS.setFreezeReasonCode("1000");
        lockRS.setFreezeStatusCode("500");
        return lockRS;
    }

    public CompletableFuture<LockRSEntity> update(LockRSEntity lock) {
        return lockRSDynamoDbAsyncTable.updateItem(lock);
    }

    public CompletableFuture<LockRSEntity> delete(String id) {
        return lockRSDynamoDbAsyncTable.deleteItem(getKeyBuild(id));
    }

    public PagePublisher<LockRSEntity> getAll() {return lockRSDynamoDbAsyncTable.scan();}

    private Key getKeyBuild(String custId) {
        return Key.builder()
                .partitionValue(custId)
                .build();
    }
}
