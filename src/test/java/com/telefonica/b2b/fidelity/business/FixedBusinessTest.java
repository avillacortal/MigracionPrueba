package com.telefonica.b2b.fidelity.business;

import com.telefonica.b2b.fidelity.commons.MessageProp;
import com.telefonica.b2b.fidelity.jdbc.SubscriberFixedDAO;
import com.telefonica.b2b.fidelity.repository.CustomerFeedbackRepository;
import com.telefonica.b2b.fidelity.repository.LoyaltycampaignRepository;
import com.telefonica.b2b.fidelity.repository.LoyaltyfeedbackRepository;
import com.telefonica.b2b.fidelity.repository.RtdmOffersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

class FixedBusinessTest {

    @Autowired
    private SubscriberFixedDAO subscriberFixedDAO;

    private RtdmOffersRepository rtdmOffersRepo = Mockito.mock(RtdmOffersRepository.class);

    private CustomerFeedbackRepository customerFeedbackRepo;
    @Autowired
    private MessageProp prop;

    private LoyaltycampaignRepository loyaltycampaignRepository = Mockito.mock(LoyaltycampaignRepository.class);

    private LoyaltyfeedbackRepository loyaltyfeedbackRepository = Mockito.mock(LoyaltyfeedbackRepository.class);

    @BeforeEach
    void setUp() {
        System.out.println("Antes de la prueba");
    }

    @Test
    void getCampaigns() {
        System.out.println("Antes de la prueba");
    }
}