import com.tensquare.article.ArticleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticleApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test1(){
        redisTemplate.opsForValue().set("tensquare_test","hello world");

    }

    @Test
    public void test2(){
        String ss = redisTemplate.opsForValue().get("tensquare_test");
        System.out.println(ss);
    }
}
