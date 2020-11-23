package space.harbour.hw11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BlockedChatServer {
    private Set<BlockedChatHandler> clients = ConcurrentHashMap.newKeySet();

    public BlockedChatServer(int portNumber) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Waiting on port : " + portNumber + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BlockedChatHandler client = new BlockedChatHandler(this, clientSocket);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("Server failed on portNumber " + portNumber);
        }
    }

    public synchronized void broadcast(String message) {
        System.out.println("New message -> " + message);
        for (BlockedChatHandler chat: clients) {
            chat.sendMessage(message);
        }
    }

    public void clientDisconnected(BlockedChatHandler chat) {
        clients.remove(chat);
        broadcast("Chat member " + chat.getName() + " left");
    }

    public static void main(String[] args) {
        new BlockedChatServer(4444);
    }
}
