/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.BeanPoliza;

/**
 *
 * @author JL
 */
public class ServletPoliza extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String sCommand = request.getParameter("comando");
        String log = request.getParameter("submit");
        int command = 0;

        if (sCommand != null) {
            command = Integer.parseInt(sCommand);
        }
        if (log != null) {
            command = 6;
        }

        HttpSession session = request.getSession(true);
        BeanPoliza poliza = (BeanPoliza) session.getAttribute("polizaE");
        
        if (poliza == null) {
            poliza = new BeanPoliza();
            session.setAttribute("polizaE", poliza);
        }

        switch (command) {
            case 0:
                out.println(poliza.getHistory());
                break;
            case 1:
                agregarPoliza(request, response, out, poliza);
                break;
            case 2:
                eliminar(request, response, out, poliza);
                break;
            case 3:
                submitPoliza(request, response, out, poliza);
                break;
            case 4:
                //vaciar(request, response, out, producto);
                break;
            case 5:
                //session.setAttribute("bean", producto);
                //grabar(request, response, out, producto, session.getAttribute("usuario").toString());
                break;
            case 6:
                //salir(request, response, out, producto);
                break;

        }
    }

    public void agregarPoliza(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanPoliza poliza) {
        try {
            String polizaId = request.getParameter("poliza");
            String cuentaP = request.getParameter("cuentaP");
            String fechaMov = request.getParameter("fechaDeMov");
            String tipo = request.getParameter("tipo");
            String pago = request.getParameter("pago");
            out.println(poliza.addPoliza(polizaId,cuentaP,fechaMov,tipo,pago));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanPoliza poliza) {
        String valor = String.valueOf(request.getParameter("item"));
        try {
            out.println(poliza.delPoliza(valor.substring(0,1),valor.substring(2)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void submitPoliza(HttpServletRequest request, HttpServletResponse response, PrintWriter out, BeanPoliza poliza) {
        out.println(poliza.registroPoliza());
    }

}