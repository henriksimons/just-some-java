package assignments.four.endpoint.model;

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
