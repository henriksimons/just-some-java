package assignments.four.endpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountRequestModel {
    private String accountId;
    private String personId;
}
