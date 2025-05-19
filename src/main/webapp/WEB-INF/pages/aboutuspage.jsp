<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>About Us</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutuspage.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <!-- Hero Section -->
    <section class="about-image-section" style="background: url('${pageContext.request.contextPath}/resources/images/customer/ref.jpg') no-repeat center center/cover;">
        <div class="image-overlay">
            <h1>About Us</h1>
        </div>
    </section>

    <!-- Content Section with Two-Column Layout -->
    <section class="about-content-section">
        <div class="container">
            <div class="about-layout">
                <div class="about-image">
                    <img src="${pageContext.request.contextPath}/resources/images/customer/ref.jpg" alt="About Us Team">
                </div>
                <div class="about-text">
                    <div class="tabs">
                        <span class="tab" onclick="showTab('vision')">Vision</span>
                        <span class="tab" onclick="showTab('mission')">Mission</span>
                        <span class="tab" onclick="showTab('philosophy')">Philosophy</span>
                    </div>
                    <div id="vision" class="tab-content">
                        <p>To be a 10 trillion Dollar Enterprise by 2030</p>
                    </div>
                    <div id="mission" class="tab-content" style="display:none;">
                        <p>Establish a dynamic entertainment conglomerate with core competencies in gaming consoles, interactive entertainment, cloud gaming, digital content, and esports and achieve recognition as an annual Innovation Leader among the top tech-driven companies in Asia.</p>
                    </div>
                    <div id="philosophy" class="tab-content" style="display:none;">
                        <p>To drive immersive innovation and gaming excellence, shaping the future of entertainment through passion, collaboration, and sustainable technology.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp" />

    <script>
        function showTab(tabName) {
            const contents = document.getElementsByClassName('tab-content');
            for (let content of contents) {
                content.style.display = 'none';
            }
            document.getElementById(tabName).style.display = 'block';
        }
    </script>
</body>
</html>