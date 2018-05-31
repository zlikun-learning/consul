package com.zlikun.learning;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsulTest {

    private Consul consul;

    @Before
    public void init() {
        this.consul = Consul.builder()
                .withHostAndPort(HostAndPort.fromParts("192.168.0.200", 8500))
                .withPing(true)
                .build();
    }

    @Test
    public void keyValue() {

        KeyValueClient client = this.consul.keyValueClient();

        assertTrue(client.putValue("user/name", "zlikun"));
        assertTrue(client.putValue("user/age", "120"));

        assertEquals("zlikun", client.getValueAsString("user/name").get());

        client.deleteKey("zlikun/age");

    }


}
