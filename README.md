REST-for-Android
================
A skeleton REST server and Android client. Can be used as a template for building cool stuff.

How to run
----------
* Go to `rest-server` directory and run `app.js`. (You need to have <a href="http://nodejs.org/">NodeJS</a> and <a href="http://expressjs.com/">ExpressJS</a> installed.)
```
$ node app.js
```
* In android code, navigate to `MainActivity` and change the `URL` variable so that it points to the server's IP. It should read something like this:
```
private String URL = "http://<ip-address>:<port-number>/api/capitalize";
```
That's it!
