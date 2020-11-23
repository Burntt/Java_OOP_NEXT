package space.harbour.hw11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NioTestClient {
    public static void main(String[] args) throws IOException {

        Runnable client = new Runnable() {
            @Override
            public void run() {
                try {
                    new NioTestClient().startClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(client, "client-A").start();
        //new Thread(client, "client-B").start();
    }

    public void startClient() throws IOException, InterruptedException {

        String hostName = "localhost";
        int portNumber = 4444;

        try {
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            // New way
            boolean loop = true;
            while (loop) {
                BufferedReader stdIn =
                        new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please set username:");
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput); // write to server
                    System.out.println("echo: " + in.readLine());
                    if ("Exit".equals(userInput)) {
                        loop = false;
                        break;
                    }
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName + ".." + e.toString());
            System.exit(1);
        }
    }
}

