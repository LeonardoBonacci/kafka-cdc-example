package guru.bonacci.cdc;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Foo {

	@Id
	private Long id;
	private String ffield;
	
	public static Foo from(String foo) {
		return new Foo(null, foo);
	}
}