/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Kaco
 */
@WebServlet(name="MainServlet", urlPatterns={"/MainServlet"})
public class MainServlet extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

 
            String path = getServletContext().getRealPath("/WEB-INF/");
            String XMLFileName = path + "/main.xml";
            String XSLFileName = path + "/desc.xsl";

            StreamSource XMLSource = new StreamSource(XMLFileName);
            StreamSource XSLSource = new StreamSource(XSLFileName);


            StreamResult homeOutput = new StreamResult(out);


            TransformerFactory xFactory = TransformerFactory.newInstance();


            Transformer optimusPrime = xFactory.newTransformer(XSLSource);


            optimusPrime.transform(XMLSource, homeOutput);

        } catch (TransformerConfigurationException ex) {

            System.out.println("Encountered TransformerConfiguration Error: " + ex.getMessage());
            
        } catch (TransformerException ex) {

            System.out.println("Encountered Transformer Error: " + ex.getMessage());

        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}