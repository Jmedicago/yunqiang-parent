package com.vgit.yunqiang.common.utils;

import com.vgit.yunqiang.pojo.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PassUtils {

    // 实例化RandomNumberGenerator对象，用于生成一个随机数
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    // 散列算法名称
    private String algorithmName = "MD5";

    // 散列迭代次数
    private int hashIterations = 2;

    // 默认密码
    private static final String DEFAULT_PASS = "123";

    public RandomNumberGenerator getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    //加密算法
    public void encryptPassword(SysUser user) {
        // 对User对象设置盐：salt；这个盐值是randomNumberGenerator生成的随机数，所以盐值并不需要我们指定
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        if (user.getPassword() != null) {
            // 调用SimpleHash指定散列算法参数：1、算法名称；2、用户输入的密码；3、盐值（随机生成的）；4、迭代次数
            String newPassword = new SimpleHash(
                    algorithmName,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    hashIterations).toHex();

            user.setPassword(newPassword);
        } else {
            // 调用SimpleHash指定散列算法参数：1、算法名称；2、用户输入的密码；3、盐值（随机生成的）；4、迭代次数
            String newPassword = new SimpleHash(
                    algorithmName,
                    DEFAULT_PASS,
                    ByteSource.Util.bytes(user.getSalt()),
                    hashIterations).toHex();

            user.setPassword(newPassword);
        }
    }

}
