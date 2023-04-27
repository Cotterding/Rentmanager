<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Reservations
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <!-- Horizontal Form -->
          <div class="box">
            <!-- form start -->
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/rents/update?id=${rent.id}">
              <div class="box-body">
                <div class="form-group">
                  <label for="car" class="col-sm-2 control-label">Voiture</label>

                  <div class="col-sm-10">
                    <select class="form-control" id="car" name="car" >
                      <c:forEach var="vehicle" items="${vehicles}">
                        <c:choose>
                          <c:when test="${vehicle.id eq rent.vehicle_id}">
                            <option value="${vehicle.id}" selected>${vehicle.constructeur} (${vehicle.nb_places} places)</option>
                          </c:when>
                          <c:otherwise>
                            <option value="${vehicle.id}">${vehicle.constructeur} (${vehicle.nb_places} places)</option>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="client" class="col-sm-2 control-label">Client</label>

                  <div class="col-sm-10">
                    <select class="form-control" id="client" name="client">
                      <c:forEach var="client" items="${clients}">
                        <c:choose>
                          <c:when test="${client.id eq rent.client_id}">
                            <option value="${client.id}" selected>${client.prenom} ${client.nom}</option>
                          </c:when>
                          <c:otherwise>
                            <option value="${client.id}">${client.prenom} ${client.nom}</option>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label for="begin" class="col-sm-2 control-label">Date de debut</label>

                  <div class="col-sm-10">
                    <input type="date" class="form-control" id="begin" name="begin" required value="${rent.debut}">
                  </div>
                </div>
                <div class="form-group">
                  <label for="end" class="col-sm-2 control-label">Date de fin</label>

                  <div class="col-sm-10">
                    <input type="date" class="form-control" id="end" name="end" required value="${rent.fin}">
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="submit" class="btn btn-info pull-right">Modifier</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
    </section>
    <!-- /.content -->
  </div>

  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
  $(function () {
    $('[data-mask]').inputmask()
  });
</script>
</body>
</html>