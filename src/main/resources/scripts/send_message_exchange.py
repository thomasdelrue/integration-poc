import pika
import sys

connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

exchange_name = 'test.integration.poc'

channel.exchange_declare(exchange=exchange_name, exchange_type='fanout')

message = 'test integration poc'
channel.basic_publish(exchange=exchange_name,
                      routing_key='',
                      body=message)

print(' [x] Sent: %r' % message)

connection.close()
