package guru.bonacci.cdc;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bar {

	@Id
	private Long id;
	private String bfield;
	
	public static Bar from(Foo foo) {
		return new Bar(null, "fibo:" + foo.getFfield());
	}
}