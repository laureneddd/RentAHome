<%@ page import="com.rentahome.entity.Message" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Live Chat</title>
    <link
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding-top: 10%;
        }

        .chatbox {
            width: 360px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .chatbox-header {
            background-color: #56c4d8;
            color: white;
            padding: 15px;
            text-align: center;
            font-weight: bold;
        }

        .chatbox-body {
            background-color: white;
            padding: 15px;
            max-height: 400px;
            overflow-y: auto;
        }

        .chatbox-footer {
            padding: 10px;
            background-color: #f5f5f5;
            border-top: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .message {
            margin-bottom: 15px;
            display: flex;
        }

        .message-text {
            padding: 10px 15px;
            border-radius: 15px;
            max-width: 80%;
        }

        .message.system {
            justify-content: flex-start;
        }

        .message.system .message-text {
            background-color: #e9f5fa;
        }

        .message.user {
            justify-content: flex-end;
        }

        .message.user .message-text {
            background-color: #f1f1f1;
        }

        .chatbox-footer input[type="text"] {
            flex: 1;
            padding: 10px;
            border: none;
            border-radius: 20px;
            box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
            outline: none;
        }

        .chatbox-footer button {
            border: none;
            background-color: #56c4d8;
            color: white;
            border-radius: 20px;
            padding: 10px 15px;
            cursor: pointer;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="chatbox">
    <div class="chatbox-header">Live Chat</div>
    <div class="chatbox-body">
        <div class="message system">
            <div class="message-text">
                Hello and thank you for reserving our house. Please confirm your
                check-in date.
            </div>
        </div>

        <%
            List<Message> messages = (List<Message>) request.getAttribute("messages");
            if (messages != null) {
                for (Message message : messages) {
        %>
        <!-- <div class="message <%= message.getSenderId().equals(session.getAttribute("username")) ? "user" : "system" %>"> -->
        <div class="message user">
            <div class="message-text"><%= message.getContent() %></div>
        </div>
        <%
                }
            }
        %>
    </div>
    <div class="chatbox-footer">
        <form
                action="${pageContext.request.contextPath}/message/sendmessage"
                method="post"
        >
            <input
                    type="hidden"
                    name="senderId"
                    value="${sessionScope.username}"
            />
            <input type="hidden" name="receiverId" value="${receiverId}"/>
            <input
                    type="text"
                    name="content"
                    id="messageInput"
                    placeholder="Type your message"
                    required
            />
            <button type="submit">Send</button>
        </form>
    </div>
</div>
</body>
</html>
