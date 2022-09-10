package assignments.four.endpoint;

public enum HttpMethod {

    GET("GET"), POST("POST");

    private final String name;

    HttpMethod(String text) {
        this.name = text;
    }

    public String getName() {
        return name;
    }
}
