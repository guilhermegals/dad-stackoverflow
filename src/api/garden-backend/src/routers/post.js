const express = require('express')
const router = new express.Router()
const Post = require('../models/post')
const { getUserFromToken, encodeUserId, encodeCommentId } = require('../service/authenticator-service')

router.post('/api/v1/posts', async (req, res) => {
    const post = new Post(req.body)
    const { unique_name, email } = getUserFromToken(req.headers.authorization)
    try {
        post['createdAt'] = new Date()
        post['ownerEmail'] = email
        post['ownerName'] = unique_name
        const postSaved = await post.save(req.body)
        res.status(201).send(postSaved)
    } catch (error) {
        res.status(400).send(error)
    }
})

router.put('/api/v1/posts/:id', async (req, res) => {
    const id = req.params.id
    const { email } = getUserFromToken(req.headers.authorization)
    let postFinded
    try {
        postFinded = await Post.findById(req.params.id)
    } catch (error) {
        return res.status(404).send('post not found')
    }

    if (postFinded.ownerEmail !== email) {
        res.status(403).send('only the poster owner can edit')
        return
    }
    try {
        const query = { '_id': id };
        postFinded = Object.assign(postFinded, req.body)
        await Post.findOneAndUpdate(query, postFinded, { upsert: false })
        res.status(200).send(postFinded)
    } catch (error) {
        res.status(400).send(error)
    }
})

router.post('/api/v1/posts/:id/comments/', async (req, res) => {
    const post = await Post.findById(req.params.id)
    const { unique_name, email } = getUserFromToken(req.headers.authorization)
    if (!post) {
        res.status(404).send('post not found')
    }
    try {
        const { newComment } = req.body
        const commentDto = {
            id: encodeCommentId(email),
            content: newComment,
            owner: encodeUserId(email),
            ownerName: unique_name,
            ownerEmail: email,
            createdAt: new Date()
        }
        post.comments.push(commentDto)
        await upsertPost(post)
        res.status(201).send(req.body)
    } catch (error) {
        res.status(400).send(error)
    }
})

const upsertPost = async (post) => {
    const id = post._id
    delete post._id

    await Post.update({ _id: id }, post, { upsert: true })
}

router.get('/api/v1/posts', async (req, res) => {
    try {
        const posts = await Post.find().sort({ '_id': -1 })
        res.send(posts)
    } catch (error) {
        res.status(500).send()
    }
})

router.get('/api/v1/posts/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const post = await Post.findById(_id)
        if (!post) {
            return res.status(404).send()
        }

        res.send(post)
    } catch (error) {
        res.status(500).send()
    }
})

router.delete('/api/v1/posts/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const post = await Post.findByIdAndDelete(_id)
        if (!post) {
            return res.status(404).send()
        }
        res.send(post)
    } catch (error) {
        res.status(400).send()
    }
})

router.put('/api/v1/posts/:id/comments/like', async (req, res) => {
    const idPoster = req.params.id
    const { id: idComment, action } = req.body
    const { email } = getUserFromToken(req.headers.authorization)

    try {
        const post = await Post.findById(idPoster)
        if (!post) {
            return res.status(204).send('post not found')
        }

        for (const comment of post.comments) {
            if (comment._id == idComment) {
                const userId = encodeUserId(email)
                if (!comment.likes) {
                    comment.likes = [
                        {
                            owner: userId,
                            isLiked: action
                        }
                    ]
                    await upsertPost(post)
                    return res.status(201).send(post)
                }

                for (const comment of post.comments) {
                    if (comment._id == idComment) {
                        const userId = encodeUserId(email)
                        if (!comment.likes) {
                            comment.likes = [{
                                owner: userId,
                                ownerEmail: email,
                                isLiked: action
                            }]
                            await upsertPost(post)
                            return res.status(201).send(post)
                        }

                        const commentIndex = comment.likes.map((like) => like.owner).indexOf(userId)
                        if (commentIndex >= 0) {
                            comment.likes[commentIndex].isLiked = action
                        } else {
                            comment.likes.push({
                                owner: userId,
                                ownerEmail: email,
                                isLiked: action
                            })
                        }

                        await upsertPost(post)
                        return res.status(201).send(post)
                    }
                }

                await upsertPost(post)
                return res.status(201).send(post)
            }
        }
        return res.status(204).send()
    } catch (error) {
        res.status(404).send(error)
    }
})

module.exports = router
