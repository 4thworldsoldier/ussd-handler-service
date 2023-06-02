package com.isw.ussd.handler.service.repo;

import com.isw.ussd.handler.service.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepo extends JpaRepository<Data, Long> {

}
