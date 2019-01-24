package com.csdnes.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.csdnes.dao.Dao;

public class Cim10Action {
	
	public CIM10 getCIM10(Diagnostic patient_diag) {
		
		CIM10 Patient_cim10 = null;

        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        

        try
        {
        	Dao dao = new Dao();
        	dao.getConnection();
        	conn=dao.getConn();

            
            ptmt = conn.prepareStatement("{call 3_CIM10(?)}");

            ptmt.setString(1, patient_diag.getCodeCIM10());
            

            rs = ptmt.executeQuery();

            while (rs.next())
            {
            	Patient_cim10 =new CIM10();
       
            	Patient_cim10.setCodeCIM10(rs.getString("CodeCIM10"));
            	Patient_cim10.setLibelle_FR(rs.getString("libelle_FR"));
            	Patient_cim10.setLibelle_EN(rs.getString("libelle_EN"));
            

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ptmt != null)
                 {
                    ptmt.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return Patient_cim10 ;
    }
		
}


