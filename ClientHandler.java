package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class ClientHandler {
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String login;
    private File historyFile;

    public ClientHandler(Server server, Socket socket) {
        historyFile = new File("history.txt"); //добавленный код

        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    doAuthentication();
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection(socket);
                }
            })
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket) {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User %s is out.", login));

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }

    private void doAuthentication() throws IOException {
        sendMessage("Greeting you in the Evigram!");
        sendMessage("Please do auth: /auth login password");

        while (true) {
            String maybeCredentials = in.readUTF();
            if (maybeCredentials.startsWith("/auth")) {
                String[] credentials = maybeCredentials.split("\\s");

                Optional<AuthService.Entry> maybeUser = server.getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);

                if (maybeUser.isPresent()) {
                    AuthService.Entry user = maybeUser.get();
                    if (server.isNotUserOccupied(user.getLogin())) {
                        login = user.getLogin();
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome!");
/**----------------------------------------------------------------------------------------------------------*/
//добавленный код
                        try {
                            FileReader fr = new FileReader(historyFile);

                            int c;
                            String text = "";
                            while ((c = fr.read()) != -1) {
                                text += Character.toString((char) c);
                            }
                            fr.close();

                            if (text.equals("")) {
                                sendMessage("Start your conversation!");
                            } else {
                                String[] words = text.split("\\n");
                                ArrayList<String> textArrList = new ArrayList<>(Arrays.asList(words));

                                int SIZE = 100;
                                int needToDelete = textArrList.size() - SIZE;
                                for (int i = 0; i < needToDelete; i++) {
                                    textArrList.remove(0);
                                }

                                for (int i = 0; i < textArrList.size(); i++) {
                                    sendMessage(textArrList.get(i));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
/**----------------------------------------------------------------------------------------------------------*/
                        server.broadcastMessage(String.format("User %s entered chat.", login));
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Current user is already logged in");
                    }
                } else {
                    sendMessage("Invalid credentials.");
                }
            } else {
                sendMessage("Invalid auth operation");
            }
        }
    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("/exit")) {
                break;
            }

            try {
                FileWriter fw = new FileWriter(historyFile, true);

                fw.write(inboundMessage + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            server.broadcastMessage(inboundMessage);
        }
    }

}
