package morning.cat.netty.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/9 上午2:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private String userId;

    private String userName;
}
