package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {

    private String name;
    private String roll_no;
    private String email;
    private String phone_no;

}
