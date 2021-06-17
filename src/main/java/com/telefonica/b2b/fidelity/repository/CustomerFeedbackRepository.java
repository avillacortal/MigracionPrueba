package com.telefonica.b2b.fidelity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefonica.b2b.fidelity.entity.CustomerFeedbackResponse;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedbackResponse, String> {

    public List<CustomerFeedbackResponse> findByServiceidAndCodresulllam(String serviceId, String codresulllam);

}
