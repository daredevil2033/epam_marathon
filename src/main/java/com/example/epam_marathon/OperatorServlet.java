package com.example.epam_marathon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

@WebServlet(name = "OperatorServlet", value = "/operator")
public class OperatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int operator_id = Integer.parseInt(request.getParameter("operator_id"));
        request.setAttribute("operator_id",operator_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("operator.jsp");
        int request_id;
        try {
            Connection connection = (Connection) getServletContext().getAttribute("connection");
            //check if some request is being processed by current operator
            PreparedStatement preparedStatement = connection.prepareStatement("select request_id from OPERATORS where OPERATOR_ID=?");
            preparedStatement.setInt(1,operator_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                request_id = resultSet.getInt("request_id");
                //if not check whether there are any unassigned requests
                if(resultSet.wasNull()){
                    resultSet = connection.createStatement().executeQuery("select request_id from REQUESTS where STATUS='new' order by DATE limit 1");
                    //if there is one assign it to the current operator
                    if(resultSet.next()) {
                        request_id = resultSet.getInt("request_id");
                        preparedStatement = connection.prepareStatement("update OPERATORS set REQUEST_ID=? where OPERATOR_ID=?");
                        preparedStatement.setInt(1, request_id);
                        preparedStatement.setInt(2, operator_id);
                        preparedStatement.executeUpdate();
                        preparedStatement = connection.prepareStatement("update REQUESTS set STATUS='processed' where REQUEST_ID=?");
                        preparedStatement.setInt(1, request_id);
                        preparedStatement.executeUpdate();
                    }
                }
                if(request_id!=0){
                    request.setAttribute("request_id",request_id);
                    //show info about request to the operator
                    preparedStatement = connection.prepareStatement("select phone, name, request from REQUESTS where REQUEST_ID=?");
                    preparedStatement.setInt(1,request_id);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    request.setAttribute("phone","+380"+resultSet.getBigDecimal("phone"));
                    request.setAttribute("name",resultSet.getString("name"));
                    String req = resultSet.getString("request");
                    if (resultSet.wasNull()) req = "";
                    request.setAttribute("request",req);
                    dispatcher.forward(request,response);
                }
                else{
                    request.setAttribute("phone","No unprocessed requests.");
                    request.setAttribute("name","No unprocessed requests.");
                    request.setAttribute("request","No unprocessed requests. Try again later.");
                    dispatcher.forward(request,response);
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("operator.jsp");
        int operator_id = Integer.parseInt(request.getParameter("operator_id"));
        request.setAttribute("operator_id",operator_id);
        String req_id = request.getParameter("request_id");
        if(!req_id.isEmpty()){
            int request_id = Integer.parseInt(req_id);
            try {
                Connection connection = (Connection) getServletContext().getAttribute("connection");
                //free the operator
                PreparedStatement preparedStatement = connection.prepareStatement("update OPERATORS set REQUEST_ID=null where OPERATOR_ID=?");
                preparedStatement.setInt(1,operator_id);
                preparedStatement.executeUpdate();
                //complete the request
                preparedStatement = connection.prepareStatement("update REQUESTS set STATUS='complete' where REQUEST_ID=?");
                preparedStatement.setInt(1,request_id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ServletException(e);
            }
            dispatcher.forward(request,response);
        }
        else {
            request.setAttribute("phone","Request not selected.");
            request.setAttribute("name","Request not selected.");
            request.setAttribute("request","Request not selected. Press Next to select next request.");
            dispatcher.forward(request,response);
        }
    }
}
