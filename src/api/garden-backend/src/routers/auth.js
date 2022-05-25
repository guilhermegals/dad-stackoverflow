const express = require('express')
const router = new express.Router()
const axios = require('axios')

router.post('/api/v1/auth', async (req, res) => {
  try {
    const { login, password } = req.body

    const body = {
      'email': login,
      'senha': password
    }
    const response = await axios.post('http://victorgontijo-001-site1.htempurl.com/api/Autenticacao/login', body)

    const token = response.data

    res.status(200).send({ token })
  } catch (error) {
    res.status(400).send(error)
  }
})
module.exports = router
