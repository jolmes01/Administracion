/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.BeanCuentas;

/**
 *
 * @author JL
 */
public class ServletControlador extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Obtiene la sesion para conocer los atributos que contiene
        HttpSession session = request.getSession(true);
        //Se obtiene si es que existe el bean de las cuentas
        BeanCuentas cuentas = (BeanCuentas) session.getAttribute("cuentasE");
        //Si el bean no existe en la sesion se crea uno y se agrega a la misma
        if (cuentas == null) {
            cuentas = new BeanCuentas();
            session.setAttribute("cuentasE", cuentas);
        }
        getCuentas(request, response, out, cuentas);
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

    private void getCuentas(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanCuentas cuentas) {
        cuentas.getCuentas();
        try {
            response.sendRedirect("./catalogo.jsp");
        } catch (IOException ex) {
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
