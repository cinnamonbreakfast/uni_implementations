package tcp;

import message.Message;
import message.MessageHeader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

@Component
public class TcpServer {
    private final ExecutorService executorService;
    private final Map<MessageHeader, UnaryOperator<Message>> methodHandlers;

    public TcpServer(ExecutorService executorService) {
        this.methodHandlers = new HashMap<>();
        this.executorService = executorService;
    }

    public void addHandler(MessageHeader methodName, UnaryOperator<Message> handler) {
        methodHandlers.put(methodName, handler);
    }

    public void startServer() throws Exception {
        try (var serverSocket = new ServerSocket(Message.PORT)) {
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(new ClientHandler(client));
            }
        } catch (IOException e) {
            throw new Exception("Error connecting clients", e);
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket client) {
            this.socket = client;
        }

        @Override
        public void run() {
            try (var os = new ObjectOutputStream(socket.getOutputStream());
                 var is = new ObjectInputStream(socket.getInputStream())) {
                Message request = new Message();
                request.readFrom(is);

                // switch & handle possible errors and send them as Message to handled client

                System.out.println("Got the request");
                System.out.println(request.getHeader());

                try
                {
                    Message response = methodHandlers.get(request.getHeader())
                            .apply(request);
                    System.out.println("Request completed");
                    response.writeTo(os);
                } catch (Exception e)
                {
                    ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
                    body.add(new AbstractMap.SimpleEntry<>("message", e.getMessage()));

                    System.out.println("Request failed");
                    System.out.println(e.getMessage());

                    Message response = new Message(MessageHeader.ERROR, body);
                    response.writeTo(os);
                }


            } catch (IOException e) {
                try {
                    throw new Exception("error processing client", e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
