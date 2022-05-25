const { decode } = require('../service/authenticator-service')
const middleware = (req, res, next) => {
    var token = req.headers.authorization;
    try {
        if (!token) {
            throw 'JWT token not provided'
        }
        decode(token)
    } catch (error) {
        if (error.name === 'JsonWebTokenError') {
            res.status(401).send('Invalid token')
        }
        res.status(401).send(error)
    }
    next()
}


module.exports = middleware
