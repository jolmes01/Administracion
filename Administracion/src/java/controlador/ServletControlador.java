/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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

        String submit = request.getParameter("submit");

        //Obtiene la sesion para conocer los atributos que contiene
        HttpSession session = request.getSession(true);
        //Se obtiene si es que existe el bean de las cuentas
        BeanCuentas cuentas = (BeanCuentas) session.getAttribute("cuentasE");
        //Si el bean no existe en la sesion se crea uno y se agrega a la misma
        if (cuentas == null) {
            System.out.println("CREADO BEAN CUENTAS");
            cuentas = new BeanCuentas();
            session.setAttribute("cuentasE", cuentas);
        }

        if (submit != null) {
            if ("cuentaG".equals(submit)) {
                registraCuentaG(request, response, out, cuentas);
            }
            if ("cuentaE".equals(submit)){
                registraCuentaE(request, response, out, cuentas);
            }
            if("cuentaD".equals(submit)){
                //bajaCuenta(request, response, out, cuentas);
            }
        } else {
            getCuentas(request, response, out, cuentas);
        }
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

    private void registraCuentaG(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanCuentas cuentas) {
        double saldoD = 0.0;
        int idCuenta = Integer.parseInt(request.getParameter("cuentaN"));
        String saldo = request.getParameter("saldoText");
        if (saldo != null && !"".equals(saldo)) {
            saldoD = Double.valueOf(saldo);
        }
        boolean result = cuentas.addCuenta(idCuenta, saldoD);
        try {
            HttpSession ses = request.getSession(true);
            ses.setAttribute("Respuesta", result);
            response.sendRedirect("./catalogo.jsp");
        } catch (IOException ex) {
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registraCuentaE(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanCuentas cuentas) {
        double saldoD = 0.0;
        int idSubD;
        int idCuenta = Integer.parseInt(request.getParameter("cuentaSubN"));
        String automatico = request.getParameter("automatic");
        String idSub = request.getParameter("idSub");
        String descripcion = request.getParameter("descripcionSub");
        String saldo = request.getParameter("saldoSub");
        if("calcular".equals(automatico)){
            idSubD = cuentas.getTotalSubCuentas(idCuenta)+1;
        }else{
            idSubD = Integer.parseInt(idSub);
        }
        if (saldo != null && !"".equals(saldo)) {
            saldoD = Double.valueOf(saldo);
        }
        boolean result = cuentas.addCuenta(idCuenta, idSubD, descripcion, saldoD);
        try {
            HttpSession ses = request.getSession(true);
            ses.setAttribute("Respuesta", result);
            response.sendRedirect("./catalogo.jsp");
        } catch (IOException ex) {
            Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*private void bajaCuenta(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanCuentas cuentas) {
        String cuenta = request.getParameter("bajaCuenta");
        boolean result = false;
        if(cuenta.length() > 4){
            String cuentasList[] = cuenta.split(".");
            int idCuenta = Integer.parseInt(cuentasList[0]);
            int idSubCuenta = Integer.parseInt(cuentasList[1]);
            result = cuentas.dropCuenta(idCuenta,idSubCuenta);
        }else{
            result = cuentas.dropCuenta(Integer.parseInt(cuenta));
        }
    }*/
}
