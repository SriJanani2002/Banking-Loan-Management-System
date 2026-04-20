package com.bank.loanmanagement.repository;

import com.bank.loanmanagement.model.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, Long> {

	List<LoanRepayment> findByLoanId(Long loanId);
}