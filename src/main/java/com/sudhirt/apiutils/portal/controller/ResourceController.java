package com.sudhirt.apiutils.portal.controller;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.mapper.ApiMapper;
import com.sudhirt.apiutils.portal.mapper.ResourceMapper;
import com.sudhirt.apiutils.portal.resource.ApiDTO;
import com.sudhirt.apiutils.portal.resource.ResourceDTO;
import com.sudhirt.apiutils.portal.service.ApiService;
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
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ApiMapper apiMapper;

    @GetMapping
    public List<ResourceDTO> list() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> get(@PathVariable String id) {
        Resource resource = resourceService.get(id);
        ResourceDTO apiResource = resourceMapper.toResource(resource);
        apiResource.add(linkTo(methodOn(ResourceController.class).getApis(id)).withRel("apis"));
        return new ResponseEntity<ResourceDTO>(apiResource, HttpStatus.OK);
    }

    @GetMapping("/{id}/apis")
    public ResponseEntity<List<ApiDTO>> getApis(@PathVariable String id) {
        List<Api> apis = apiService.find(id);
        List<ApiDTO> apiDTOs = apiMapper.toResource(apis);
        apiDTOs.forEach(e -> e.add(linkTo(methodOn(ResourceController.class).getApi(e.getResourceId(), e.getVersion())).withSelfRel()));
        return new ResponseEntity<List<ApiDTO>>(apiDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}/apis/{version}")
    public ResponseEntity<ApiDTO> getApi(@PathVariable String id, @PathVariable String version) {
        Api api = apiService.get(id, version);
        ApiDTO apiDTO = apiMapper.toResource(api);
        apiDTO.add(linkTo(methodOn(ResourceController.class).getJson(id, version)).withRel("json"));
        return new ResponseEntity<ApiDTO>(apiDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/apis/{version}/json")
    public ResponseEntity<String> getJson(@PathVariable String id, @PathVariable String version) {
        Api api = apiService.get(id, version);
        return new ResponseEntity<String>(api.getSwaggerJson(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity createResource(@Valid @RequestBody ResourceDTO resourceInput) {
        Resource resource = resourceMapper.toEntity(resourceInput);
        resource = resourceService.save(resource);
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setIdentifier(resource.getId());
        resourceDTO.add(linkTo(methodOn(ResourceController.class).get(resource.getId())).withSelfRel());
        return new ResponseEntity(resourceDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/apis", consumes = "application/json")
    public ResponseEntity createApi(@PathVariable(name = "id") String resourceId, @Valid @RequestBody ApiDTO apiInput) {
        apiInput.setResourceId(resourceId);
        Api api = apiMapper.toEntity(apiInput);
        api = apiService.save(api);
        ApiDTO apiDTO = new ApiDTO();
        apiDTO.setResourceId(api.getId()
                .getResourceId());
        apiDTO.setVersion(api.getId()
                .getVersion());
        apiDTO.add(linkTo(methodOn(ResourceController.class).getApi(apiInput.getResourceId(), apiInput.getVersion())).withSelfRel());
        return new ResponseEntity(apiDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/apis/{version}")
    public ResponseEntity uploadApi(@PathVariable String id, @PathVariable String version, @RequestBody String json) {
        apiService.update(id, version, json);
        ApiDTO apiDTO = new ApiDTO();
        apiDTO.setResourceId(id);
        apiDTO.setVersion(version);
        apiDTO.add(linkTo(methodOn(ResourceController.class).getJson(id, version)).withSelfRel());
        return ResponseEntity.ok(apiDTO);
    }
}
