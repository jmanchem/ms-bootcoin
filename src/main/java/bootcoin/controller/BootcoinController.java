package bootcoin.controller;

import bootcoin.entity.Bootcoin;
import bootcoin.service.BootcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RefreshScope
@RestController
@RequestMapping("/bootcoin")
public class BootcoinController {

    @Autowired
    BootcoinService bootcoinService;

    @GetMapping("/list")
    public Flux<Bootcoin> list(){
        return bootcoinService.findAll();
    }

    @GetMapping("/find/{id}")
    public Mono<Bootcoin> findById(@PathVariable String id){
        return bootcoinService.findById(id);
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Bootcoin>> create(@RequestBody Bootcoin subType){
        return bootcoinService.create(subType)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer , HttpStatus.CREATED))
                .onErrorResume(t->Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Bootcoin>> update(@Valid @RequestBody Bootcoin subType) {
        return bootcoinService.update(subType)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable String id) {
        return bootcoinService.delete(id)
                .filter(st -> st)
                .map(deleteCustomer -> new ResponseEntity<>("Customer Deleted", HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
