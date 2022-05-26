const mongoose = require('mongoose');

app.get('/MongoDB', async (req, res) => {
    await mongoose.connect('mongodb://127.0.0.1:27017', {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        useFindAndModify: true,
        useCreateIndex: true
    });

    const { Schema } = mongoose;
    const goodsSchema = new Schema({
        goodsId: {
            type: Number,
            required: true,
            unique: true
        },
        name: {
            type: String,
            required: true,
            unique: true
        },
        thumbnailUrl: {
            type: String
        },
        category: {
            type: String
        },
        price: {
            type: Number
        }
    });

    let Goods = mongoose.model("Goods", goodsSchema);

    // create(): mongoDB에 데이터 추가
    await Goods.create({
        goodsId: 1,
        name: "맛있는 저녁",
        thumbnailUrl: "https://~~",
        category: "food",
        price: 15000
    });
    res.send('ok');
});