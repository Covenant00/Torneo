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
        maestro = d.Maestro();

        int respuesta = d.BuscaRespuesta(idUsuario);


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Bienvenido</title> 
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
            <h1><img src="https://torneo2022.scm.azurewebsites.net/wwwroot/webapps/torneo-1.0-SNAPSHOT/IMG/LOGO-QATAR-2022-5.jpg" class="img-fluid" width="30%;"><br>&nbsp;&nbsp;Bienvenido <strong><%= nombreUsuario%></strong></h1>
        </div>
    </div>

    <div class="container-fluid">
        <!--navbar-->
        <nav class="navbar  navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="respuestas.jsp">Mi Quiniela</a>
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
        <%if (respuesta == 0) {%>
        <table class="table table-responsive table-bordered table-striped ">
            <thead class="text-center" style="background-color: #520b24; color: white;">
            <th>Gana Local</th>
            <th>Local</th>
            <th>Empate</th>
            <th>Visitante</th>
            <th>Gana Visita</th>
            </thead>

            <form method="post" action="insertaQuiniela">
                <input  type="text" name="idUsuario" value="<%= idUsuario%>" hidden="">
                <%for (Maestro m : maestro) {%>

                <tr>
                    <td class="text-center"> <br><input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="local" required=""></td>
                        <% equipo = d.EquiposxID(m.getIdE1());%>
                    <td ><center> <img src="https://torneo2022.scm.azurewebsites.net/wwwroot/webapps/torneo-1.0-SNAPSHOT/<%= equipo.get(1)%>" class="img-fluid">&nbsp;<strong> <%= equipo.get(0)%></strong></center></td>
                    <% equipo.clear(); %>
                    <% equipo = d.EquiposxID(m.getIdE2());%>
                <td class="text-center"><br> <input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="empate" required=""></td>
                <td><center> <img src="https://torneo2022.scm.azurewebsites.net/wwwroot/webapps/torneo-1.0-SNAPSHOT/<%= equipo.get(1)%>" class="img-fluid">  &nbsp;<strong> <%= equipo.get(0)%></strong></center></td>
                <td class="text-center"><br> <input class="form-check-input" type="radio" name="<%= m.getIdMaestro()%>" value="visita" required=""></td>
                    <% equipo.clear(); %>
                </tr>

                <%}%>
        </table>

        <div class="d-grid gap-2">
            <button class="btn" style="background-color:  #520b24; color: white;" type="submit">Enviar Resultados</button>
        </div>

    </form>
    <%} else {%>
    <center>
        <br><br>
        <h1>Gracias por participar, puede revisar sus respuestas en <a href="respuestas.jsp">este enlace</a></h1>
    </center>
    <%}%>
</div>

<br><br>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<script>
    $(window).on('load', function () {
        var OK = document.getElementById("OK").value;
        if (OK === "null") {
        } else {
            Swal.fire(
                    'Todo bien :)',
                    'Se han registrado tus respuestas',
                    'success'
                    );
        }
    });
</script>

<%
    if (OK != null) {
        request.getSession().removeAttribute("OK");
    }
%>
</html>
<%}%>