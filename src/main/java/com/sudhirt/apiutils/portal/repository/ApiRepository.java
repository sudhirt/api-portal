package com.sudhirt.apiutils.portal.repository;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.entity.ApiKey;
import com.sudhirt.apiutils.portal.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<Api, ApiKey> {

    List<Api> findAllByIdResourceId(String resourceId);
}
