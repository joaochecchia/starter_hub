package com.example.StarterHub.infra.presentation;

import com.example.StarterHub.core.domain.Commit;
import com.example.StarterHub.core.gateway.CommitGateway;
import com.example.StarterHub.infra.Mapper.CommitMapper;
import com.example.StarterHub.infra.requests.create.CreateCommitRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/starter-hub/users/repository/commits")
public class CommitController {

    private final CommitGateway commitGateway;
    private final CommitMapper mapper;

    public CommitController(CommitGateway commitGateway, CommitMapper mapper) {
        this.commitGateway = commitGateway;
        this.mapper = mapper;
    }

    @PostMapping("/postCommit")
    public ResponseEntity<Commit> postCommit(@RequestBody CreateCommitRequest request){
        Optional<Commit> newCommit = commitGateway.postCommit(mapper.toDomain(request));

        return ResponseEntity.ok(newCommit.get());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Commit> searchCommit(@PathVariable UUID id){
        Optional<Commit> findCommit = commitGateway.searchCommit(id);

        return ResponseEntity.ok(findCommit.get());
    }

    @Transactional
    @GetMapping("/findAll/{id}")
    public ResponseEntity<ArrayList<Commit>> findAllCommits(@PathVariable UUID id){
        Optional<ArrayList<Commit>> findAllCommits = commitGateway.findAllCommits(id);

        return ResponseEntity.ok(findAllCommits.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Commit> editCommit(@PathVariable UUID id, @RequestBody Commit commit){
        Optional<Commit> editedCommit = commitGateway.editCommit(id, commit);

        return ResponseEntity.ok(editedCommit.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommit(@PathVariable UUID id){
        String deleteCommit = commitGateway.deleteCommit(id);

        return ResponseEntity.ok(deleteCommit);
    }
}
