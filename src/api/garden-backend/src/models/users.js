const mongoose = require('mongoose')

const User = mongoose.model('User', {
    name: String,
    enrolment: String,
    password: String,
})

module.exports = User