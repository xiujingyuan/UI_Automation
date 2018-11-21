package web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Created by Merry on 8/29/16.
 */
public class CheckPoint {
    Logger logger = LoggerFactory.getLogger(CheckPoint.class);

    public String caseId;
    public String actual;
    public String expectation;

    public void validate() {
        Assert.assertEquals(actual, expectation);
    }
}
