package net.cancolor.cattlehorsesbot;

import io.netty.channel.Channel;
import net.cancolor.easymiraiapi.channel.WebSocketMessageChannel;
import net.cancolor.easymiraiapi.constent.ChannelConstant;
import net.cancolor.easymiraiapi.factory.MessageChannelFactory;
import net.cancolor.easymiraiapi.init.ImClientInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = "net.cancolor")
public class CattleHorsesBotApplication implements CommandLineRunner {
    org.slf4j.Logger logger = LoggerFactory.getLogger(this.toString());
    @Resource
    ImClientInit imClientInit;

    static Long botId = 2052737713L;

    public static void main(String[] args) {
        SpringApplication.run(CattleHorsesBotApplication.class, args);
    }


    @Override
    public void run(String... args) {
        Thread webSocketThread = new Thread(new clientThread());
        webSocketThread.setName("websocket");
        webSocketThread.start();
    }

    public class clientThread implements Runnable {
        private Logger logger = LoggerFactory.getLogger(this.toString());
        private Channel channel;

        @Override
        public void run() {
            imClientInit.connection();
            this.channel = imClientInit.getchannel();
            try {
                WebSocketMessageChannel messageChannel = (WebSocketMessageChannel) MessageChannelFactory.getMessageChannel(ChannelConstant.WEB_SOCKET);
                //私聊
                messageChannel.builder(channel, botId, 166748580L,575604615L).addAt().send();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}