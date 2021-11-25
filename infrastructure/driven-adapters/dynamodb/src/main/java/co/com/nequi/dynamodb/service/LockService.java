package co.com.nequi.dynamodb.service;

import co.com.nequi.model.template.LockRS;
import co.com.nequi.model.template.gateways.LockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import co.com.nequi.dynamodb.repository.LockDynamoDBRepository;

@Service
public class LockService implements LockRepository {
    public static final Logger log = LoggerFactory.getLogger(TemplateService.class);

    private final LockDynamoDBRepository lockDynamoDBRepository;

    public LockService(LockDynamoDBRepository lockDynamoDBRepository) {
        this.lockDynamoDBRepository = lockDynamoDBRepository;
    }


    @Override
    public LockRS getById(String id) {
       return lockDynamoDBRepository.getByID(id);
    }
}
