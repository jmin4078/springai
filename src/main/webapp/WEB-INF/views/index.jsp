<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Spring AI</title>
</head>
<body>
<h1>Spring AI</h1>
<section>
    <form method="post">
        <input name="message" placeholder="질문을 작성해주세요">
        <select name="provider">
            <c:forEach items="${providers}" var="provider">
                <option value="${provider}">${provider}</option>
            </c:forEach>
        </select>
        <button>전송</button>
    </form>
</section>
<c:if test="${not empty answer}">
    <section>
        <p>답변 :</p>
        <div id="answer-raw" hidden><c:out value="${answer}"/></div>
        <div id="answer-rendered"></div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const raw = document.getElementById('answer-raw');
            const rendered = document.getElementById('answer-rendered');
            if (raw && rendered) {
                rendered.innerHTML = marked.parse(raw.textContent);
            }
        });
    </script>
</c:if>

</body>
</html>