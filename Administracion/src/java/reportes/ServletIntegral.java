/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.Date;
import modelo.ResultadoIntegral;

/**
 *
 * @author JL
 */
public class ServletIntegral extends HttpServlet {

    private final String descripciones[]
            = {"Ventas netas", "Costo de ventas", "Utilidad o pérdida bruta",
                "Gastos generales", "Gastos de venta", "Gastos de adminisración",
                "Otros ingresos y gastos netos", "Utilidad de operación (EBIT)",
                "Resultado Integral de financiemiento (RIF)", "Intereses devengados",
                "Fluctuación cambiaria", "Cambios en el valor razonable de activos y pasivos financieros",
                "Utilidad o pérdida antes de impuestos a la utilidad", "Impuestos a la utilidad",
                "Utilidad de operaciones continuas", "Operaciones discontinuadas",
                "Utilidad neta", "Otros resultados integrales (ORI)", "Resultado integral"};

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        String path = context.getContextPath();
        String realPath = context.getRealPath("/");
        response.setContentType("application/pdf");
        //Para descargar el PDF
        /*response.setHeader("Content-Disposition",
         "attachment; filename=\"ResultadoIntegral.pdf\"");*/
        // step 1: creation of a document-object
        try {
            Document document = new Document(PageSize.LETTER);
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a temporary buffer
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, buffer);
            // step 3: we open the document
            document.open();
            // step 4: we add  content to the document
            Paragraph title = new Paragraph((session.getAttribute("nEmpresa") != null) ? session.getAttribute("nEmpresa").toString() : "Empresa");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            title = new Paragraph("Estado de resultado integral del 01 de Enero al 31 de Diciembre del " + (new Date().getYear() + 1900));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            title = new Paragraph("Cifras en miles de pesos");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            for (int i = 0; i < 2; i++) {
                document.add(new Paragraph(" "));
            }
            PdfPTable table = new PdfPTable(2);
            //Obtenemos los datos de la clase ResultadoIntegral
            ResultadoIntegral r = new ResultadoIntegral();
            r.calculaResultado(Integer.parseInt(session.getAttribute("Empresa").toString()));
            /*ventas - costo- utilidad - gastosV - gastosA - gastosI - otrosIngresos - otrosGastos - EBIT 
             interesesDev - fluctuacion - cambiosValor - antesImpuestos - impuestosUtilidad - operacionesCon
             operacionesDis - utilneta - ORI - resultadoIntegral */
            for (int i = 0; i < r.getSaldos().size(); i++) {
                PdfPCell dato = new PdfPCell(new Phrase(descripciones[i]));
                dato.setBorder(Rectangle.NO_BORDER);
                dato.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(dato);
                dato.setPhrase(new Phrase("" + r.getValores().get(i)));
                dato.setBorder(Rectangle.NO_BORDER);
                dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(dato);
            }
            document.add(table);
            document.add(new Paragraph(""));
            Paragraph nota = new Paragraph("Gracias por tu compra, vuelve pronto");
            nota.setAlignment(Element.ALIGN_CENTER);
            document.add(nota);
            // step 5: we close the document
            document.close();
            // step 6: we output the writer as bytes to the response output
            DataOutputStream output = new DataOutputStream(response.getOutputStream());
            byte[] bytes = buffer.toByteArray();
            response.setContentLength(bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                output.writeByte(bytes[i]);
            }
            output.close();
            response.getOutputStream();
            //session.removeAttribute("bean");
            //session.removeAttribute("producto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
