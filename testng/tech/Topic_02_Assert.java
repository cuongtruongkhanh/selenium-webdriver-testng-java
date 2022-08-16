package tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
	
	@Test
	public void TC_01() {
		// Thư viện Assert: kiểm tra tính đúng đắn của dữ liệu
		// Mong đợi nó đúng/ sai/ đầu vào và đầu ra như nhau
		// Bằng null/ khác null/ ...
		
		//Kiểm tra 1 điều kiện đúng -> Assert.assertTrue
		//Kiểm tra 1 điều kiện sai -> Assert.assertFalse
		
		//Kiểm tra dữ liệu đầu vào và đầu ra như nhau -> Assert.assertEquals
		
		//Unit / Integration Test: Null, not Null
		//Kiểm tra dữ liệu ban đầu null, sau đó check not null (đã khởi tạo dữ liệu)
		Object fullname = null;
		Object notNullName = "automation";
		
		Assert.assertNull(fullname);
		Assert.assertNotNull(notNullName);
		
		Topic_02_Assert topic02 = null;
		Assert.assertNull(topic02);
		
		topic02 = new Topic_02_Assert();
		Assert.assertNotNull(topic02);
		
		
	}
	
}
