/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CONTROLLER;

import DAO.Datos;
import MODEL.Maestro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public class insertaQuiniela extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet insertaQuiniela</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet insertaQuiniela at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Datos d = new Datos();
        ArrayList<Maestro> maestro = new ArrayList<>();
        maestro = d.Maestro();
        HttpSession session = request.getSession();
        String idU = request.getParameter("idUsuario");
        int idUsuario = Integer.parseInt(idU);
        for (Maestro m : maestro) {
            String resultado = request.getParameter("" + m.getIdMaestro());

            if (resultado.equalsIgnoreCase("local")) {
                d.InsertaResultado(m.getIdE1(), m.getIdE2(), 1, 0, 0, idUsuario);
            } else if (resultado.equalsIgnoreCase("visita")) {
                d.InsertaResultado(m.getIdE1(), m.getIdE2(), 0, 1, 0, idUsuario);
            } else if (resultado.equalsIgnoreCase("empate")) {
                d.InsertaResultado(m.getIdE1(), m.getIdE2(), 0, 0, 1, idUsuario);
            }

        }
        session.setAttribute("OK", "OK");
        response.sendRedirect("inicio.jsp");
        
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
