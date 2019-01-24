package com.csdnes.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.csdnes.dao.Dao;

public class DiagnosticAction {
	  //chercher une list hospitalisation par numpatient
    public List<Diagnostic> queryByNumHosp(Hospitalisation hosp)
         {
    		 
    		 Diagnostic Patient_diag = null;
             List<Diagnostic> listeDiagnostics= new ArrayList<Diagnostic>();
   
            Connection conn = null;
            PreparedStatement ptmt = null;
            ResultSet rs = null;
            
    
            try
            {
            	Dao dao = new Dao();
            	dao.getConnection();
            	conn=dao.getConn();
    
                //String sql = "" + " select * from tab_hospitalisation " + " where NumPatient= ?  ";
    
                ptmt = conn.prepareStatement("{call 2_CIM10(?)}");
    
                ptmt.setInt(1, hosp.getNumHospitalisation());
                
    
                rs = ptmt.executeQuery();
    
                while (rs.next())
                {
                	Patient_diag =new Diagnostic();
                	Patient_diag.setNumDiagnostic(rs.getInt("NumDiagnostic"));
                	Patient_diag.setNumHospitalisation(rs.getInt("NumHospitalisation"));
                	Patient_diag.setCodeCIM10(rs.getString("CodeCIM10"));
                
                	
                	listeDiagnostics.add(Patient_diag);

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
    
            return listeDiagnostics ;
        }
	

}
