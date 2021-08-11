import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private ServerSocket socket;
    private Socket client;
    private boolean incoming = false;
    private boolean outgoing = false;

    public Server() {
        start();
        communicate();
    }

    private void start() {
        try {
            socket = new ServerSocket(1234);

            System.out.println("Socket creating complete...");
            System.out.println("Waiting for a connection...");

            client = socket.accept();

            System.out.println("Client has connected...");
            System.out.println(client);
            System.out.println("Status OK.");
            System.out.println("------------------------------");

            System.out.println("Messages are sending to server...");
        } catch (IOException e) {
            System.out.println("Status OKN'T.");
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            AtomicBoolean incoming = new AtomicBoolean(true);
            AtomicBoolean outgoing = new AtomicBoolean(false);
            AtomicBoolean shut_down = new AtomicBoolean(false);

            Scanner sc = new Scanner(System.in);

            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            Thread in_check = new Thread(() -> {
                try {
                    String inMessage;

                    while (true) {
                        inMessage = in.readUTF();

                        if (inMessage.equals("-server_is_act")) {
                            System.out.println("Messages are sending to client...");
                            outgoing.set(true);
                            incoming.set(false);
                        } else if (inMessage.equals("/exit")) {
                            System.out.println("You've been disconnected");
                            shut_down.set(true);
                            out.writeUTF("-end");
                            break;
                        } else if (incoming.get()) {
                            System.out.println("Server: " + inMessage);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            in_check.start();

            String outMessage;
            while (true) {
                if (!shut_down.get()) {
                    if (outgoing.get()) {
                        outMessage = sc.nextLine();

                        if (outMessage.equals("/to server")) {
                            out.writeUTF("-client_is_act");
                            outgoing.set(false);
                            incoming.set(true);
                        }

                        out.writeUTF(outMessage);
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}