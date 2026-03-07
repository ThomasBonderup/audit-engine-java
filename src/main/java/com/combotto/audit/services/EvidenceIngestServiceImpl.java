package com.combotto.audit.services;

import org.springframework.stereotype.Service;

import com.combotto.audit.model.EvidenceEnvelope;

@Service
public class EvidenceIngestServiceImpl implements EvidenceIngestService {

  private final EvidencePublisher publisher;

  public EvidenceIngestServiceImpl(EvidencePublisher publisher) {
    this.publisher = publisher;
  }

  public void ingest(EvidenceEnvelope envelope) {
    publisher.publish(envelope);
  }
}
