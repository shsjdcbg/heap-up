package pers.dyx.netty.protocol.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 * @author dyx
 */
public class ChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf buffer;

    ChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void write(int b) throws IOException {
        this.buffer.writeByte(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        this.buffer.writeBytes(bytes);
    }

    @Override
    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        this.buffer.writeBytes(bytes, srcIndex, length);
    }

    ByteBuf getBuffer() {
        return this.buffer;
    }
}
