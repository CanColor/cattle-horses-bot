package net.cancolor.cattlehorsesbot;

import io.netty.channel.Channel;
import net.cancolor.easymiraiapi.init.ImClientInit;
import net.cancolor.easymiraiapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easymiraiapi.utils.SendServerMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages="net.cancolor")
public class CattleHorsesBotApplication implements CommandLineRunner {
    org.slf4j.Logger logger = LoggerFactory.getLogger(this.toString());
    @Resource
    ImClientInit imClientInit;
    Channel channel ;

    public static void main(String[] args) {
        SpringApplication.run(CattleHorsesBotApplication.class, args);
    }





    @Override
    public void run(String... args) {
        Thread webSocketThread=new Thread(new clientThread());
        webSocketThread.setName("websocket");
        webSocketThread.start();
    }

    public class clientThread implements Runnable {
        Logger logger = LoggerFactory.getLogger(this.toString());

        @Override
        public void run() {
            imClientInit.connection();
            login();
        }


        public void login(){
            channel=imClientInit.getchannel();
            SendServerMessageDTO sendServerMessageDTO =new SendServerMessageDTO();
            sendServerMessageDTO.setComond("login");
            sendServerMessageDTO.setClientId(1);
            sendServerMessageDTO.setClientName("sb");

        }

    }
}
