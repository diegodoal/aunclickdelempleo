import models.datasource.SingletonDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Victor on 20/03/2015.
 */
public class MongoDBClientTest {

    public static String email = UtilsTest.email;

    @BeforeClass
    public static void initTestData(){
        UtilsTest.buildNewFakeUser();
    }

    @AfterClass
    public static void dropDB(){
        SingletonDataSource singletonDataSource = SingletonDataSource.getInstance();
        singletonDataSource.dropUserCollection();
    }

    @Test
    public void getAllUsersTest(){
        SingletonDataSource singletonDataSource = SingletonDataSource.getInstance();
        singletonDataSource.getAllUsers();
        assertEquals(singletonDataSource.mongoClient.getConnector().isOpen(), false);
    }

    @Test
    public void getUserByEmailTest(){
        SingletonDataSource singletonDataSource = SingletonDataSource.getInstance();
        singletonDataSource.getUserByEmail(email);
        assertFalse(singletonDataSource.mongoClient.getConnector().isOpen());


    }
}
