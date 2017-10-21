package com.sudhirt.apiutils.portal.controller;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.mapper.ApiMapper;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import com.sudhirt.apiutils.portal.resource.ApiResource;
import com.sudhirt.apiutils.portal.service.ApiService;
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
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private ApiMapper apiMapper;

    @GetMapping
    public List<ApiResource> list() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResource> get(@PathVariable String id) {
        Api api = apiService.get(id);
        ApiResource apiResource = apiMapper.toResource(api);
        apiResource.add(linkTo(methodOn(ApiController.class).getJson(api.getId())).withRel("json"));
        return new ResponseEntity<ApiResource>(apiResource, HttpStatus.OK);
    }

    @GetMapping("/{id}/json")
    public ResponseEntity<String> getJson(@PathVariable String id) {
        Api api = apiService.get(id);
        return new ResponseEntity<String>(api.getSwaggerJson(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity create(@Valid @RequestBody ApiResource apiResource) {
        Api api = apiMapper.toEntity(apiResource);
        api = apiService.save(api);
        ApiResource retval = new ApiResource();
        retval.setIdentifier(api.getId());
        retval.add(linkTo(methodOn(ApiController.class).get(api.getId())).withSelfRel());
        return new ResponseEntity(retval, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity uploadApi(@PathVariable String id, @RequestBody String json) {
        apiService.update(id, json);
        ApiResource apiResource = new ApiResource();
        apiResource.setIdentifier(id);
        apiResource.add(linkTo(methodOn(ApiController.class).getJson(id)).withSelfRel());
        return ResponseEntity.ok(apiResource);
    }
}
