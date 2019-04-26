const handler = require('serve-handler');
const http = require('http');

const server = http.createServer((request, response) => {
    // You pass two more arguments for config and middleware
    // More details here: https://github.com/zeit/serve-handler#options
    console.log(require('url').parse(request.url))
    return handler(request, response);
})

server.listen(8080, () => {
    console.log('Running at http://localhost:8080');
});