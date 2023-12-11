package akleinedler.skuska.Object.persistence;

import akleinedler.skuska.Object.persistence.entity.ObjectEntity;
import akleinedler.skuska.Object.service.ObjectsDto;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends Neo4jRepository<ObjectEntity, Long> {

    @Query("MATCH(n:Object) RETURN n")
    List<ObjectEntity> getAllObjects();


    @Query("MATCH(n:Object) WHERE id(n) = $objectId RETURN n")
    ObjectEntity getObject(Long objectId);


    @Query("CREATE(n:Object {name: $name, age: $age});")
    void createObject(String name, long age);

    @Query("MATCH(n:Object) WHERE id(n) = $objectId DETACH DELETE n;")
    void deleteObject(Long objectId);
}
