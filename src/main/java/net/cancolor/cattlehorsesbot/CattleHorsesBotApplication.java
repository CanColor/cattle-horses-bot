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
        private final Logger logger = LoggerFactory.getLogger(this.toString());
        private Channel channel;

        @Override
        public void run() {
            imClientInit.connection();
            this.channel = imClientInit.getchannel();
            try {
                WebSocketMessageChannel messageChannel = (WebSocketMessageChannel) MessageChannelFactory.getMessageChannel(ChannelConstant.WEB_SOCKET);
                //私聊
//                messageChannel.builder(channel, botId, 166748580L,575604615L).addAt().addPlainText("1")
//                        .addFace(1).addFace(2).send();

//                messageChannel.builder(channel, botId, 575604615L).addPokeMessage(PokeConstant.BI_XIN).send();
                //戳一戳
//               messageChannel.builder(channel, botId, 166748580L,575604615L).nudge();
//                messageChannel.builder(channel, botId, 575604615L).nudge();
                //禁言

//                messageChannel.builder(channel, botId, 166748580L, 3021611449L).mute(2);
//
//                messageChannel.builder(channel, botId, 166748580L, 3021611449L).unmute();
//
//                messageChannel.builder(channel, botId, 166748580L, 3021611449L).kick("给爷爪巴", false);

//                messageChannel.builder(channel,botId,575604615L).addVipFace(VipFace.LiuLian).send();
//                messageChannel.builder(channel, botId, 575604615L).addFlashImageByImageId("{94403FB8-2A62-3E24-521B-59A5F1A6C034}").send();
//                messageChannel.builder(channel, botId, 575604615L).addFlashImageByUrl("https://ae01.alicdn.com/kf/H3333c75419484757805d349999842ff2m/XIAOMI-MIJIA-Portable-Car-Hand-Helded-Vaccum-Cleaner-for-home-Wireless-Mini-Dust-Catcher-Collector-13000Pa.jpg").send();
                messageChannel.builder(channel, botId, 575604615L).addFlashImageByImageByFilePath("C:\\Users\\Administrator\\Desktop\\024f78f0f736afc3a3090d29a419ebc4b7451270.jpg").send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
