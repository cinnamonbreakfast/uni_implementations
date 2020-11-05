package Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {
    public static final int PORT = 1234;
    public static final String HOST = "localhost";

    private MessageHeader header;
    private List<Map.Entry<String, Object>> body;

    public Message() {
    }

    public Message(MessageHeader header, List<Map.Entry<String, Object>> body) {
        this.header = header;
        this.body = body;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public List<Map.Entry<String, Object>> getBody() {
        return body;
    }

    public void setBody(List<Map.Entry<String, Object>> body) {
        this.body = body;
    }

    public void writeTo(ObjectOutputStream os) throws Exception {
        try
        {
            os.writeObject(this);
        }
        catch (IOException e)
        {
            throw new Exception("Failed to write object to the stream.");
        }
    }

    public void readFrom(ObjectInputStream is) throws Exception
    {
        try
        {
            Message message = (Message) is.readObject();

            this.setHeader(message.getHeader());
            this.setBody(message.getBody());
        } catch (ClassNotFoundException e) {
            throw new Exception("Failed to write message from socket.");
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
