package guru.bonacci.cdc;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class RtxtApplication {

	public static void main(String[] args) {
		SpringApplication.run(RtxtApplication.class, args);
	}
	
	@Bean
	CommandLineRunner demo(OurService service) {
		return args -> {
			Flux<Integer> foof  = Flux.generate(
			            () -> Tuples.of(0, 1),
			            (state, sink) -> {
			                sink.next(state.getT1());
			                return Tuples.of(state.getT2(), state.getT1() + state.getT2());
			            }
			    );
			
			foof.delayElements(Duration.ofSeconds(1)).map(String::valueOf)
				.subscribe(foo -> service.save(foo).map(String::valueOf).subscribe(log::info));
		};
	}
	
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory cf) {
		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(cf);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ByteArrayResource((
				"DROP TABLE IF EXISTS foo;"
			  + "CREATE TABLE foo (id serial, ffield character varying(255) NOT NULL);"
			  +	"DROP TABLE IF EXISTS bar;"
			  + "CREATE TABLE bar (id serial, bfield character varying(255) NOT NULL);")
			.getBytes())));

		return initializer;
	}
}

@Service
@RequiredArgsConstructor
class OurService {
	
	private final FooRepository fooRepo;  
	private final BarRepository barRepo;  

	@Transactional
	public Mono<Bar> save(String foo) {
		return Mono.just(foo)
				.map(Foo::from).flatMap(fooRepo::save)
				.map(Bar::from).flatMap(barRepo::save);
	}

}


