import models.datasource.UserDataSource;
import models.entities.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Constants;

import static org.junit.Assert.assertEquals;

/**
 * Created by Victor on 13/03/2015.
 */
public class UpdateUserFieldTest {

    private static User user;
    private static String email = UtilsTest.email;

    @BeforeClass
    public static void setUpBeforeClass(){
        user = UtilsTest.buildNewFakeUser();
        UserDataSource.insertIntoUsersCollection(user);
    }

    @AfterClass
    public static void tearDownAfterClass(){
        UserDataSource.dropUserCollection();
    }

    @Test
    public void checkUpdate_personalInformation(){
        String fakeName = "fakeName";
        String fakeSurnames = "fakeSurnames";
        String fakeEmail = "fakeEmail";
        String fakePassword = "fakePassword";

        UserDataSource.updateUserData(email, Constants.USER_NAME, fakeName);
        UserDataSource.updateUserData(email, Constants.USER_SURNAMES, fakeSurnames);
        UserDataSource.updateUserData(email, Constants.USER_PASSWORD, fakePassword);
        UserDataSource.updateUserData(email, Constants.USER_EMAIL, fakeEmail);

        email = fakeEmail;

        assertEquals(fakeName, UserDataSource.getUserByEmail(email).name);
        assertEquals(fakeSurnames, UserDataSource.getUserByEmail(email).surnames);
        assertEquals(fakeEmail, UserDataSource.getUserByEmail(email).email);
        assertEquals(fakePassword, UserDataSource.getUserByEmail(email).password);
    }
}
