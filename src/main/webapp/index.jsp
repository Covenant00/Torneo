<%-- 
    Document   : index
    Created on : 26 jun 2022, 13:34:01
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Quiniela Mundial 2022</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <%
        String error = (String) session.getAttribute("error");
    %>
    <body>
        <input type="text" name="error"  id="error" value="<%=error%>" hidden="">
        <div class="container">
            <div class="offset-md-4 col-md-4" style="background-color: #eee; padding: 2%;" >
                <form method="post" action="login">
                    <div class="mb-3">
                        <img src="https://torneo2022.scm.azurewebsites.net/wwwroot/webapps/torneo-1.0-SNAPSHOT/IMG/LOGO-QATAR-2022-5.jpg" class="img-fluid">
                        <br><br>
                        <label for="exampleInputEmail1" class="form-label">Nombre de usuario</label>
                        <input type="text" class="form-control" id="email" name="Usuario">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-danger" type="submit">Entrar</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
    <script type="text/javascript">

        $(window).on('load', function () {
            var error = document.getElementById("error").value;
            if (error === "null") {
            } else {
                Swal.fire(
                        'Atención',
                        error,
                        'error'
                        );
            }
        });
    </script>

    <%

        if (error != null) {
            request.getSession().removeAttribute("error");
        }
    %>
</html>
