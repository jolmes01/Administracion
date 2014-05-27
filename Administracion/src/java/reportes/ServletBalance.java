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
            =new Font(FontFamily.HELVETICA, 10, Font.BOLD);
    public static final Font NORMAL
            = new Font(FontFamily.HELVETICA, 10);
    public static final Font NORMAL_Negative
            = new Font(FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.RED);
    private final String descripciones[]
            ={"Activo","Circulante","Caja","Bancos","Inversiones Temporales","Clientes","Deudores diversos",
              "Documentos por cobrar","Almacén","IVA acreditable","IVA por acreditar",
              "Rentas pagadas por anticipado","Primas de seguro","Intereses pagados por anticipado",
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
            Paragraph title = new Paragraph((session.getAttribute("nEmpresa") != null) ? session.getAttribute("nEmpresa").toString() : "Empresa");
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
            
            //Obtenemos los datos de la clase BalanceGeneral
           BalanceGeneral b=new BalanceGeneral();
           b.calcula(Integer.parseInt(session.getAttribute("Empresa").toString()));
           
           PdfPTable table=new PdfPTable(3);
           PdfPCell cel= new PdfPCell();
           cel.setPhrase(new Phrase("ACTIVO",new Font(FontFamily.HELVETICA,10,Font.BOLD)));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           
           double sumacirculante=0;
           double sumanocirculante=0;
           
           if(b.getDescripcircu().size()>0)
           {
                cel.setPhrase(new Phrase("Circulante",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripcircu().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripcircu().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValcircu().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setBorder(Rectangle.NO_BORDER);
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(dato);
                    sumacirculante+=b.getValcircu().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase("Suma activo circulante",new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""+sumacirculante,new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
               cel.setBorder(Rectangle.NO_BORDER);
               cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
               table.addCell(cel);
               
           }
           
           if(((b.getDescripinmu().size()>0)||(b.getDescripinmu().size()>0))||(b.getDescripotros().size()>0))
           {
                cel.setPhrase(new Phrase("No Circulante",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
           }
           
           if(b.getDescripinmu().size()>0)
           {
                cel.setPhrase(new Phrase("Inmuebles, planta y equipo",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripinmu().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripinmu().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValinmu().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setBorder(Rectangle.NO_BORDER);
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(dato);
                    sumanocirculante+=b.getValinmu().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               
           }
           
           if(b.getDescripinta().size()>0)
           {
                cel.setPhrase(new Phrase("Intangibles",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setBorder(Rectangle.NO_BORDER);
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripinta().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripinta().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValinta().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    sumanocirculante+=b.getValinta().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               
           }
           
           if(b.getDescripotros().size()>0)
           {
                cel.setPhrase(new Phrase("Otros activos",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setBorder(Rectangle.NO_BORDER);
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripotros().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripotros().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValotros().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    sumanocirculante+=b.getValotros().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
           }
           
           if(((b.getDescripinmu().size()>0)||(b.getDescripinmu().size()>0))||(b.getDescripotros().size()>0))
           {
              cel.setPhrase(new Phrase("Suma activo no circulante",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
              cel.setBorder(Rectangle.NO_BORDER);
              cel.setHorizontalAlignment(Element.ALIGN_LEFT);
              table.addCell(cel);
              cel.setPhrase(new Phrase(""));
              cel.setBorder(Rectangle.NO_BORDER);
              table.addCell(cel);
              cel.setPhrase(new Phrase(""+sumanocirculante,new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
              cel.setBorder(Rectangle.NO_BORDER);
              cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
              table.addCell(cel); 
           
           }
           
           double sumactivo=0;
           sumactivo=sumacirculante+sumanocirculante;
           
           cel.setPhrase(new Phrase("SUMA EL ACTIVO",new Font(FontFamily.HELVETICA,10,Font.BOLD)));
           cel.setBorder(Rectangle.NO_BORDER);
           cel.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""+sumactivo,new Font(FontFamily.HELVETICA,10,Font.BOLD)));
           cel.setBorder(Rectangle.BOTTOM);
           cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
           table.addCell(cel); 
           
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           
           //PASIVO
           
           if(((b.getDescripasivocp().size()>0)||(b.getDescripasivolp().size()>0)))
           {
                cel.setPhrase(new Phrase("PASIVO",new Font(FontFamily.HELVETICA,10,Font.BOLD)));
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
           }
           
           double pasivocp=0;
           double pasivolp=0;
           
           if(b.getDescripasivocp().size()>0)
           {
                cel.setPhrase(new Phrase("A corto plazo",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setBorder(Rectangle.NO_BORDER);
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripasivocp().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripasivocp().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValpasivocp().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    pasivocp+=b.getValpasivocp().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase("Suma pasivo a corto plazo",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""+pasivocp,new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
               cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
           }
           
           if(b.getDescripasivolp().size()>0)
           {
                cel.setPhrase(new Phrase("A largo plazo",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setBorder(Rectangle.NO_BORDER);
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripasivocp().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripasivolp().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValpasivolp().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    pasivolp+=b.getValpasivolp().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase("Suma pasivo a largo plazo",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""+pasivolp,new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
               cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
           }
           
           //CAPITAL CONTABLE
           
           if(((b.getDescripcapitalcon().size()>0)||(b.getDescripcapitalgan().size()>0)))
           {
                cel.setPhrase(new Phrase("CAPITAL CONTABLE",new Font(FontFamily.HELVETICA,10,Font.BOLD)));
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
           }
           
           double capitalcon=0;
           double capitalgan=0;
           
           if(b.getDescripcapitalcon().size()>0)
           {
                cel.setPhrase(new Phrase("Capital Contribuido",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setBorder(Rectangle.NO_BORDER);
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripcapitalcon().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripcapitalcon().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValcapitalcon().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    capitalcon+=b.getValcapitalcon().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase("Suma capital contribuido",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""+capitalcon,new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
               cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
           }
           
           if(b.getDescripcapitalgan().size()>0)
           {
                cel.setPhrase(new Phrase("Capital Ganado",new Font(FontFamily.HELVETICA,10,Font.ITALIC)));
                cel.setBorder(Rectangle.NO_BORDER);
                cel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
                cel.setPhrase(new Phrase(""));
                cel.setBorder(Rectangle.NO_BORDER);
                table.addCell(cel);
               
               for(int i=0;i<b.getDescripcapitalgan().size();i++)
               {
                    PdfPCell dato = new PdfPCell();
                    Chunk ch = new Chunk(b.getDescripcapitalgan().get(i));
                    Phrase ph = new Phrase(ch);
                    ph.setFont(NORMAL);
                    dato.setPhrase(ph);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    dato.setPhrase(new Phrase(""+b.getValcapitalgan().get(i),new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
                    dato.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
                    capitalgan+=b.getValcapitalgan().get(i);
                    dato.setPhrase(new Phrase(""));
                    dato.setBorder(Rectangle.NO_BORDER);
                    table.addCell(dato);
               }
               cel.setPhrase(new Phrase("Suma capital ganado",new Font(FontFamily.HELVETICA,10,Font.UNDERLINE)));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""));
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
               cel.setPhrase(new Phrase(""+capitalgan,new Font(FontFamily.HELVETICA,10,Font.NORMAL)));
               cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
               cel.setBorder(Rectangle.NO_BORDER);
               table.addCell(cel);
           }
           
           double sumapasivoycapital=0;
           sumapasivoycapital=pasivocp+pasivolp+capitalcon+capitalgan;
           
           cel.setPhrase(new Phrase("SUMA PASIVO MAS CAPITAL CONTABLE",new Font(FontFamily.HELVETICA,10,Font.BOLD)));
           cel.setBorder(Rectangle.NO_BORDER);
           cel.setHorizontalAlignment(Element.ALIGN_LEFT);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""));
           cel.setBorder(Rectangle.NO_BORDER);
           table.addCell(cel);
           cel.setPhrase(new Phrase(""+sumapasivoycapital,new Font(FontFamily.HELVETICA,10,Font.BOLD)));
           cel.setBorder(Rectangle.BOTTOM);
           cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
           table.addCell(cel); 
           
           
           
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
