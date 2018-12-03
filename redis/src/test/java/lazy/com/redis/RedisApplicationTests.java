package lazy.com.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
        stringRedisTemplate.opsForValue().set("test", "test");

    }

    @Test
    public void redisset() {

        Map<String, Object> map = new HashMap();

        map.put("who hell think you are?", "You are my lover");
        map.put("Are you falled into love?", "yes,It was.");
        redisTemplate.opsForValue().set("hi", map);

    }

    @Test
    public void redisget() {

        Map<String,Object> map=(Map<String, Object>) redisTemplate.opsForValue().get("hi");

        map.keySet().forEach(s-> System.out.println(s));
        map.values().forEach(b-> System.out.println(b));

    }
    @Test
    public void redismap(){
        Map<String, Object> map = new HashMap();

        map.put("1", "Tom");
        map.put("2", "Tony");
//
//        redisTemplate.opsForValue().multiSet(map);
//        System.out.println(String.valueOf(redisTemplate.dump("1")));

//        boolean b=redisTemplate.delete("2");
//        System.out.println(b);

//        long l=redisTemplate.opsForValue().increment("hgllo");
////        System.out.println(l);

        redisTemplate.opsForHash().hasKey("hash",map);
    }

    @Test
    public void listredis(){
        ListOperations<String,List<String>> listOperations = redisTemplate.opsForList();


        List<String> ls=new ArrayList<>();
        ls.add("hi");
        ls.add("what's your name");
        ls.add("I'm Your lover");
        listOperations.leftPush("listkey",ls);
        List<String>lsg= listOperations.rightPop("listkey");
        lsg.forEach(s-> System.out.println(s));


    }
    @Test
    public void setRedis(){

        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("key","value2","value3","value4");
        Set<String> strings=setOperations.members("key");
        strings.forEach(s-> System.out.println(s));


    }

    @Test
    public void hashRedis(){

        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String,Object> map=new HashMap<>();
        map.put("val1",1);
        map.put("val2",2);
        map.put("val3",3);
        hashOperations.putAll("mapkey",map);

        map=hashOperations.entries("mapkey");
        Map<String, Object> finalMap = map;
        map.keySet().forEach(s-> System.out.println(finalMap.get(s)));
        System.out.println("test3");

    }
}
