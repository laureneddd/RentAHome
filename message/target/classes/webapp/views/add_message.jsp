<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%> <%@ page
language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Send Message</title>
  </head>
  <body>
    <h1>Send a Message</h1>
    <form action="/message/addmessage" method="post">
      <label for="senderId">Sender ID:</label>
      <input type="text" id="senderId" name="senderId" required /><br />
      <label for="receiverId">Receiver ID:</label>
      <input type="text" id="receiverId" name="receiverId" required /><br />
      <label for="content">Content:</label>
      <textarea id="content" name="content" required></textarea><br />
      <label for="time">Time:</label>
      <input type="date" id="time" name="time" required /><br />
      <button type="submit">Send Message</button>
    </form>
    <p>${messageSuccess}</p>
  </body>
</html>
