package com.telefonica.b2b.fidelity.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.telefonica.b2b.fidelity.commons.Constant;
import com.telefonica.b2b.fidelity.commons.Util;
import com.telefonica.b2b.fidelity.exception.BusinessException;
import com.telefonica.b2b.fidelity.pojo.Principalcomponent;
import com.telefonica.b2b.fidelity.pojo.PsCampana;
import com.telefonica.b2b.fidelity.pojo.Subscriber;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class SubscriberFixedDAO {

    @Autowired
    @Qualifier(value = "daasDataSource")
    private HikariDataSource daasDataSource;

    public List<Subscriber> getCustomerByNationalId(String nationalIdType, String nationalId) {
	List<Subscriber> lstSubscriber = new ArrayList<>();

	try (Connection conn = daasDataSource.getConnection();
		PreparedStatement ps = this.createPsFindByMsisdn(conn, nationalIdType, nationalId);
		ResultSet rs = ps.executeQuery();) {
	    while (rs.next()) {
		Subscriber subscriber = new Subscriber();
		Principalcomponent principalcomponent = new Principalcomponent();

		subscriber.setSubscriberid(rs.getString(1));
		subscriber.setMsisdn(rs.getString(2));
		subscriber.setCategory(rs.getString(3));
		subscriber.setSubscriberState(rs.getString(4));

		principalcomponent.setStatus(rs.getString(5));
		principalcomponent.setSubscriberid(rs.getString(6));

		PsCampana psCampana = new PsCampana();
		psCampana.setVelocidad(Util.parseStringToInt(rs.getString(7)));
		principalcomponent.setPsCampana(psCampana);

		subscriber.setPrincipalcomponent(principalcomponent);

		lstSubscriber.add(subscriber);
	    }
	} catch (SQLException ex) {
	    throw new BusinessException(Constant.GENERIC_CODE);
	}
	return lstSubscriber;
    }

    private PreparedStatement createPsFindByMsisdn(Connection conn, String nationalIdType, String nationalId) throws SQLException {
	String query = "select  s.subscriberid,s.msisdn, s.category, s.subscriberstate, p.status,p.subscriberid, pc.velocidad"
		+ " from subscriber_f s join customer_f c on c.id=s.id and c.origen=s.origen"
		+ " join principalcomponent_f p on p.subscriberid=s.subscriberid  and p.origen=s.origen"
		+ " join ps_campana pc on pc.id_ps_campana = p.productkey  where c.tipodocumento=? and c.numerodocumento=?";

	PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	ps.setFetchSize(100);
	ps.setString(1, nationalIdType);
	ps.setString(2, nationalId);

	return ps;

    }

}
