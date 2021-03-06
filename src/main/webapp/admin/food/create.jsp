<%@ page import="com.example.example_fpt_final.entity.Food" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.example_fpt_final.entity.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/25/2021
  Time: 12:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    Food food = (Food) request.getAttribute("food");
    if(food==null){
        food = new Food();
    }
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    if(errors == null){
        errors = new HashMap<>();
    }
    ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/admin/include/header.jsp">
        <jsp:param name="title" value="Food form"/>
        <jsp:param name="description" value="Food form"/>
        <jsp:param name="keywords" value="admin, page...."/>
    </jsp:include>
</head>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
    <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i
            class="fa fa-bars"></i>  Menu
    </button>
    <span class="w3-bar-item w3-right">Admin page</span>
</div>

<jsp:include page="/admin/include/left-menu.jsp"/>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

    <!-- Header -->
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-dashboard"></i> Food form</b></h5>
    </header>

    <div class="w3-padding w3-margin-bottom">
        <%
            if(errors.size() > 0){
        %>
        <div class="w3-panel w3-pale-red w3-border">
            <h3>Danger!</h3>
            <ul>
                <%
                    for (Map.Entry<String, String> entry :
                            errors.entrySet()) {
                %>
                <li><%=entry.getValue()%></li>
                <%}%>
            </ul>
        </div>
        <%}%>
        <form action="/admin/food/create" method="post" class="w3-container w3-padding w3-card-4 w3-margin">
            <%--            <div class="w3-margin">--%>
            <%--                <label>Id</label>--%>
            <%--                <input class="w3-input" type="text" name="id">--%>
            <%--            </div>--%>
            <div class="w3-margin">
                <label>Name</label>
                <input class="w3-input" type="text" name="name" value="<%=food.getName()%>" required>
            </div>
            <div class="w3-margin">
                <label>Code</label>
                <input class="w3-input" type="text" name="code" value="<%=food.getCode()%>" required>
            </div>
            <div class="w3-margin">
                <label>Category</label>
                <select class="w3-select" name="category" required>
                    <option value="" disabled selected>Choose your category</option>
                    <%
                        for (Category category:
                                categories) {
                    %>
                    <option <%if (food.getCategory() == category.getId()) {%> <%="selected"%> <%}%> value="<%=category.getId()%>"><%=category.getName()%></option>
                    <%}%>
                </select>
            </div>
            <div class="w3-margin">
                <label>Price</label>
                <input class="w3-input" type="number" name="price" value="<%=food.getPrice()%>" required>
            </div>
            <div class="w3-margin">
                <label>Description</label>
                <input class="w3-input" type="text" name="description" value="<%=food.getDescription()%>">
            </div>
            <div class="w3-margin">
                <label>Thumbnail</label>
                <input class="w3-input" type="url" name="thumbnail" value="<%=food.getThumbnail()%>" required>
            </div>
            <div class="w3-margin">
                <label>Status</label>
                <select class="w3-select" name="status" required>
                    <option value="" disabled selected>Choose your status</option>
                    <option <%if (food.getStatus() == 1) {%> <%="selected"%> <%}%> value="1">Sell</option>
                    <option <%if (food.getStatus() == 2) {%> <%="selected"%> <%}%> value="2">Stop selling</option>
                    <option <%if (food.getStatus() == 3) {%> <%="selected"%> <%}%> value="3">Delete</option>
                </select>
            </div>
            <button class="w3-btn w3-blue w3-margin">Submit</button>
        </form>
    </div>
    <hr>
    <!-- Footer -->
    <jsp:include page="/admin/include/footer.jsp"/>
    <!-- End page content -->
</div>
<jsp:include page="/admin/include/script.jsp"/>
</body>
</html>

