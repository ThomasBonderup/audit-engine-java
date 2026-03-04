package com.combotto.audit.api;

public record CreateAuditRunRequest(
    String assetId,
    String Profile) {
}