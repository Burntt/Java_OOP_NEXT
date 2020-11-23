package space.harbour.hw11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NioChatHandler extends Thread {
    String name;
    Socket clientSocket;
    NioChatServer server;
    PrintWriter out;
    BufferedReader in;

    public NioChatHandler(NioChatServer server, Socket clientSocket) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            this.name = in.readLine();
            server.broadcast("New chat member " + name + " from " + clientSocket.getInetAddress());
            //Process client request and send back response
            String request, response;
            while ((request = in.readLine()) != null) {
                response = processRequest(request);
                out.println(response);
                if ("Exit".equals(request)) {
                    break;
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String processRequest(String request) {
        System.out.println("Server receive message from " + name + " > " + request);
        return request;
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }
}