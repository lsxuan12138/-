import com.hitfriend.dc.mapper.UserMapper;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.util.D;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testSelect(){
        SqlSession sqlSession = D.getConn();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserByOpenid("2333333333");
        System.out.println(user);
    }

    public static void main(String[] args) {
        String openid ="{\"session_key\":\"2GdG+t6ad78XpvV14NvRgQ==\",\"openid\":\"o__Mb45mcemfCRo1jGqq1-czzNDs\"}";
        openid = openid.split("\"")[7];
        System.out.println(openid);
    }
}
