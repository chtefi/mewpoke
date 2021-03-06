package com.criteo.nosql.mewpoke.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Configuration class that are use by the app
 * Use fromFile method in order to transform a yaml file into an config's instance
 * <p>
 * TODO (r.gerard): Improve the class, add substructure for consul and services instead of Map<String,String>
 */
public final class Config {
    public static final String DEFAULT_PATH = "config.yml";

    private Map<String, String> app;
    private Service service;
    private Discovery discovery;
    private CouchbaseStats couchbaseStats;

    public static Config fromFile(final String filePath) throws IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final Config cfg = mapper.readValue(new File(filePath), Config.class);
        return cfg;
    }

    public Service getService() {
        return service;
    }

    public Discovery getDiscovery() {
        return discovery;
    }

    public Map<String, String> getApp() {
        return app;
    }

    public CouchbaseStats getCouchbaseStats() {
        return couchbaseStats;
    }

    public class CouchbaseStats {
        private List<String> bucket;
        private List<String> xdcr;

        public List<String> getBucket() {
            return bucket;
        }

        public List<String> getXdcr() {
            return xdcr;
        }
    }

    public static class ConsulDiscovery {
        private String host;
        private Integer port;
        private Integer timeoutInSec;
        private String readConsistency;
        private List<String> tags;

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }

        public Integer getTimeoutInSec() {
            return timeoutInSec;
        }

        public String getReadConsistency() {
            return readConsistency;
        }

        public List<String> getTags() {
            return tags;
        }
    }

    public static class StaticDiscovery {
        private String clustername;
        private String host;

        public String getClustername() {
            return clustername;
        }

        public String getHost() {
            return host;
        }
    }

    public static class Discovery {
        private ConsulDiscovery consul;
        private StaticDiscovery staticDns;
        private Long refreshEveryMin; // FIXME. keep it a while for compat

        public Long getRefreshEveryMin() {
            return refreshEveryMin;
        }

        public ConsulDiscovery getConsul() {
            return consul;
        }

        public StaticDiscovery getStaticDns() {
            return staticDns;
        }
    }

    public static class Service {
        private String type;
        private long timeoutInSec;
        private List<String> tags;
        private String username;
        private String password;

        public String getType() {
            return type;
        }

        public long getTimeoutInSec() {
            return timeoutInSec;
        }

        public List<String> getTags() {
            return tags;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
