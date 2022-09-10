package assignments.one;

import assignments.four.Person;

import java.util.Objects;

public class Account implements Comparable<Account> {

    private final String id;
    private Person owner; //Assuming every account must be owned by someone.

    public Account(String accountName) {
        this.id = accountName;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
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
}
