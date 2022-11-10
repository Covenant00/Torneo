<%--
    Document   : inicio
    Created on : 26 jun 2022, 16:11:07
    Author     : root
--%>

<%@page import="CONTROLLER.UserComparator"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.HashMap"%>
<%@page import="MODEL.Maestro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Equipo"%>
<%@page import="DAO.Datos"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.LinkedHashMap"%>
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
        ArrayList<Maestro> maestro = new ArrayList<>();
        ArrayList<Maestro> RespuestasUsuarios = new ArrayList<>();
        ArrayList<Integer> Usuarios = new ArrayList<>();
        Usuarios = d.usuarios();
        maestro = d.Maestro();

        HashMap<Integer, Integer> tabla = new HashMap<Integer, Integer>();

%>

<%    int RespuestasCorrectas = 0;
    for (Integer i : Usuarios) {
        RespuestasUsuarios = d.Respuestas(i);

        for (Maestro r : RespuestasUsuarios) {
            for (Maestro m : maestro) {
                if (r.getIdE1() == m.getIdE1() && r.getIdE2() == m.getIdE2() && r.getGana1() == m.getGana1() && r.getGana2() == m.getGana2() && r.getEmpate() == m.getEmpate()) {
                    RespuestasCorrectas++;
                }
            }
        }

        RespuestasUsuarios.clear();
        tabla.put(i, RespuestasCorrectas);
        RespuestasCorrectas = 0;

    }

    UserComparator comparator = new UserComparator(tabla);
    Map<Integer, Integer> result = new TreeMap<>(comparator);
    result.putAll(tabla);

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Tabla de líderes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet">
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
            <h1><img src="IMG/LOGO-QATAR-2022-5.jpg" class="img-fluid" width="30%;"><br><strong>Tabla de Líderes</strong></h1>
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

        <table id="lideres" class="table table-responsive table-bordered table-striped ">
            <thead class="text-center" style="background-color: #520b24; color: white;">

            <th>#</th>
            <th>Usuario</th>
            <th>Aciertos</th>
            </thead>
            <%int numeros = 1;%>
            <tbody class="text-center">

                <%for (Integer key : tabla.keySet()) {
                        Integer value = tabla.get(key);
                %>
                <tr>
                    <td></td>
                    <td><%=d.NombreUsuario(key)%></td>
                    <td><%= value%></td>
                </tr>
                <%numeros++;
                    }%>
            </tbody>

        </table>

    </div>

    <br><br>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        var table = $('#lideres').DataTable({
            "order": [2, 'desc'],
            "aLengthMenu": [20]

        });

        table.on('order.dt search.dt', function () {
            table.column(0, {
                search: 'applied',
                order: 'applied'
            }).nodes().each(function (cell, i) {
                console.log(cell);
                cell.innerHTML = i + 1;
            });
        }).draw();
    });
</script>

</html>
<%}%>