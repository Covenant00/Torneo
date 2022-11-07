<%-- 
    Document   : inicio
    Created on : 26 jun 2022, 16:11:07
    Author     : root
--%>

<%@page import="MODEL.Maestro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Equipo"%>
<%@page import="DAO.Datos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    if (session.getAttribute("idUsuario") == null) {
        response.sendRedirect("index.jsp");
    } else {
        String OK = (String) session.getAttribute("OK");
        int idUsuario = (Integer) session.getAttribute("idUsuario");
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        Datos d = new Datos();
        ArrayList<String> equipo = new ArrayList<>();
        ArrayList<Maestro> maestro = new ArrayList<>();
        maestro = d.Respuestas(idUsuario);


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Mis Respuestas</title> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <input type="text" id="OK" value="<%= OK%>" hidden=""> 

</head>
<body>
    <style>
        @font-face {
            font-family: source;
            src: url('FONTS/SourceSansPro-Regular.ttf');
        }
        h1 {
            font-family: source
        }
    </style>

    <div class="container-fluid">
        <div class="row text-center">
            <h1><img src="IMG/LOGO-QATAR-2022-5.jpg" class="img-fluid" width="30%;"><br><strong>Mis Respuestas</strong></h1>
        </div>
    </div>

    <div class="container-fluid">
        <!--navbar-->
        <nav class="navbar  navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="inicio.jsp">Mi Quiniela</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="lideres.jsp">Tabla de Líderes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="logout">Salir</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--navbar-->
    </div>


    <div class="container-fluid">

        <table class="table table-responsive table-bordered table-striped ">
            <thead class="text-center" style="background-color: #520b24; color: white;">
            <th>Gana Local</th>
            <th>Local</th>
            <th>Empate</th>
            <th>Visitante</th>
            <th>Gana Visita</th>
            </thead>


            <input  type="text" name="idUsuario" value="<%= idUsuario%>" hidden="">
            <%for (Maestro m : maestro) {%>

            <tr>
                <%if (m.getGana1() != 0) {%>
                <td class="text-center"> <br><input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="local" checked="" disabled=""></td>
                    <%} else {%>
                <td class="text-center"> <br><input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="local" disabled=""></td>
                    <%}%>
                    <% equipo = d.EquiposxID(m.getIdE1());%>
                <td ><center> <img src="IMG/<%= equipo.get(1)%>" class="img-fluid">&nbsp;<strong> <%= equipo.get(0)%></strong></center></td>
                <% equipo.clear(); %>
                <% equipo = d.EquiposxID(m.getIdE2());%>
                <%if (m.getEmpate() != 0) {%>
            <td class="text-center"><br> <input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="empate" checked="" disabled=""></td>
                <%} else {%>
            <td class="text-center"><br> <input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="empate" disabled=""></td>
                <%}%>
            <td><center> <img src="IMG/<%= equipo.get(1)%>" class="img-fluid">  &nbsp;<strong> <%= equipo.get(0)%></strong></center></td>
                <% equipo.clear();%>
                <%if (m.getGana2() != 0) {%>
            <td class="text-center"><br> <input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="visita"checked="" disabled=""></td>
                <%} else {%>
            <td class="text-center"><br> <input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="visita" disabled=""></td>
                <%}%>

            </tr>

            <%}%>
        </table>

    </div>

    <br><br>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


</html>
<%}%>