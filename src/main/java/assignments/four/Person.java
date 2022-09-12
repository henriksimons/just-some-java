package assignments.four;

import java.util.Objects;

public class Person {

    private final String id;

    private Person(Builder builder) {
        Objects.requireNonNull(builder);
        if (builder.id == null) {
            throw new IllegalArgumentException("Parameter Id can not be null when creating a new person.");
        }
        this.id = builder.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId());
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public String id;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
