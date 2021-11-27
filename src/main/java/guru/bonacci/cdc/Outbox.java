package guru.bonacci.cdc;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outbox {

	@Id
	private Long id;
	private String anything;
	
	public static Outbox from(Foo foo) {
		return new Outbox(null, "fibo:" + foo.getFfield());
	}
}