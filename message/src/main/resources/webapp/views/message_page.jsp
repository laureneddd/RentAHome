<!DOCTYPE html>
<html>
  <head>
    <title>My Messages</title>
  </head>
  <body>
    <h1>My Messages</h1>
    <table border="1">
      <tr>
        <th>Message ID</th>
        <th>Sender ID</th>
        <th>Receiver ID</th>
        <th>Content</th>
        <th>Time</th>
      </tr>
      <c:forEach items="${messages}" var="message">
        <tr>
          <td>${message.messageId}</td>
          <td>${message.senderId}</td>
          <td>${message.receiverId}</td>
          <td>${message.content}</td>
          <td>${message.time}</td>
          <td>
            <a href="/messages/${message.messageId}/replies">View Replies</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
