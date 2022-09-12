package assignments.one;

import assignments.four.Person;

import java.util.Objects;

public class Account implements Comparable<Account> {

    private final String id;
    private final Person owner; //Assuming every account must be owned by someone. Not required by constructor due to earlier assignments.

    protected Account(Builder builder) {
        Objects.requireNonNull(builder);
        if (builder.id == null) {
            throw new IllegalArgumentException("Parameter id can not be empty when creating a new account");
        } else {
            this.id = builder.id;
            this.owner = builder.owner;
        }
    }

    public Person getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId());
    }


    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Account o) {
        return o.getId().compareTo(this.getId());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public String id;
        public Person owner;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder owner(Person owner) {
            this.owner = owner;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
