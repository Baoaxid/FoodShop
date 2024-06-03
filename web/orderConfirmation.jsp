<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<jsp:include page="header.jsp"/>
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
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div style="margin-top: 200px" >
                <h1>Thank You for Your Order!</h1>
                <p>Your order has been successfully placed.</p>



                <p>If you have any questions, please contact us at <a href="mailto:support@example.com">support@example.com</a>.</p>

                <a href="shop" class="button">Continue Shopping</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>

