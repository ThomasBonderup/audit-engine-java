package com.combotto.audit.services;

import com.combotto.audit.model.EvidenceEnvelope;

public interface EvidencePublisher {
  void publish(EvidenceEnvelope envelope);
}
