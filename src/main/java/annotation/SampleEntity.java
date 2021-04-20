package annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SampleEntity {
    private String id;
    @Label("birthday")
    @DatePattern("yyyy-MM-dd HH:mm:ss")
    private Date date;
}
