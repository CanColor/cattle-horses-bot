package net.cancolor.cattlehorsesbot.plug;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;

import java.util.concurrent.TimeUnit;

//缓存 用来做复读机
public class RepeatCache {
    static Cache<Long, String> cache = Caffeine.newBuilder()
            // 数量上限
            .maximumSize(1024)
            // 过期机制
            .expireAfterAccess(1, TimeUnit.MINUTES)
            // 弱引用key
//                .weakKeys()
            // 弱引用value
//                .weakValues()
            // 剔除监听
            .removalListener((RemovalListener<Long, String>) (k, v, cause) ->
                    System.out.println("key:" + k + ", value:" + v + ", 删除原因:" + cause.toString()))
            .build();

    public static boolean isRepeat(Long key, String value) {
        String result = cache.getIfPresent(key);
        if (result == null) {
            cache.put(key, value);
            return false;
        } else {
            if (!result.equalsIgnoreCase(value)) {
                cache.put(key, value);
                return false;
            }
        }
        return true;
    }


    public static void del(Long key) {
        cache.invalidate(key);
    }

}
