package rabbitmqdemo

import com.budjb.rabbitmq.publisher.RabbitMessagePublisher


class TestController {

    RabbitMessagePublisher rabbitMessagePublisher

    static allowedMethods = [index: 'GET', show: 'GET']

    def index() {
        (1..10).each { Number index ->
            double randomNumber = Math.random()
            rabbitMessagePublisher.send {
                routingKey = 'scrapeRequestQueue'
                body = [id: randomNumber, title: 'Title is: ' + randomNumber]
            }
        }

//        respond([id: randomNumber])
    }
}
