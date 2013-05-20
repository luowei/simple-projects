package com.rootls.service;

import java.util.List;

import com.rootls.bean.FJSearchBean;
import com.rootls.model.ContractFJ;

public interface ContractFJService {
	List<ContractFJ> getContractFjByCodition(FJSearchBean fjSearchBean,
                                             String adminId);

	int getPageCountByCondition(FJSearchBean fjSearchBean, String adminId);

	ContractFJ getContractById(Integer id);

	void updateContract(Integer id, Integer type);

	Boolean updateOptionBeforeCheck(String adminID, String fjId);

	Boolean deleteOptionBeforeCheck(String adminID, String fjId);

	void updateContractFile(ContractFJ contractFj);

	void deleteContract(String id);
}
