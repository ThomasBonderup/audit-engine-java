package com.combotto.audit.services;

import com.combotto.audit.model.EvidenceEnvelope;

public interface EvidenceIngestService {
  void ingest(EvidenceEnvelope envelope);
}
