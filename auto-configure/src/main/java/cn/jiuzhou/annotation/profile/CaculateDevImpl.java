package cn.jiuzhou.annotation.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * dev 环境使用的实现类
 * @author wangjiuzhou (835540436@qq.com)
 * @date 2019/05/24
 */
@Profile(value = "dev")
@Service
public class CaculateDevImpl implements Caculate {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("dev 环境");
        Integer reduce = Stream.of(values).reduce(0, Integer::sum);
        return reduce;
    }
}
