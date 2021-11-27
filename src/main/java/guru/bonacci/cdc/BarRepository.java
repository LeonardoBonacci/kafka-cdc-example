package guru.bonacci.cdc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface BarRepository extends ReactiveCrudRepository<Bar, Integer> {
}
