package com.combotto.audit.services;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.combotto.audit.api.CreateAuditRunRequest;
import com.combotto.audit.model.AuditRun;
import com.combotto.audit.repositories.AuditRunRepository;
import com.combotto.audit.errors.NotFoundException;

@Service
public class AuditRunService {

  private final AuditRunRepository repo;
  private final AtomicLong ids = new AtomicLong(0);

  public AuditRunService(AuditRunRepository repo) {
    this.repo = repo;
  }

  public AuditRun create(CreateAuditRunRequest req) {
    long id = ids.incrementAndGet();
    AuditRun run = new AuditRun(id, req.assetId(), req.Profile(), "QUEUED");
    return repo.save(run);
  }

  public AuditRun getById(long id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("AuditRun" + id + "not found"));
  }

  public List<AuditRun> getByAssetId(String assetId) {
    return repo.findByAssetId(assetId);
  }
}