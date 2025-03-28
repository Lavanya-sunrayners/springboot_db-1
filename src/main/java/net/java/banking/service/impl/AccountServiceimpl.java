package net.java.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.java.banking.dto.AccountDto;
import net.java.banking.entity.Account;
import net.java.banking.mapper.AccountMapper;
import net.java.banking.repository.AccountRepository;
import net.java.banking.service.AccountService;


@Service
public class AccountServiceimpl implements AccountService{
	
	private AccountRepository accountRepository;
	

	public AccountServiceimpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		
		Account account=AccountMapper.maptoAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.maptoAccountDto(savedAccount);
	}


    @Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		
		Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesnot exist"));
		return AccountMapper.maptoAccountDto(account);
	}
    
    
  @Override
	public AccountDto deposit(Long id, double amount) {
	
		Account account=accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesnot exist"));
		
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.maptoAccountDto(savedAccount);


		
		
	}


@Override
public AccountDto withdraw(Long id, double amount) {
	// TODO Auto-generated method stub
	Account account=accountRepository
			.findById(id)
			.orElseThrow(() -> new RuntimeException("Account doesnot exist"));
	
	if(account.getBalance() < amount) {
		throw new RuntimeException("insuffficient balance");
	}
	double total=account.getBalance()-amount;
	account.setBalance(total);
	Account savedAccount=accountRepository.save(account);
	return AccountMapper.maptoAccountDto(savedAccount);

	
	
}


@Override
public void deleteById(Long id) {
	// TODO Auto-generated method stub
	
	Account account=accountRepository
			.findById(id)
			.orElseThrow(() -> new RuntimeException("Account doesnot exist"));
	
	accountRepository.deleteById(id);

}


@Override
public List<AccountDto> getAllAccounts() {
	// TODO Auto-generated method stub
	List<Account> accounts = accountRepository.findAll();
	return accounts.stream().map((account)-> AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());
	
}
	

}
