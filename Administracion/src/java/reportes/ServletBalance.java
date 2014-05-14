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
              "IVA por trasladar","Impuestos por pagar","Intereses cobrados por anticipado","Rentas cobradas por anticipado a corto plazo","PTU por pagar","A largo plazo",
              "Documentos por pagar a largo plazo","Acreedores hipotecarios","Crédito refraccionario","Intereses cobrados por anticipado a largo plazo",
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
            PdfPTable table=new PdfPTable(1);
            //Obtenemos los datos de la clase BalanceGeneral
            BalanceGeneral b=new BalanceGeneral();
            b.calcula(Integer.parseInt(session.getAttribute("Empresa").toString()));
            for(int i=0; i<b.getSaldos().size();i++)
            {
                PdfPCell dato = new PdfPCell();
                if(i==0 || i==32 || i==33 || i==49 || i==61)
                {
                    Chunk ch= new Chunk(descripciones[i]);
                    ch.setFont(BOLD_Tot);
                    Phrase ph= new Phrase(ch);
                    dato.setPhrase(ph);
                    dato.setHorizontalAlignment(Element.ALIGN_LEFT);
                }
                else if(i==1 || i==16 || i==34 || i==44 || i==50 || i==55)
                {
                    Chunk ch= new Chunk(descripciones[i],new Font(FontFamily.TIMES_ROMAN,12,Font.UNDERLINE));
                    Phrase ph= new Phrase(ch);
                    dato.setPhrase(ph);
                    dato.setHorizontalAlignment(Element.ALIGN_LEFT);
                }
                else
                {
                    Chunk ch=new Chunk(descripciones[i]);
                    Phrase ph= new Phrase(ch);
                    dato.setPhrase(ph);
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
                dato.setBorder(Rectangle.NO_BORDER);
                table.addCell(dato);
                
                if(i==0 || i==1 || i==16 || i ==32 || i==33 || i==34 || i==44 || i==49 || i==50 || i==55 || i==61)
                {
                    Chunk ch=new Chunk();
                    String valor="";
                    ch.append(valor);
                    Phrase ph= new Phrase(ch);
                    dato.setPhrase(ph);
                }
                else if(b.getVal().size()>0)
                {
                    Chunk ch=new Chunk();
                    if(b.getSaldos().get(i)<0)
                    {
                        String valor="("+(b.getSaldos().get(i)*(-1))+")";
                        ch.append(valor);
                        ch.setFont(NORMAL_Negative);
                        Phrase ph= new Phrase(ch);
                        dato.setPhrase(ph);
                    }
                    else
                    {
                        dato.setPhrase(new Phrase(""+b.getSaldos().get(i)));
                    }
                }
                if(i==15 || i==31 || i==43 || i==48 || i==54 || i==60)
                {
                    dato.setBorder(Rectangle.BOTTOM);

                }
                else
                {
                    dato.setBorder(Rectangle.NO_BORDER);
                }
                dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(dato);
                
                
                if(i==15 || i==31 || i==32 || i==43 || i==48 || i==54 || i==60 || i==61)
                {
                    dato.setPhrase(new Phrase(""+b.getSumas().get(i)));
                    dato.setBorder(Rectangle.BOTTOM);
                }
                else
                {
                    Chunk ch=new Chunk();
                    String valor="";
                    ch.append(valor);
                    Phrase ph= new Phrase(ch);
                    dato.setPhrase(ph);
                }
                dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(dato);
            }
            document.add(table);
            document.add(new Paragraph(""));
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
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
