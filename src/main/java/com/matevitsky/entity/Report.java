package com.matevitsky.entity;

import java.util.Objects;

public class Report {

    private final int id;
    private final String tittle;
    private final String content;
    private final ReportStatus status;
    private final String cancellationReason;
    private final int clientId;


    public Report(Builder builder) {
        this.id = builder.id;
        this.tittle = builder.tittle;
        this.content = builder.content;
        this.status = builder.status;
        this.cancellationReason = builder.cancellationReason;
        this.clientId = builder.clientId;

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getContent() {
        return content;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public int getClientId() {
        return clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        Report report = (Report) o;
        return id == report.id &&
            clientId == report.clientId &&

            tittle.equals(report.tittle) &&
            content.equals(report.content) &&
            status == report.status &&
            cancellationReason.equals(report.cancellationReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle, content, status, cancellationReason, clientId);
    }

    public static class Builder {
        private int id;
        private String tittle;
        private String content;
        private ReportStatus status;
        private String cancellationReason;
        private int clientId;


        private Builder() {
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTittle(String tittle) {
            this.tittle = tittle;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withStatus(ReportStatus status) {
            this.status = status;
            return this;
        }

        public Builder withCancellationReason(String cancellationReason) {
            this.cancellationReason = cancellationReason;
            return this;
        }

        public Builder withClientId(int clientId) {
            this.clientId = clientId;
            return this;
        }

        public Report build() {
            return new Report(this);
        }


    }


}
