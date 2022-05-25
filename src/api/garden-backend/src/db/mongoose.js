const mongoose = require('mongoose')
const uri = "mongodb+srv://garden:borboletinha123@cluster0.wx4qn.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

mongoose.connect(uri, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useFindAndModify: false
})
