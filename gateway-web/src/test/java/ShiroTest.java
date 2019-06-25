import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class ShiroTest {

    //shiro提供了现成的加密类  Md5Hash
    @Test
    public void testMd5(){
        //MD5加密
        String password = new Md5Hash("1111").toString();
        System.out.println("加密后："+password);
        //加盐  salt  默认一次散列
        String password_salt=new Md5Hash("1111", "siggy").toString();
        System.out.println("加盐后："+password_salt);
        //散列2次
        String password_salt_2 = new Md5Hash("1111", "siggy", 2).toString();
        System.out.println("散列2次："+password_salt_2);
        //使用SimpleHash
        SimpleHash hash = new SimpleHash("MD5", "admin", "admin",10);
        System.out.println("simpleHash:"+hash.toString());
    }
}
