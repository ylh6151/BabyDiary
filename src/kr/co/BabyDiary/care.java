package kr.co.BabyDiary;

public class care {
	String id;
	String month;
	String vac;
	String bogun;
	String etc;
	String complate, comdate;
	
	public care(String id, String month, String vac, String bogun, String etc) {
		this.id = id;
		this.month = month;
		this.vac = vac;
		this.bogun = bogun;
		this.etc = etc;
	}

	public String getComplate() {
		return complate;
	}

	public void setComplate(String complate) {
		this.complate = complate;
	}

	public String getComdate() {
		return comdate;
	}

	public void setComdate(String comdate) {
		this.comdate = comdate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBogun() {
		return bogun;
	}

	public void setBogun(String bogun) {
		this.bogun = bogun;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getVac() {
		return vac;
	}

	public void setVac(String vac) {
		this.vac = vac;
	}
	
}