/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FlywaySchemaHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer       installedRank;
    private final String        version;
    private final String        description;
    private final String        type;
    private final String        script;
    private final Integer       checksum;
    private final String        installedBy;
    private final LocalDateTime installedOn;
    private final Integer       executionTime;
    private final Boolean       success;

    public FlywaySchemaHistory(FlywaySchemaHistory value) {
        this.installedRank = value.installedRank;
        this.version = value.version;
        this.description = value.description;
        this.type = value.type;
        this.script = value.script;
        this.checksum = value.checksum;
        this.installedBy = value.installedBy;
        this.installedOn = value.installedOn;
        this.executionTime = value.executionTime;
        this.success = value.success;
    }

    public FlywaySchemaHistory(
        Integer       installedRank,
        String        version,
        String        description,
        String        type,
        String        script,
        Integer       checksum,
        String        installedBy,
        LocalDateTime installedOn,
        Integer       executionTime,
        Boolean       success
    ) {
        this.installedRank = installedRank;
        this.version = version;
        this.description = description;
        this.type = type;
        this.script = script;
        this.checksum = checksum;
        this.installedBy = installedBy;
        this.installedOn = installedOn;
        this.executionTime = executionTime;
        this.success = success;
    }

    /**
     * Getter for <code>public.flyway_schema_history.installed_rank</code>.
     */
    public Integer getInstalledRank() {
        return this.installedRank;
    }

    /**
     * Getter for <code>public.flyway_schema_history.version</code>.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Getter for <code>public.flyway_schema_history.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for <code>public.flyway_schema_history.type</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter for <code>public.flyway_schema_history.script</code>.
     */
    public String getScript() {
        return this.script;
    }

    /**
     * Getter for <code>public.flyway_schema_history.checksum</code>.
     */
    public Integer getChecksum() {
        return this.checksum;
    }

    /**
     * Getter for <code>public.flyway_schema_history.installed_by</code>.
     */
    public String getInstalledBy() {
        return this.installedBy;
    }

    /**
     * Getter for <code>public.flyway_schema_history.installed_on</code>.
     */
    public LocalDateTime getInstalledOn() {
        return this.installedOn;
    }

    /**
     * Getter for <code>public.flyway_schema_history.execution_time</code>.
     */
    public Integer getExecutionTime() {
        return this.executionTime;
    }

    /**
     * Getter for <code>public.flyway_schema_history.success</code>.
     */
    public Boolean getSuccess() {
        return this.success;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FlywaySchemaHistory (");

        sb.append(installedRank);
        sb.append(", ").append(version);
        sb.append(", ").append(description);
        sb.append(", ").append(type);
        sb.append(", ").append(script);
        sb.append(", ").append(checksum);
        sb.append(", ").append(installedBy);
        sb.append(", ").append(installedOn);
        sb.append(", ").append(executionTime);
        sb.append(", ").append(success);

        sb.append(")");
        return sb.toString();
    }
}
