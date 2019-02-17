

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.ufabc.amem.util.ConnectionPool;

import org.junit.Assert;

class ConnectionPoolTest {

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	void testGetConnection() {
		
		Connection conn = ConnectionPool.getInstance().getConnection();
		
		if(conn != null) {
			
		}
	}

	@Test
	void testReleaseConnection() {
		Assert.assertEquals("", "");
	}

	@Test
	void testGetUrl() {
		fail("Not yet implemented");
	}
}