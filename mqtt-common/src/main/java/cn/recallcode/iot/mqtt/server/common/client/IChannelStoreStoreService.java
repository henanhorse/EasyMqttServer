package cn.recallcode.iot.mqtt.server.common.client;

import org.apache.ignite.cache.query.FieldsQueryCursor;

import javax.cache.Cache;
import java.util.List;

public interface IChannelStoreStoreService {

    /**
     * 保存channelID
     *
     * @param channelId
     * @param channelStore
     */
    void putChannelId(String channelId, ChannelStore channelStore);

    /**
     * 根据ChannelId 获取连接进来的客户端
     */

    ChannelStore getByChannelId(String channelId);

    /**
     * @param channelId
     * @return
     */
    boolean containsChannelId(String channelId);

    /**
     * 删除ChannelId
     */
    void removeChannelId(String channelId);

    /**
     * 获取所有的数目
     */
    int clientCount();

    /**
     * 获取所有
     */
    List<Cache.Entry<String, ChannelStore>> listAll(int page, int size);

    /**
     * 保存数据库
     */
    void save(ChannelStore channelStore);
    /**
     * 删除
     */
    void delete(String channelId);
}
