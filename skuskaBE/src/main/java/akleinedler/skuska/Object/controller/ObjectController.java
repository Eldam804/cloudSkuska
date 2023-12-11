package akleinedler.skuska.Object.controller;


import akleinedler.skuska.Object.persistence.entity.ObjectEntity;
import akleinedler.skuska.Object.service.ObjectRequestDto;
import akleinedler.skuska.Object.service.ObjectService;
import akleinedler.skuska.Object.service.ObjectsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Object -- Id, name, age
@RestController
public class ObjectController {
    @Autowired
    private ObjectService objectService;

    @GetMapping("/api/objects")
    public List<ObjectEntity> getAllObjects(){
        return objectService.getAllObjects();
    }

    @GetMapping("/api/object/{objectId}")
    public ObjectEntity getObject(@PathVariable Long objectId){
        return objectService.getObject(objectId);
    }

    @PostMapping("/api/object")
    public void createObject(@RequestBody ObjectRequestDto objectRequestDto){
        objectService.createObject(objectRequestDto);
    }

    @DeleteMapping("/api/object/{objectId}")
    public void deleteObject(@PathVariable Long objectId){
        objectService.deleteObject(objectId);
    }

}
