package com.example.epam_marathon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "RequestServlet", value = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BigDecimal phone = new BigDecimal(request.getParameter("phone").substring(4));
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            PreparedStatement preparedStatement = connection.prepareStatement("select status from REQUESTS where PHONE=? order by DATE desc limit 1");
            preparedStatement.setBigDecimal(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            //if a request for this number exists it should have status complete to proceed
            if(resultSet.next() && !Objects.equals(resultSet.getString("status"), "complete"))
                response.sendRedirect("error.html");
            else {
                preparedStatement = connection.prepareStatement("insert into requests(phone,name,request,status,date) values (?,?,?,'new',CURRENT_TIMESTAMP())");
                preparedStatement.setBigDecimal(1,phone);
                preparedStatement.setString(2,request.getParameter("name"));
                preparedStatement.setString(3,request.getParameter("request"));
                preparedStatement.executeUpdate();
                response.sendRedirect("success.html");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
