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
    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <h3 class="profile-username text-center"> ${vehicle.constructeur} (${vehicle.nb_places} places) </h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Reservation(s)</b> <a class="pull-right">${nbRentals}</a>
                </li>
                <li class="list-group-item">
                  <b>Client(s)</b> <a class="pull-right">${nbClients}</a>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
              <li><a href="#user" data-toggle="tab">Clients</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="rents">
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Client</th>
                      <th>Date de debut</th>
                      <th>Date de fin</th>
                    </tr>
                    <c:forEach items="${rentals}" var="rental">
                      <tr>
                        <td>${rental.id}</td>
                        <td>${clients.findById(rental.client_id).prenom} ${clients.findById(rental.client_id).nom}</td>
                        <td>${rental.debut}</td>
                        <td>${rental.fin}</td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </div>
              <!-- /.tab-pane -->
              <div class="tab-pane" id="user">
                <!-- /.box-header -->
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Nom</th>
                      <th>Prenom</th>
                      <th>Email</th>
                      <th>Naissance</th>
                    </tr>
                    <c:forEach items="${rentals}" var="rental">
                      <tr>
                        <td>${clients.findById(rental.client_id).id}</td>
                        <td>${clients.findById(rental.client_id).nom}</td>
                        <td>${clients.findById(rental.client_id).prenom}</td>
                        <td>${clients.findById(rental.client_id).email}</td>
                        <td>${clients.findById(rental.client_id).naissance}</td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </div>
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>

  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>