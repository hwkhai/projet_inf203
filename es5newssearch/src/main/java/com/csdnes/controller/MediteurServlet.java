package com.csdnes.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MediteurServlet
 */
@WebServlet(name = "SearchPatientCIM10", urlPatterns = {"/SearchPatientCIM10"})
public class MediteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MediteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String cim10libelle_langue=req.getParameter("CIM10_langue");
        if(cim10libelle_langue!="") {
        	req.setAttribute("cim10libelle_langue",cim10libelle_langue);
        	req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
       
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
