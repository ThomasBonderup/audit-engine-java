package com.combotto.audit.repositories;

import com.combotto.audit.model.AuditRun;

import java.util.Optional;
import java.util.List;

public interface AuditRunRepository {
  AuditRun save(AuditRun run);

  Optional<AuditRun> findById(long id);

  List<AuditRun> findByAssetId(String assetId);
}
