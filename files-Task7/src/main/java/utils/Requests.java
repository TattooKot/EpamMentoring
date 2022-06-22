package utils;

public enum Requests {
    GET_FILE_BY_ID("select * from files where id = ?"),
    CREATE_NEW_FILE("insert into files(name, path, exp) values (?, ?, ?)"),
    GET_LAST_FILE("SELECT * FROM files ORDER BY ID DESC LIMIT 1");

    private final String request;

    Requests(String request) {
        this.request = request;
    }

    public String toString() {
        return this.request;
    }
}
