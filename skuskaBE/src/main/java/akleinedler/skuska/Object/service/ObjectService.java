package akleinedler.skuska.Object.service;

import akleinedler.skuska.Object.persistence.ObjectRepository;
import akleinedler.skuska.Object.persistence.entity.ObjectEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {
    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<ObjectEntity> getAllObjects() {
        return objectRepository.getAllObjects();
    }

    @Cacheable(value = "object", key = "#objectId")
    public ObjectEntity getObject(Long objectId) {
        return objectRepository.getObject(objectId);

    }

    @CachePut(value = "object", key = "#objectRequestDto.id")
    public void createObject(ObjectRequestDto objectRequestDto) {
        objectRepository.createObject(objectRequestDto.getName(), objectRequestDto.getAge());
        rabbitTemplate.convertAndSend("object-exchange", "object-created");
    }

    @CacheEvict(value = "object", key = "#objectId")
    public void deleteObject(Long objectId) {
        objectRepository.deleteObject(objectId);
        rabbitTemplate.convertAndSend("object-exchange", "object-deleted");
    }
}
