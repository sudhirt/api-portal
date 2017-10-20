package com.sudhirt.apiutils.portal.repository;

import com.sudhirt.apiutils.portal.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<Api, String> {
}
