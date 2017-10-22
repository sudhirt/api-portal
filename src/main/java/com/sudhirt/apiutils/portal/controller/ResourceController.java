package com.sudhirt.apiutils.portal.controller;

import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.mapper.ResourceMapper;
import com.sudhirt.apiutils.portal.resource.ResourceDTO;
import com.sudhirt.apiutils.portal.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/apis")
public class ResourceController {

    @Autowired
    private ResourceService apiService;

    @Autowired
    private ResourceMapper apiMapper;

    @GetMapping
    public List<ResourceDTO> list() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> get(@PathVariable String id) {
        Resource api = apiService.get(id);
        ResourceDTO apiResource = apiMapper.toResource(api);
        apiResource.add(linkTo(methodOn(ResourceController.class).getJson(api.getId())).withRel("json"));
        return new ResponseEntity<ResourceDTO>(apiResource, HttpStatus.OK);
    }

    @GetMapping("/{id}/json")
    public ResponseEntity<String> getJson(@PathVariable String id) {
        Resource api = apiService.get(id);
        return new ResponseEntity<String>(api.getSwaggerJson(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity create(@Valid @RequestBody ResourceDTO apiResource) {
        Resource api = apiMapper.toEntity(apiResource);
        api = apiService.save(api);
        ResourceDTO retval = new ResourceDTO();
        retval.setIdentifier(api.getId());
        retval.add(linkTo(methodOn(ResourceController.class).get(api.getId())).withSelfRel());
        return new ResponseEntity(retval, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity uploadApi(@PathVariable String id, @RequestBody String json) {
        apiService.update(id, json);
        ResourceDTO apiResource = new ResourceDTO();
        apiResource.setIdentifier(id);
        apiResource.add(linkTo(methodOn(ResourceController.class).getJson(id)).withSelfRel());
        return ResponseEntity.ok(apiResource);
    }
}
