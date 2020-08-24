package com.meetup.api.domain.meetup;

import com.meetup.api.domain.asistants.Assistant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class MeetupEvent {
  private final String uuid = UUID.randomUUID().toString();

  private final Date when;
  private final Place where;
  private final List<Assistant> assistants;
  private final Long totalOfBeers;

  public MeetupEvent(Date when, Place where, List<Assistant> assistants, Long totalOfBeers) {
    this.when = when;
    this.where = where;
    this.assistants = assistants;
    this.totalOfBeers = totalOfBeers;
  }

  public String getUuid() {
    return uuid;
  }

  public Date getWhen() {
    return when;
  }

  public List<Assistant> getAssistants() {
    return Collections.unmodifiableList(this.assistants);
  }

  public Integer countAssistants() {
    return this.assistants.size();
  }

  public Long getTotalOfBeers() {
    return totalOfBeers;
  }

  public void addAssistant(Assistant assistant) {
    this.assistants.add(assistant);
  }

  public Place getWhere() {
    return where;
  }

  public static class Builder {
    private Date when;
    private Place where;
    private List<Assistant> assistants = new ArrayList<>();
    private Long totalOfBeers;

    public Builder when(Date when) {
      this.when = when;
      return this;
    }

    public Builder where(Place place) {
      this.where = place;
      return this;
    }

    public Builder withAssistant(Assistant assistant) {
      this.assistants.add(assistant);
      return this;
    }

    public Builder withBeers(Long totalOfBeers) {
      this.totalOfBeers = totalOfBeers;

      return this;
    }

    public MeetupEvent create() {
      return new MeetupEvent(when, where, assistants, totalOfBeers);
    }
  }
}
