package tcp;

import Message.Message;
import Message.MessageHeader;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class TcpClient
{
    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(Message.HOST, Message.PORT);
             var os = new ObjectOutputStream(socket.getOutputStream());
            var is = new ObjectInputStream(socket.getInputStream());
        ) {
            request.writeTo(os);

            Message response = new Message();
            response.readFrom(is);

            return response;
        } catch (Exception e) {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("message", e.getMessage()));
            System.out.println(e.getMessage());
            return new Message(MessageHeader.ERROR, body);

        }
    }
}


