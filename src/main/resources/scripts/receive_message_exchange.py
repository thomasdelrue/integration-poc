import pika
import sys

connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

exchange_name = 'test.integration.poc'

channel.exchange_declare(exchange=exchange_name, exchange_type='fanout')
result = channel.queue_declare(exclusive=True)
queue_name = result.method.queue
channel.queue_bind(exchange=exchange_name, queue=queue_name)

print(' [*] Waiting for messages. To exit press CTRL-C')


def callback(ch, method, properties, body):
    print(' [x] Received: %r' % body)


channel.basic_consume(callback, queue=queue_name, no_ack=True)
channel.start_consuming()
