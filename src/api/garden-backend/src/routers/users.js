const express = require('express')
const router = new express.Router()
const User = require('../models/users')

router.post('/api/v1/users', async (req, res) => {
    const user = new User(req.body)
    try {
        await user.save(req.body)
        res.status(201).send(user)
    } catch (error) {
        res.status(400).send(error)
    }
})
router.get('/api/v1/users', async (req, res) => {
    try {
        const users = await User.find({})
        res.send(users)
    } catch (error) {
        res.status(500).send()
    }
})

router.get('/api/v1/users/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const user = await User.findById(_id)
        if (!user) {
            return res.status(404).send()
        }

        res.send(user)
    } catch (error) {
        res.status(500).send()
    }
})

router.delete('/api/v1/users/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const user = await User.findByIdAndDelete(_id)
        if (!user) {
            return res.status(404).send()
        }
        res.send(user)
    } catch (error) {
        res.status(400).send()
    }
})

router.put('/api/v1/users/:id', async (req, res) => {
    const _id = req.params.id

    try {
        const user = await User.findByIdAndUpdate(_id, req.body)
        if (!user) {
            return res.status(404).send()
        }
        res.send(user)
    }
    catch (error) {
        res.status(404).send()
    }
})

module.exports = router