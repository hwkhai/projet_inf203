package com.csdnes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csdnes.utils.HospitalisationAction;
import com.csdnes.utils.CIM10;
import com.csdnes.utils.Cim10Action;
import com.csdnes.utils.Diagnostic;
import com.csdnes.utils.DiagnosticAction;
import com.csdnes.utils.Hospitalisation;

/**
 * Servlet implementation class RecherchePatient
 */
@WebServlet(name = "SearchPatients", urlPatterns = {"/SearchPatients"})
public class RecherchePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecherchePatient() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static boolean isInteger(String str) {    
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
        return pattern.matcher(str).matches();    
      }  

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

        String number_patient = req.getParameter("number_patient");
        if(number_patient=="") {
        	req.getRequestDispatcher("RecherchePatient.jsp").forward(req,resp);
        }
        Boolean etat=RecherchePatient.isInteger(number_patient);
        if(!etat) {
        	req.getRequestDispatcher("RecherchePatient.jsp").forward(req,resp);
        	
        }else
        {
        //这里要转成整型
        int vsnumber_patient=Integer.parseInt(number_patient);
       
        
        HospitalisationAction hospAction= new HospitalisationAction();
        DiagnosticAction	patient_diag=new DiagnosticAction();
        Cim10Action 	patient_cim10=new Cim10Action();
        List<CIM10> ListCIM10= new ArrayList<CIM10>();
        if(number_patient==null) {
        	System.out.println("entrer un number_patient svp");
        }else {
        List<Hospitalisation> listeHospitalisations=hospAction.queryById(vsnumber_patient);
        
		 for (Hospitalisation hosp: listeHospitalisations)
         {
			 List<Diagnostic> listeDiagnostics=patient_diag.queryByNumHosp(hosp);
			 for (Diagnostic diag: listeDiagnostics)
			 {
				 CIM10 cim10_patient=patient_cim10.getCIM10(diag);
				 ListCIM10.add(cim10_patient);
				 System.out.println(cim10_patient.getCodeCIM10()+cim10_patient.getLibelle_EN()+cim10_patient.getLibelle_FR());
				 
			 }
			 
			 System.out.println(hosp+"\n");
         }
        }
        

        System.out.println(number_patient);
        req.setAttribute("ListCIM10", ListCIM10);
        req.setAttribute("number_patient", number_patient);
        req.getRequestDispatcher("ResultPatient.jsp").forward(req,resp);//这里是调用结果界面
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
    
    


}
