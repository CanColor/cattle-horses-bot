package net.cancolor.cattlehorsesbot;

import io.netty.channel.Channel;
import net.cancolor.cattlehorsesbot.plug.RepeatCache;
import net.cancolor.easybotapi.channel.WebSocketMessageChannel;
import net.cancolor.easybotapi.constant.ChannelConstant;
import net.cancolor.easybotapi.factory.MessageChannelFactory;
import net.cancolor.easybotapi.model.message.Message;
import net.cancolor.easybotapi.model.message.dto.SendClientMessageDTO;
import net.cancolor.easybotapi.model.message.dto.SendServerMessageDTO;
import net.cancolor.easybotapi.plug.Plug;
import net.cancolor.easybotapi.plug.PlugInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author SoarDao
 * @title: TestPlugImpl
 * @projectName canColor
 * @description: TODO
 * @date 2021/12/15 21:05
 */
@Component
public class TestPlugImpl implements PlugInterface {

    private final Plug plug;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public TestPlugImpl() {
        plug = new Plug().setClzName(this.getClass().getName()).setName("复读机")
                .setStatus(1).setConditionType(3).setCondition(null).setEventList(null);
    }

    @Override
    public Plug init() {
        return plug;
    }

    @Override
    public SendServerMessageDTO run(SendClientMessageDTO sendClientMessageDTO) {
        Long botId = sendClientMessageDTO.getBot().getId();
        Long groupId = sendClientMessageDTO.getGroup().getId();
        Long friendId = sendClientMessageDTO.getFriend().getId();
        Channel channel = ChannelFactory.getChannel();
        WebSocketMessageChannel messageChannel = null;
        try {
            messageChannel = (WebSocketMessageChannel) MessageChannelFactory.getMessageChannel(ChannelConstant.WEB_SOCKET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("触发：{},数据", this.getClass().getName(), sendClientMessageDTO.toString());
        String message = getMessage(sendClientMessageDTO);
        //群聊
        if (groupId != null) {
            if (RepeatCache.isRepeat(sendClientMessageDTO.getGroup().getId(), message)) {
                messageChannel.builder(channel, botId, groupId, null).addPlainText(message).send();
                RepeatCache.del(groupId);
            }
        } else {
            if (RepeatCache.isRepeat(sendClientMessageDTO.getFriend().getId(), message)) {
                messageChannel.builder(channel, botId, null, friendId).addPlainText(message).send();
                RepeatCache.del(friendId);
            }

        }
        return null;
    }


    public String getMessage(SendClientMessageDTO sendClientMessageDTO) {
        List<Message> messageList = sendClientMessageDTO.getMessageList();
        String message = messageList.get(0).getMessage();
        return message;
    }
}
