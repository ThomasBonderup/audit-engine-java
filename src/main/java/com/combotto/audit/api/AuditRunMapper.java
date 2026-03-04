package com.combotto.audit.api;

import com.combotto.audit.model.AuditRun;

public class AuditRunMapper {
  private AuditRunMapper() {
  }

  public static AuditRunResponse toResponse(AuditRun run) {
    return new AuditRunResponse(
        run.id(),
        run.assetId(),
        run.profile(),
        run.status());
  }

}
