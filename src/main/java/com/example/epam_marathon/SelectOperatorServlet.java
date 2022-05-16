package com.example.epam_marathon;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "SelectOperatorServlet", value = "/select_operator")
public class SelectOperatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<Integer,String> operatorList = new HashMap<>();
        try{
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            ResultSet result = connection.createStatement().executeQuery("SELECT operator_id, name FROM operators ORDER BY name");
            while (result.next()) operatorList.put(result.getInt("operator_id"), result.getString("name"));
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        request.setAttribute("operatorList",operatorList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("select_operator.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
