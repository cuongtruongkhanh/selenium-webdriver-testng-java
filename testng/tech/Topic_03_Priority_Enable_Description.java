package tech;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {
	@Test(priority = 1)
	public void Product_01_Create() {
		
	}
	
	@Test(priority = 2)
	public void Product_02_View() {
		
	}
	
	@Test(priority = 3)
	public void Product_03_Edit() {
		
	}
	
	@Test(priority = 4, enabled = true, description = "User is able to delete item in product cart")
	public void Product_04_Delete() {
		
	}
	
	/*
	 Các TC không phải chạy từ trên xuống dưới
	 Ưu tiên:
	 - Theo Priority
	 - Nếu k set priority -> Theo bảng chữ cái Alphabet (0->9, A->Z)
	 enabled = false -> k chạy TC
	 Description: miêu tả cho các member non-technical  
	 
	 */
}
