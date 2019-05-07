import com.module.common.util.SnowflakeIdWorker;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wongdong
 * @date 2019/5/6
 */
public class SnowflakeTest {
    /**
    * 测试twitter 的snowflake算法ID生成重复验证
    */
    @Test
    public void test1() {
        Map<Long,Long> map=new HashMap<>();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000000; i++) {
            long id = idWorker.nextId();
            map.put(id,id);
        }
        System.out.println(map.size());
    }

    @Test
    public void test2(){

    }
}
