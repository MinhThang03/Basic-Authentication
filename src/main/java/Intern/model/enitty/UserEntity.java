package Intern.model.enitty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;

@RestResource(exported=false)
@Accessors(chain = true)
@NoArgsConstructor
@Document(collection = "user")
@Data
public class UserEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Indexed
    private String username;
    @JsonIgnore
    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
