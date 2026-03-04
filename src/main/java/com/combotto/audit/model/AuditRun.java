package com.combotto.audit.model;

public record AuditRun(
    long id,
    String assetId,
    String profile,
    String status) {
}