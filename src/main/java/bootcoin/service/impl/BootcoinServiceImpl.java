package bootcoin.service.impl;

import bootcoin.entity.Bootcoin;
import bootcoin.repository.BootcoinRepository;
import bootcoin.service.BootcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootcoinServiceImpl implements BootcoinService {

    @Autowired
    BootcoinRepository bootcoinRepository;

    @Override
    public Mono<Bootcoin> create(Bootcoin subType) {
        return bootcoinRepository.save(subType);
    }

    @Override
    public Flux<Bootcoin> findAll() {
        return bootcoinRepository.findAll();
    }

    @Override
    public Mono<Bootcoin> findById(String id) {
        return bootcoinRepository.findById(id);
    }

    @Override
    public Mono<Bootcoin> update(Bootcoin profile) {
        return bootcoinRepository.save(profile);
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return bootcoinRepository.findById(id)
                .flatMap(
                        delete -> bootcoinRepository.delete(delete)
                                .then(Mono.just(Boolean.TRUE))
                )
                .defaultIfEmpty(Boolean.FALSE);
    }
}
