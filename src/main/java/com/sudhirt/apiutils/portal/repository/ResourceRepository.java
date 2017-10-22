package com.sudhirt.apiutils.portal.repository;

import com.sudhirt.apiutils.portal.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {
}
