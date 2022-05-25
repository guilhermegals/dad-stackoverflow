const express = require('express')
require('./db/mongoose')

const postRouter = require('./routers/post')
const userRouter = require('./routers/users')
const authRouter = require('./routers/auth')
const swaggerUI = require('swagger-ui-express')
const middleware = require('./configuration/auth-interceptor')
const cors = require('cors')

const swaggerDocument = require('./doc/swagger.json')

const app = express()

const port = process.env.PORT || 3001

app.use(cors())
app.use(express.json())
app.use(authRouter)
app.use('/doc', swaggerUI.serve, swaggerUI.setup(swaggerDocument))

app.use(middleware)
app.use(postRouter)
app.use(userRouter)

app.listen(port, () => {
  console.log(`Server is up on port ${port}`)
})
