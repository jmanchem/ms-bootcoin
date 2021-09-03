package bootcoin.service;

import bootcoin.entity.Bootcoin;
;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {
    public Mono<Bootcoin> create(Bootcoin profile);

    public Flux<Bootcoin> findAll();

    public Mono<Bootcoin> findById(String id);

    public Mono<Bootcoin> update(Bootcoin profile);

    public Mono<Boolean> delete(String id);
}
