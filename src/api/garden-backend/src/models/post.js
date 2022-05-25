const mongoose = require('mongoose')

const Post = mongoose.model('Post', {
    title: String,
    content: String,
    isActive: Boolean,
    createdAt: Date,
    updatedAt: Date,
    subject: String,
    topic: [],
    owner: String, // user id
    ownerName: String,
    ownerEmail: String,
    comments: [{
        owner: String,
        ownerName: String,
        ownerEmail: String,
        content: String,
        createdAt: Date,
        likes: [{
            owner: String,
            ownerEmail: String,
            isLiked: Boolean
        }]
    }],
})

module.exports = Post