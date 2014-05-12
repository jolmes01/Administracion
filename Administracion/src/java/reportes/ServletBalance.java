package reportes;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;
import java.util.Date;
import modelo.BalanceGeneral;

/**
 *
 * @author AlejandraMonserrat
 */
public class ServletBalance extends HttpServlet {
    
    public static final Font BOLD_Tot
            =new Font(FontFamily.HELVETICA, 12, Font.BOLD);
    public static final Font NORMAL
            = new Font(FontFamily.HELVETICA, 12);
    public static final Font NORMAL_Negative
            = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.RED);
    private final String descripciones[]
            ={"Activo","Circulante","Caja","Bancos","Inversiones Temporales","Clientes","Deudores diversos",
              "Documentos por cobrar","Almacén","IVA acreditable","IVA por acreditar",
              "Rentras pagadas por anticipado","Primas de seguro","Intereses pagados por anticipado",
              "Papelería y útiles","Propaganda y publicidad","No circulante","Terrenos","Edificio",
              "Depreciación acumulada de edificio","Mobiliario y equipo de oficina",
              "Depreciación acumulada de mobiliario y equipo","Equipo de reparto",
              "Depreciación acumulada de equipo de transporte","Equipo de cómputo",
              "Depreciación acumulada de equipo de cómputo","Crédito mercantil","Patentes",
              "Marcas","Depósitos en garantía","Gastos de organización","Gastos de instalación","SUMA ACTIVO",
              "Pasivo","A corto plazo","Proveedores","Acreedores diversos","Documentos por pagar","IVA trasladado",
              "IVA por trasladar","Impuestos por pagar","Rentas cobradas por anticipado","PTU por pagar","A largo plazo",
              "Documentos por pagar a largo plazo","Acreedores hipotecarios","Crédito refraccionario",
              "Capital contable","Capital contribuido","Capital Social","Aportaciones para futuros aumentos de capital","Donaciones",
              "Prima en venta de acciones","Capital ganado","Utilidades de ejercicios anteriores","Pérdida de ejercicios anteiores","Reserva legal",
              "Utilidad del ejercicio","Pérdida del ejercicio","SUMA PASIVO MÁS CAPITAL CONTABLE"};
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletConfig config= getServletConfig();
        ServletContext context= config.getServletContext();
        String path=context.getContextPath();
        String realPath=context.getRealPath("/");
        response.setContentType("application/pdf");
        //Para descargar el PDF
        response.setHeader("Content-Disposition","attachment;filename=\"BalanceGeneral.pdf\"");
        //step 1: creation of a document-object
        try{
            Document document= new Document(PageSize.LETTER);
            //step 2;
            //we create a writer that listens to the document
            //and directs a PDF-stream to a temporary buffer
            ByteArrayOutputStream buffer=new ByteArrayOutputStream();
            PdfWriter.getInstance(document,buffer);
            //step 3: we open the document
            document.open();
            //step 4: we add content to the document
            Paragraph title= new Paragraph((session.getAttribute("nEmpresa")!=null) ? session.getAttribute("nEmpresa").toString() : "Empresa");
            title.setAlignment(Element.ALIGN_CENTER);
            title.setFont(NORMAL);
            document.add(title);
            title = new Paragraph("Estado de situación financiera al 31 de Diciembre del " + (new Date().getYear() + 1900));           
            title.setAlignment(Element.ALIGN_CENTER);
            title.setFont(NORMAL);
            document.add(title);
            title = new Paragraph("Cifras en miles de pesos");           
            title.setAlignment(Element.ALIGN_CENTER);
            title.setFont(NORMAL);
            document.add(title);
            for (int i = 0; i < 2; i++) {
                document.add(new Paragraph(" "));
            }
            PdfPTable table=new PdfPTable(3);
            //Obtenemos los datos de la clase BalanceGeneral
            BalanceGeneral b=new BalanceGeneral();
            b.calcula(Integer.parseInt(session.getAttribute("Empresa").toString()));
            for(int i=0; i<b.getSaldos().size();i++)
            {
            }
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
