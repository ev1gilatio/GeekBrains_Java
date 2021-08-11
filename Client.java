import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    private Socket socket;

    public Client() {
        start();
        communicate();

        System.out.println("Client closing complete...");
        System.out.println("Status OK.");
    }

    private void start() {
        try {
            socket = new Socket("localhost", 1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void communicate() {
        try {
            AtomicBoolean outgoing = new AtomicBoolean(true);
            AtomicBoolean incoming = new AtomicBoolean(false);
            AtomicBoolean disconnected = new AtomicBoolean(false);

            Scanner sc = new Scanner(System.in);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Thread in_check = new Thread(() -> {
                try {
                    String inMessage;

                    while (true) {
                        inMessage = in.readUTF();

                        if (inMessage.equals("-end")) {
                            System.out.println("Press ENTER to close client");
                            disconnected.set(true);
                            break;
                        } else if (inMessage.equals("-client_is_act")) {
                            System.out.println("Messages are sending to server...");
                            outgoing.set(true);
                            incoming.set(false);
                        } else if (incoming.get()) {
                            System.out.println("Client: " + inMessage);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            in_check.start();

            String outMessage;
            while (true) {
                if (!disconnected.get()) {
                    if (outgoing.get()) {
                        outMessage = sc.nextLine();

                        if (outMessage.equals("/to client")) {
                            out.writeUTF("-server_is_act");
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