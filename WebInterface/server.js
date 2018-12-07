/*  An example server for running the RoyalField Web Interface
    This will launch on localhost port 8080 */

// Load modules
const express = require('express');
const http = require('http');
const path = require('path');

const app = express();
const server = http.createServer(app);

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/client/index.html');
});
app.use('/', express.static(__dirname + '/client'));

server.listen('8080', () => {
  console.log("Listening on port %s", server.address().port);
});
