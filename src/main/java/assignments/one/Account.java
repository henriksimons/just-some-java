package assignments.one;

import java.util.Objects;

public class Account implements Comparable<Account> {

    private final String accountName;

    public Account(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getAccountName(), account.getAccountName());
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountName());
    }

    @Override
    public int compareTo(Account o) {
        return o.getAccountName().compareTo(this.getAccountName());
    }
}
