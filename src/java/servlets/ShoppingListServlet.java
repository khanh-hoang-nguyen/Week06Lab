/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Khanh Nguyen
 */
public class ShoppingListServlet extends HttpServlet {

    ArrayList itemList = new ArrayList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");

        if (name == null || name.equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        if (action.equals("register")) {
            itemList = new ArrayList(); // generate new arraylist every register
            session.setAttribute("itemList", itemList); // set new arraylist
            session.setAttribute("username", username); // set username
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else if (action.equals("add")) {
            String item = request.getParameter("item");
            itemList.add(item);
            session.setAttribute("itemList", itemList);
        } else if (action.equals("delete")) {
            String item = request.getParameter("item");
            ArrayList list = (ArrayList) session.getAttribute("itemList");
            list.remove(item);
            session.setAttribute("itemList", list);
        } else if (action.equals("logout")) {
            session.invalidate();
            session = request.getSession();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }

        doGet(request, response);

    }

}
