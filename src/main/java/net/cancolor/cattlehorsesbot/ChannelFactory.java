package net.cancolor.cattlehorsesbot;

import io.netty.channel.Channel;

public class ChannelFactory {

    private static Channel channel = null;

    public static Channel getChannel() {
        return channel;
    }

    public static void setChannel(Channel channel) {
        ChannelFactory.channel = channel;
    }
}
