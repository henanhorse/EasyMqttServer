/**
 * Copyright (c) 2018, Mr.Wang (recallcode@aliyun.com) All rights reserved.
 */

package cn.recallcode.iot.mqtt.server.protocol;

import cn.recallcode.iot.mqtt.server.common.message.IDupPubRelMessageStoreService;
import cn.recallcode.iot.mqtt.server.common.message.IMessageIdService;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PUBCOMP连接处理
 */
public class PubComp {

	private static final Logger LOGGER = LoggerFactory.getLogger(PubComp.class);

	private IMessageIdService messageIdService;

	private IDupPubRelMessageStoreService dupPubRelMessageStoreService;

	public PubComp(IMessageIdService messageIdService, IDupPubRelMessageStoreService dupPubRelMessageStoreService) {
		this.messageIdService = messageIdService;
		this.dupPubRelMessageStoreService = dupPubRelMessageStoreService;
	}

	public void processPubComp(Channel channel, MqttMessageIdVariableHeader variableHeader) {
		int messageId = variableHeader.messageId();
		LOGGER.debug("PUBCOMP - clientId: {}, messageId: {}", (String) channel.attr(AttributeKey.valueOf("clientId")).get(), messageId);
		dupPubRelMessageStoreService.remove((String) channel.attr(AttributeKey.valueOf("clientId")).get(), variableHeader.messageId());
		messageIdService.releaseMessageId(messageId);
	}
}
