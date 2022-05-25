const jwt = require('jsonwebtoken');
const getUserFromToken = (token) => {
    try {
        return decode(token)
    } catch (error) {
        return false
    }
}

const encodeUserId = (identifier) => jwt.sign(identifier, process.env.TOKEN_SIGN_PASSWORD || 'verysecr3tp4assword');

const encodeCommentId = (identifier) => {
    const date = new Date()
    return jwt.sign(`${identifier};${date.getTime()}`, process.env.TOKEN_SIGN_PASSWORD || 'verysecr3tp4assword');
}

const decode = (token) => jwt.decode(token);

module.exports = { decode, getUserFromToken, encodeUserId, encodeCommentId }