package com.rootls.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rootls.bean.FJSearchBean;
import com.rootls.model.ContractFJ;
import com.rootls.repository.ContractFJRepository;
import com.rootls.service.ContractFJService;

@Service
public class ContractFJServiceImpl implements ContractFJService {

	Logger logger = Logger.getLogger(ContractFJServiceImpl.class);

	@Resource
	ContractFJRepository contractFJRepository;

	@Override
	public List<ContractFJ> getContractFjByCodition(FJSearchBean fjSearchBean,String adminId) {
		return contractFJRepository.getContractFjByCodition(fjSearchBean,adminId);
	}

	@Override
	public int getPageCountByCondition(FJSearchBean fjSearchBean,String adminId) {
		return contractFJRepository.getPageCountByCondition(fjSearchBean,adminId);
	}

	@Override
	public ContractFJ getContractById(Integer id) {
		return contractFJRepository.getContractById(id);
	}

	@Override
	public void updateContract(Integer id, Integer type) {
		contractFJRepository.updateContract(id, type);

	}

	@Override
	public Boolean deleteOptionBeforeCheck(String adminID, String fjId) {
		return contractFJRepository.deleteOptionBeforeCheck(adminID, fjId);
	}

	@Override
	public Boolean updateOptionBeforeCheck(String adminID, String fjId) {
		return contractFJRepository.updateOptionBeforeCheck(adminID, fjId);
	}

	@Override
	public void updateContractFile(ContractFJ contractFj) {
		contractFJRepository.updateContractFile(contractFj);

	}

	@Override
	public void deleteContract(String id) {
		contractFJRepository.deleteContract(id);
	}

}
