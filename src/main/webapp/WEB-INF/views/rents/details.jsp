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
                            <h3 class="profile-username text-center"> Reservation ${rent.id} :  ${rent.debut} - ${rent.fin}</h3>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#user" data-toggle="tab">Client</a></li>
                            <li><a href="#car" data-toggle="tab">Voiture</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="user">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th>Email</th>
                                            <th>Naissance</th>
                                        </tr>
                                        <tr>
                                            <td>${client.findById(rent.client_id).id}</td>
                                            <td>${client.findById(rent.client_id).nom}</td>
                                            <td>${client.findById(rent.client_id).prenom}</td>
                                            <td>${client.findById(rent.client_id).email}</td>
                                            <td>${client.findById(rent.client_id).naissance}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="car">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Constructeur</th>
                                            <th style=>Nombre de places</th>
                                        </tr>
                                        <tr>
                                            <td>${vehicle.findById(rent.vehicle_id).id}</td>
                                            <td>${vehicle.findById(rent.vehicle_id).constructeur}</td>
                                            <td>${vehicle.findById(rent.vehicle_id).nb_places}</td>
                                        </tr>
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