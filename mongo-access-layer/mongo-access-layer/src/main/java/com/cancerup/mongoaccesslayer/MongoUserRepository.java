package com.cancerup.mongoaccesslayer;

import com.cancerup.mongoaccesslayer.models.DataAccess;
import com.cancerup.mongoaccesslayer.models.DataPutRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MongoUserRepository extends MongoRepository<DataPutRequest,Integer> {
    Optional<DataPutRequest> findByDataAccess(DataAccess dataAccess);
}
