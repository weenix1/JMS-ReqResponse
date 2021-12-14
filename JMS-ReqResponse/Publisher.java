import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.Scanner;

/**
 * Class Publisher with some changes by Wolfgang Renz, May 2020
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class Publisher extends JMSClient{
    private static final int TIMESTEP=2000; // milliseconds
    private static final int SIZE = 256;

    private MessageProducer producer;
    private int messageID;

    public Publisher(String destination) throws JMSException
    {
        super(destination);
        assert(connected==true);
    }

    public Publisher() throws JMSException
    {   
        this("event");
    }

    @Override
    protected boolean connect() throws JMSException
    {
        if(!connected) connected= super.connect();
        Destination dest = new ActiveMQTopic(destination);
        producer = session.createProducer(dest);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);     
        messageID=0;
        return connected;
    }

    public void sendMessage(String text) throws JMSException
    {
        if(!connected) connect();
        assert(connected== true);
        TextMessage textMessage= session.createTextMessage(text);
        textMessage.setIntProperty("id", ++messageID);
        producer.send(textMessage);
    }

    public void eot() throws JMSException
    {
        sendMessage("EOT");
        System.err.println("EOT sent.");
        close();
    }
}
