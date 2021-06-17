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
import com.telefonica.b2b.fidelity.exception.BusinessException;
import com.telefonica.b2b.fidelity.pojo.Principalcomponent;
import com.telefonica.b2b.fidelity.pojo.Subscriber;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class SubscriberMobileDAO {

    @Autowired
    @Qualifier(value = "daasDataSource")
    private HikariDataSource daasDataSource;

    public List<Subscriber> getCustomerByNationalId(String nationalIdType, String nationalId) {
	List<Subscriber> lstSubscriber = new ArrayList<>();

	try (Connection conn = daasDataSource.getConnection();
		PreparedStatement ps = this.createPsFindCustomerByNationalId(conn, nationalIdType, nationalId);
		ResultSet rs = ps.executeQuery();) {
	    while (rs.next()) {
		Subscriber subscriber = new Subscriber();
		subscriber.setMsisdn(rs.getString(1));
		subscriber.setCategory(rs.getString(2));
		subscriber.setSubscriberState(rs.getString(3));
		subscriber.setSubscriberid(rs.getString(4));

		Principalcomponent principalcomponent = new Principalcomponent();
		principalcomponent.setStatus(rs.getString(5));
		principalcomponent.setName(rs.getString(6));
		subscriber.setPrincipalcomponent(principalcomponent);
		lstSubscriber.add(subscriber);
	    }
	} catch (SQLException ex) {
	    throw new BusinessException(Constant.GENERIC_CODE);
	}
	return lstSubscriber;
    }

    private PreparedStatement createPsFindCustomerByNationalId(Connection conn, String nationalIdType, String nationalId)
	    throws SQLException {
	String query = "select s.msisdn,s.category,s.subscriberstate, s.subscriberid,p.status,p.name"
		+ " from customer c join subscriber s on s.id=c.id  join principalcomponent p on p.subscriberid=s.subscriberid"
		+ " where c.tipodocumento = ? and c.numerodocumento = ?";
	PreparedStatement ps = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	ps.setFetchSize(50);
	ps.setString(1, nationalIdType);
	ps.setString(2, nationalId);

	return ps;

    }

}
