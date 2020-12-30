package com.ase.ase.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Service
public final class MessagingService {

  @Value("${activemq.host}")
  private String Host;

  private MessageProducer           producer;
  private ActiveMQConnectionFactory connectionFactory;
  private Connection                connection;
  private Session                   session;
  private Topic                     destination;

  public void send(Serializable content) {
    try {
      if (producer == null) {
        init("new-data");
      }
      TextMessage message = session.createTextMessage(content.toString());
      producer.send(message);
    } catch (Exception e) {
      System.out.println("Caught: " + e);
      e.printStackTrace();
    }
  }

  public void init(String queueName) {
    try {
      connectionFactory = new ActiveMQConnectionFactory("tcp://" + Host + ":61616");
      connectionFactory.setTrustAllPackages(true);
      connection = createConnection(connectionFactory);
      if (connection != null) {
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic(queueName);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
      } else {
        System.out.println("Connection could not be established");
      }
    } catch (Exception exception) {
      System.out.println(
          "Error while creating connection to activemq broker. Message: " + exception.getMessage());
      exception.printStackTrace();
    }
  }

  private static Connection createConnection(ActiveMQConnectionFactory connectionFactory) {
    int retry = 0;
    boolean connected = false;
    Connection connection = null;
    while (!connected && retry < 3) {
      try {
        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();
        connected = true;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(
            "Connection could not be established, try nr. " + retry + " " + new Date());
        try {
          Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
        }
      }
      retry++;
    }
    return connection;
  }

  public void close() throws JMSException {
    // Clean up
    session.close();
    connection.close();
  }
}
