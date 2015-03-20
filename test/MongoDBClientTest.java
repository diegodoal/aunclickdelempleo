import com.mongodb.Mongo;
import models.datasource.DataSource;
import models.datasource.ObjectDataSource;
import models.datasource.UserDataSource;
import org.junit.AfterClass;
import org.junit.Test;
import utils.EmailChecker;

import static org.junit.Assert.assertFalse;

/**
 * Created by Victor on 20/03/2015.
 */
public class MongoDBClientTest {

    public static String email = UtilsTest.email;

    @AfterClass
    public static void initTestData(){
        UtilsTest.buildNewFakeUser();
    }

    @Test
    public void getUserByEmailTest(){
        UserDataSource.getUserByEmail("email");
        assertFalse(DataSource.mongoClient.getConnector().isOpen());
    }

    @Test
    public void getAllUsersTest(){
        UserDataSource.getAllUsers();
        assertFalse(DataSource.mongoClient.getConnector().isOpen());
    }

    @Test
    public void getNextInterviewsTest(){
        EmailChecker emailChecker = new EmailChecker();
        emailChecker.getUsersWithNextInterviews(1);
        assertFalse(DataSource.mongoClient.getConnector().isOpen());
    }

    @Test
    public void multiGetOneObjectTest(){
        for(int i=0; i<1000; i++){
            ObjectDataSource objectDataSource = ObjectDataSource.getInstance();
            objectDataSource.getUserByEmail(email);
        }
    }


    @Test
    public void multiGetAllTest(){
        for(int i=0; i<10000; i++){
            UserDataSource.getAllUsers();
        }
    }
}
