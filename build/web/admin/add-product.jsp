<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="container" style="margin-top: 200px;">
        <h2 class="text-center">Add New Product</h2>
    <c:if test="${not empty msg}">
        <h3 class="text-center text-success">${msg}</h2>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <h3 class="text-center text-danger">${errorMessage}</h2>
            </c:if>
            <form action="add-product" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="fruitName">Name:</label>
                    <input type="text" class="form-control" id="fruitName" name="name" required>
                </div>
                <div class="form-group">
                    <label for="fruitImage">Image:</label>
                    <input type="file" class="form-control" id="fruitImage" name="image" required>
                    <img id="imagePreview" src="#" alt="Image Preview" style="display: none; max-width: 200px; max-height: 200px;"/>
                </div>
                <div class="form-group">
                    <label for="fruitCategory">Category:</label>
                    <select class="form-control" id="fruitCategory" name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fruitPrice">Price ($):</label>
                    <input type="number" step="0.01" class="form-control" id="fruitPrice" name="price" required>
                </div>
                <div class="form-group">
                    <label for="fruitDescription">Description:</label>
                    <textarea class="form-control" id="fruitDescription" name="description" rows="3" required=""></textarea>
                </div>
                <div class="form-group">
                    <label for="fruitWeight">Weight (kg):</label>
                    <input type="number" step="0.01" class="form-control" id="fruitWeight" name="weight" required>
                </div>
                <div class="form-group">
                    <label >Quantity :</label>
                    <input type="number" step="1" class="form-control" name="quantity" required>
                </div>
                <div class="form-group">
                    <label for="fruitCountry">Origin:</label>
                    <input type="text" class="form-control" id="fruitCountry" name="origin" required>
                </div>
                <div class="form-group">
                    <label for="fruitQuality">Quality:</label>

                    <select name="quality" id="fruitQuality" class="form-control">
                        <option value="A">A ( Perfect )</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                        <option value="E">E</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="fruitTest">Test food safety:</label>
                    <select name="test" class="form-control" name="test">
                        <option value="false">Passed</option>
                        <option value="true">Failed</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Add Fruit</button>
            </form>
            </div>

            <jsp:include page="footer.jsp"/>
            <script>
                document.getElementById('fruitImage').addEventListener('change', function (event) {
                    var reader = new FileReader(); // Create a new instance of the FileReader
                    reader.onload = function () {
                        var output = document.getElementById('imagePreview');
                        output.src = reader.result; // Set the src of the img tag to the read file
                        output.style.display = 'block'; // Make the img tag visible
                    };
                    if (event.target.files[0]) { // Check if a file was selected
                        reader.readAsDataURL(event.target.files[0]); // Read the selected file
                    }
                });
            </script>
