<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{index}">
<head>
</head>
<body>
<div layout:fragment="main">
    <!-- RIBBON -->
    <div th:replace="/common/fragment :: head(${res})"></div>
    <!-- END RIBBON -->
</div>
<script></script>
</body>
</html>