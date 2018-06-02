package rabbitmqdemo

import com.budjb.rabbitmq.consumer.MessageContext
import com.budjb.rabbitmq.publisher.RabbitMessagePublisher
import groovy.util.logging.Slf4j

@Slf4j
class TestConsumerConsumer {

    RabbitMessagePublisher rabbitMessagePublisher

    static counter = 0

    static rabbitConfig = [
            queue: 'scrapeRequestQueue'
    ]

    /**
     * Handle an incoming RabbitMQ message.
     *
     * @param body The converted body of the incoming message.
     * @param context Properties of the incoming message.
     * @return
     */
    def handleMessage(def body, MessageContext messageContext) {
        log.info 'Handling Message. . .'

        log.debug '{}', body.toString()

        counter++
        if (counter % 5 == 0) {
            rabbitMessagePublisher.send {
                routingKey = 'failedScrapeSource'
                body = body
            }

            l   og.debug 'ScrapeSource Failed!!! So published to failedQueue'
        }

        log.info 'Message has been handled. . .'
    }
}
