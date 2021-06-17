package com.telefonica.b2b.fidelity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefonica.b2b.fidelity.entity.Loyaltycampaign;

@Repository
public interface LoyaltycampaignRepository extends JpaRepository<Loyaltycampaign, String>{

}
