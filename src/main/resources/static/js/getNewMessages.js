function getNewMessages() {
    let roomId = document.querySelector(".card").getAttribute("data-room-id");
    console.log("FETCHING");
    fetch(`/ui/room/newMessages?roomId=${roomId}`)
        .then(response => response.json())
        .then(messages => {
            // Update the chat with the new messages
            messages.forEach(message => {
                let sender = message.user.username;
                let text = message.text;
                let date = new Date(message.messageTime).toLocaleString();
                // Append the new message to the chat table
                let row = `<tr><td>${sender}</td><td>${text}</td><td>${date}</td></tr>`;
                document.querySelector("table > tbody").innerHTML += row;
            });
        });
}

// Call getNewMessages function every 5 seconds
setInterval(getNewMessages, 5000);
