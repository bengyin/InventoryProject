'use strict';

var express = require('express');

// Constants
const PORT = 9090;
const HOST = '0.0.0.0';

// App
const app = express();

app.set('views', 'views');
app.set('view engine', 'pug');

var greet = process.env.greet || 'Docker learners';
console.log(`greet value=${greet}`);

app.get('/', function(req, res) {
    res.render('home', { greet: greet});
});

app.listen(PORT, HOST);

console.log(`Running on http://${HOST}:${PORT}`);
