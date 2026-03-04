package com.combotto.audit.api;

public record AuditRunResponse(
    long id,
    String assetId,
    String profile,
    String status) {
}
