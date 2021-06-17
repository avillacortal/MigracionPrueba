
package com.telefonica.b2b.fidelity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telefonica.b2b.fidelity.entity.RtdmOffers;

@Repository
public interface RtdmOffersRepository extends JpaRepository<RtdmOffers, String> {

    public List<RtdmOffers> findByCampaignTypeAndBussinesLineType(String campaignId, String bussinesLineType);

}