package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.nt.bo.HotelBO;
import com.nt.dto.HotelDTO;

public class HotelDAOImpl implements HotelDao {
	DataSource ds;

	public HotelDAOImpl(DataSource ds) {
		System.out.println("HotelDAOImpl:1-param constructor");
		this.ds = ds;
	}

	public List<HotelBO> fethHotelByCity(String cond) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<HotelBO> listBO = null;
		HotelBO bo = new HotelBO();

		try {
			// get jdbc connection
			con = ds.getConnection();

			ps = con.prepareStatement(
					"SELECT HNAME,CITY,PRICE,TYPE,ROOM,GUEST,STAR FROM HOTEL WHERE CITY IN" + cond + " ORDER BY CITY");

			// EXECUTE QUERY
			rs = ps.executeQuery();

			// copying rs obj to listBO
			listBO = new ArrayList();
			while (rs.next()) {
				bo = new HotelBO();
				bo.setHname(rs.getString(1));
				bo.setCity(rs.getString(2));
				bo.setPrice(rs.getInt(3));
				bo.setType(rs.getString(4));
				bo.setRoom(rs.getInt(5));
				bo.setGuest(rs.getString(6));
				bo.setStar(rs.getInt(7));
				listBO.add(bo);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} // catch
		finally {
			// close jdbc obj
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // finally
		return listBO;
	}

}
