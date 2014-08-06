package kr.co.BabyDiary;

public class hospital {
	
	String name, tel, address1, address2;
	
	public hospital(String name, String tel, String address1, String address2) {
		this.name = name;
		this.tel = tel;
		this.address1 = address1;
		this.address2 = address2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	

}
