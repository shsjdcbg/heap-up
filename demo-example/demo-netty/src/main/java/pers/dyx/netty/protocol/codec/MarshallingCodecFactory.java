package pers.dyx.netty.protocol.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @author dyx
 */
public class MarshallingCodecFactory {

    /**
     * 创建Jboss Marshaller
     *
     * @return Marshaller
     * @throws IOException IO异常
     */
    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createMarshaller(configuration);
    }

    /**
     * 创建Jboss Unmarshaller
     *
     * @return Unmarshaller
     * @throws IOException IO异常
     */
    protected static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling
                .getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createUnmarshaller(configuration);
    }
}
