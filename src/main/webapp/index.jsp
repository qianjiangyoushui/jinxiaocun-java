<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getSession().getServletContext().getAttribute("path").toString();response.sendRedirect(path+"index/index.action");%>

