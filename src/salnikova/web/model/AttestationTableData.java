package salnikova.web.model;

import java.util.LinkedList;
import java.util.List;

import salnikova.model.Attestation;
import salnikova.model.Student;

public class AttestationTableData {

	public List<Attestation> getAttestations(){
		return m_attestations;
	}
	
	
	public Student getStudent(){
		return m_student;
	}
	
	public void setStudent(final Student value){
		
	}
	
	private Student m_student;
	private List<Attestation> m_attestations = new LinkedList<>();
}
