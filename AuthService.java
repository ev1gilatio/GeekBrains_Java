package Server;

import Database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthService {
    private DatabaseManager databaseManager;
    private static List<Entry> entries;
    private ArrayList<String> nicks;
    private ArrayList<String> passes;
    private ArrayList<String> users;

    public AuthService() {
        databaseManager = new DatabaseManager();
        entries = new ArrayList<>();
        nicks = new ArrayList<>();
        passes = new ArrayList<>();
        users = databaseManager.users();

        String[] credentials;
        for (String user : users) {
            credentials = user.split("\\s");

            nicks.add(credentials[0]);
            passes.add(credentials[1]);
        }

        for (int i = 0; i < users.size(); i++) {
            entries.add(new Entry(nicks.get(i), passes.get(i)));
        }
    }

    public Optional<Entry> findUserByLoginAndPassword(String login, String password) {
        for (AuthService.Entry entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(password)) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    static class Entry {
        String login;
        String password;

        Entry(String login, String password) {
            this.login = login;
            this.password = password;
        }

        String getLogin() {
            return login;
        }

        String getPassword() {
            return password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(login, entry.login) && Objects.equals(password, entry.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(login, password);
        }
    }
}
