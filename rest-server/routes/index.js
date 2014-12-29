var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.send('Rahul\'s homepage');
});

router.get('/api', function(req, res) {
	res.send('API Home');
});

router.post('/api/capitalize', function(req, res) {
	var input = req.body.input;
	var response = {};
	if(input) {
		response.output = input.toUpperCase();
	} else {
		response.error = 'Nothing to process';
	}

	res.send(response);
});

module.exports = router;
