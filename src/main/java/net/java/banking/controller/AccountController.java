package net.java.banking.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.banking.dto.AccountDto;
import net.java.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	//aDD
	@PostMapping
	
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
		
	}
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//deposit
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
			                                  @RequestBody Map<String,Double> request){
		Double amount=request.get("amount");
		
		AccountDto accountDto=accountService.deposit(id,amount);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//withdraw
      @PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
            @RequestBody Map<String,Double> request){
        Double amount=request.get("amount");
		
		AccountDto accountDto=accountService.withdraw(id,amount);
		return ResponseEntity.ok(accountDto);
		
	}
      
     
  	//get all
      @GetMapping
      public ResponseEntity<List<AccountDto>> getAllAccounts()
      {
    	 List<AccountDto> accounts= accountService.getAllAccounts();
    	 return ResponseEntity.ok(accounts);
    	 
      }
      @DeleteMapping("/{id}")
      public ResponseEntity<String> deleteById(@PathVariable Long id){
    	  accountService.deleteById(id);
  		
  		return ResponseEntity.ok("deleted successfully");
  		
  	}
}
