/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
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

        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String action = (String) session.getAttribute("action");

        if (username == null || username.equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        } else {
            if (action.equals("register")) {
                if (username != null || !username.equals("")) {
                    request.setAttribute("username", username);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                            .forward(request, response);
                }
            } else if (action.equals("logout")) {
                session.invalidate();
                session = request.getSession(); // generates new session
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
            } else if (action.equals("add")) {
                String item = request.getParameter("item");
                itemList.add(item);
                request.setAttribute("itemList", itemList);
            } else if (action.equals("delete")) {
                String item = request.getParameter("item");
                itemList.remove(item);
                request.setAttribute("itemList", itemList);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);

    }

}
