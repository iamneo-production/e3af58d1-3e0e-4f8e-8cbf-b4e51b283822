package com.contactlist.examly.controller;

import javax.servlet.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contactlist.examly.service.ContactDao;
import com.contactlist.examly.model.Contact;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class ContactServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ContactDao contactDao;

    public void init() {
        contactDao = new ContactDao();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("Action: "+ action);

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/print2":
                    printLog(request, response);
                    break;    
                case "/insert":
                    addContact(request, response);
                    break;
                case "/delete":
                    deleteContact(request, response);
                    break;
                case "/listById":
                    listContactById(request, response);
                    break;
                case "/list":
                    listAllContacts(request, response);
                    break;
                case "/edit":
                    updateContact(request, response);
                    break;
                default:
                    listAllContacts(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void listContactById(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        Contact contact = contactDao.viewContactById(Integer.valueOf(request.getParameter("contactId")));
        request.setAttribute("contact", contact);
        request.setAttribute("Owner", "Charan");
        System.out.println(contact.getContactName());
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void listAllContacts(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        System.out.print("Entered listAll called");
        List < Contact > contactList = contactDao.viewContacts();
        
        request.setAttribute("contactsList", contactList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        System.out.print("request :" +request.getAttribute("contactsList"));
        dispatcher.forward(request, response);
    }

    private void printLog(HttpServletRequest request, HttpServletResponse response) 
    throws SQLException, IOException, ServletException{
        List < Contact > contactList = contactDao.viewContacts();
        contactList.forEach(i->System.out.println(i.getContactName()) );
    }

    // private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    // throws ServletException, IOException {
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    //     dispatcher.forward(request, response);
    // }

    // private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    // throws SQLException, ServletException, IOException {
    //     int id = Integer.parseInt(request.getParameter("id"));
    //     User existingUser = userDAO.selectUser(id);
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    //     request.setAttribute("user", existingUser);
    //     dispatcher.forward(request, response);

    // }

    private void addContact(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String contactName = request.getParameter("contactName");
        int contactNumber = Integer.valueOf(request.getParameter("contactNumber"));
        String contactImage = request.getParameter("contactImage");
        Contact contact = new Contact(contactName, contactNumber, contactImage);
        contactDao.addContact(contact);
        response.sendRedirect("list");
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        String contactName = request.getParameter("contactName");
        int contactNumber = Integer.valueOf(request.getParameter("contactNumber"));
        String contactImage = request.getParameter("contactImage");

        Contact contact = new Contact(contactId, contactName, contactNumber, contactImage);
        contactDao.updateContact(contact);
        response.sendRedirect("list");
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        contactDao.deleteContact(contactId);
        response.sendRedirect("list");
    }
    
}
