package guru.bonacci.cdc;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface OutboxRepository extends ReactiveCrudRepository<Outbox, Integer> {
}
