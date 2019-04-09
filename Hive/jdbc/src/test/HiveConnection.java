package org.apache.hive.jdbc;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.token.Token;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class TestHiveConnection {

  private static final String EXISTING_TOKEN = "ExistingToken";
  public static final String EXPECTED_TOKEN_STRING_FORM = "AAAAAA";
  private static HiveConnection.DelegationTokenFetcher fetcher;

  @BeforeClass
  public static void init() {
    fetcher = new HiveConnection.DelegationTokenFetcher();
  }

  @Test
  public void testIfNPEThrownWhileGettingDelegationToken() throws IOException {
    try {
      String tokenStr = fetcher.getTokenFromCredential(new Credentials(), "hive");
      Assert.assertEquals("Token with id: hive shall not be found.", null, tokenStr);
    } catch (NullPointerException e) {
      Assert.fail("This NPE is not handled in the code elsewhere so user is not notified about it!");
      e.printStackTrace();
    }
  }

  @Test
  public void testIfGettingDelegationTokenFromCredentialWorks() throws IOException {
    Credentials creds = new Credentials();
    creds.addToken(new Text(EXISTING_TOKEN), new Token<>());

    String tokenStr = fetcher.getTokenFromCredential(creds, EXISTING_TOKEN);
    Assert.assertEquals("Token string form is not as expected.", EXPECTED_TOKEN_STRING_FORM, tokenStr);
  }
}
