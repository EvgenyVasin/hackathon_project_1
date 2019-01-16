/**
 * Created by safin.v on 11.10.2016.
 */
var eventSocket = null;
var commandSocket = null;
var stompEventClient = null;
var stompCommandClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
}

function connectEventSockets() {
    eventSocket = new SockJS('/asterEvent');
    stompEventClient = Stomp.over(eventSocket);
    stompEventClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompEventClient.subscribe('/topic/asterEvent', function(greeting){
            addText("tbl",greeting.body);
        });
    });
}

function disconnectEventSockets() {
    if (stompEventClient != null) {
        stompEventClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function connect() {
    var socket = new SockJS('/aster');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/aster', function(greeting){
            addText("tbl",greeting.body);
        });
        stompClient.subscribe('/topic/asterEvent', function(greeting){
            addText("tbl",greeting.body);
        });
    });
}
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}