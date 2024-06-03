
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .btn-login {
        font-size: 0.9rem;
        letter-spacing: 0.05rem;
        padding: 0.75rem 1rem;
    }

    .btn-google {
        color: green !important;
        background-color: #ea4335;
    }

    .btn-facebook {
        color: green !important;
        background-color: #3b5998;
    }
</style>

<body>
    <jsp:include page="header.jsp"></jsp:include>

        <!-- Modal Search Start -->
        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="search">
                        <div class="modal-body d-flex align-items-center">
                            <div class="input-group w-75 mx-auto d-flex">
                                <input name="search" type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                <button id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal Search End -->

        <form action="login" method="post">
            <div class="container">
                <div class="row">
                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div class="card border-0 shadow rounded-3" style="margin-top: 200px" >
                            <div class="card-body p-4 p-sm-5">
                                <h5 class="card-title text-center mb-5 fw-light fs-5">Log In</h5>
                                <h5 class="text-danger">${requestScope.errorLogin}</h5>
                            <h5 class="text-success">${requestScope.logout}</h5>

                            <form>
                                <div class="form-floating mb-3">
                                    <input name="username" type="text" class="form-control" id="floatingInput" placeholder="Username">
                                    <label for="floatingInput">Username</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                    <label for="floatingPassword">Password</label>
                                </div>

                                <div class="d-flex justify-content-between">
                                    <div class="form-check mb-3">
                                        <input class="form-check-input" type="checkbox" value="" id="rememberPasswordCheck" name="rem">
                                        <label class="form-check-label" for="rememberPasswordCheck">
                                            Remember password
                                        </label>
                                    </div>
                                    <div class="form-check mb-3">
                                        <a href="#">Forgot Password</a>
                                    </div>
                                </div>
                                <div class="d-grid">
                                    <input type="submit" class="btn btn-primary btn-login text-uppercase fw-bold" value="Log in">

                                </div>
                                <hr class="my-4">
                                <div class="d-grid mb-2">
                                    <button class="btn btn-google btn-login text-uppercase fw-bold" type="submit">
                                        <i class="fab fa-google me-2"></i> Log in with Google
                                    </button>
                                </div>
                                <div class="d-grid">
                                    <button class="btn btn-facebook btn-login text-uppercase fw-bold" type="submit">
                                        <i class="fab fa-facebook-f me-2"></i> Log in with Facebook
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <jsp:include page="footer.jsp"></jsp:include>
</body>

