package guru.bonacci.cdc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface FooRepository extends ReactiveCrudRepository<Foo, Integer> {
}
