package org.dynaresume.hr.service.impl;
import java.util.List;

import org.dynaresume.hr.repository.ResumeRepository;
import org.dynaresume.hr.domain.Resume;
import org.dynaresume.hr.service.HRService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service(value="hrService")
public class HRServiceImpl implements HRService {

	@Autowired
	private ResumeRepository resumeRepository;

	public List<Resume> findAll() {
		
		return resumeRepository.findAll();
	}

}
