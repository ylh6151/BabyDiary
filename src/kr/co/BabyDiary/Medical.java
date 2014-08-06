package kr.co.BabyDiary;

public class Medical {
	String no,date,symptom,diagnosis,medicineDay;
	
	public Medical(String no, String date, String symptom, String diagnosis, String medicineDay) {
		this.no = no;
		this.date = date;
		this.symptom = symptom;
		this.diagnosis = diagnosis;
		this.medicineDay = medicineDay;
	}

	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicineDay() {
		return medicineDay;
	}

	public void setMedicineDay(String medicineDay) {
		this.medicineDay = medicineDay;
	}
}
