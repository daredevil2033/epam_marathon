package com.example.epam_marathon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/administrator")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
            List<Request> requestList = new ArrayList<>();
            ResultSet resultSet;
            if(request.getParameter("sbutton").equals("Show new")){
                resultSet = connection.createStatement().executeQuery("select * from REQUESTS where STATUS='new'");
            }
            else {
                resultSet = connection.createStatement().executeQuery("select * from REQUESTS where STATUS='processed'");
            }
            while (resultSet.next()){
                requestList.add(new Request(
                resultSet.getInt("request_id"),
                resultSet.getBigDecimal("phone"),
                resultSet.getString("name"),
                resultSet.getString("request"),
                resultSet.getString("status"),
                resultSet.getTimestamp("date")
                ));
            }
            request.setAttribute("requestList",requestList);
            requestDispatcher.forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
