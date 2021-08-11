/**
 * Для отправки сообщений серверу введите команду "/to server" в коносль (без кавычек)
 * Для отправки сообщений клиенту введите команду "/to client" в консоль (без кавычек)
 * Для завершения работы клиента введите команду "/exit" в консоль (без кавычек)
 * когда вы находитесь на стороне клиента (обращаетесь к серверу)
 * и следуюйте дальнейшим инструкциям
 */

public class Main {
    public static void main(String[] args) {
        Thread server = new Thread(() -> new Server());
        Thread clients = new Thread(() -> new Client());

        server.start();
        clients.start();
    }
}