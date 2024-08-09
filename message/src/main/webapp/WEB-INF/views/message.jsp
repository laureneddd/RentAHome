<!-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> -->
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
        align-items: center;
        height: 100vh;
        margin: 0;
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
      }
      .message-text {
        background-color: #e9f5fa;
        padding: 10px 15px;
        border-radius: 15px;
        max-width: 80%;
      }
      .message.user .message-text {
        background-color: #f1f1f1;
        text-align: left;
        margin-left: auto;
      }
      .chatbox-footer input[type="text"] {
        width: calc(100% - 70px);
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
      }
    </style>
  </head>
  <body>
    <div class="chatbox">
      <div class="chatbox-header">Live Chat</div>
      <div class="chatbox-body">
        <div class="message">
          <div class="message-text">
            Hello and thank you for reserving our house. Please confirm your
            check-in date.
          </div>
        </div>
        <div class="message user">
          <div class="message-text">Thank you, I will check in tomorrow.</div>
        </div>
      </div>
      <div class="chatbox-footer">
        <input type="text" placeholder="Type your message" />
        <button>Send</button>
      </div>
    </div>
  </body>
</html>
