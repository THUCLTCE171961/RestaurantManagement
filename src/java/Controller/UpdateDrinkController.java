/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.DrinkDAO;
import Model.DrinkModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateDrinkController", urlPatterns = {"/updateD"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UpdateDrinkController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateDrinkController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateDrinkController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DrinkDAO drinkDAO = new DrinkDAO();
        String drinkID = request.getParameter("drinkID");
        String drinkName = request.getParameter("drinkName");
        String beverageID = request.getParameter("beverageID");
        String description = request.getParameter("description");
        String priceParam = request.getParameter("price");
        String quantityParam = request.getParameter("quantity");

        Part file = request.getPart("drinkImg");
        String imgFileName = file.getSubmittedFileName();
        String uploadDirectory = getServletContext().getRealPath("/image/");
        String uploadPath = uploadDirectory + File.separator + imgFileName;

        try ( FileOutputStream fos = new FileOutputStream(uploadPath);  InputStream is = file.getInputStream()) {
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("updateErr", "Không cập nhật được sản phẩm");
            request.getRequestDispatcher("listDrink.jsp").forward(request, response);
            return;
        }

        BigDecimal price = null;
        int quantity = 0;

        try {
            if (priceParam != null && !priceParam.isEmpty()) {
                price = new BigDecimal(priceParam);
            } else {
                request.setAttribute("updateErr", "Không cập nhật được sản phẩm");
                request.getRequestDispatcher("listDrink.jsp").forward(request, response);
                return;
            }
            if (quantityParam != null && !quantityParam.isEmpty()) {
                quantity = Integer.parseInt(quantityParam);
            } else {
                request.setAttribute("updateErr", "Không cập nhật được sản phẩm");
                request.getRequestDispatcher("listDrink.jsp").forward(request, response);
                return;
            }

            DrinkModel updatedDrink = new DrinkModel();
            updatedDrink.setDrinkID(drinkID);
            updatedDrink.setDrinkImg(imgFileName);
            updatedDrink.setDrinkName(drinkName);
            updatedDrink.setBeverageID(beverageID);
            updatedDrink.setDescription(description);
            updatedDrink.setPrice(price);
            updatedDrink.setQuantity(quantity);

            drinkDAO.updateDrink(updatedDrink);

            request.setAttribute("updateSuccess", "Cập nhật sản phẩm thành công");
            request.getRequestDispatcher("listDrink.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("updateErr", "Không cập nhật được sản phẩm");
            request.getRequestDispatcher("listDrink.jsp").forward(request, response);
        }

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
