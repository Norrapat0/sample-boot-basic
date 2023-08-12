package th.mfu;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/roles")
     public Collection<Role> getAllEmployees() {
        return roleRepository.findAll();
    }
    // create method for role
    @PostMapping("/roles")
    public ResponseEntity createRole(@RequestBody Role role){
        roleRepository.save(role);
        return ResponseEntity.ok(role);
    }


    //set user role for account
    @PutMapping("/accounts/{accountid}/roles/{roleid}")
    public ResponseEntity setRoleToAccount(@PathVariable Long accountid, @PathVariable Long roleid){
        // search account by id
        Optional<Account> accountopt = accountRepository.findById(accountid);
        // check if account exists
        if(!accountopt.isPresent()){
            // return error message 404
            return ResponseEntity.notFound().build();
        }
        Account account = accountopt.get();
        // add role to account
        Optional<Role> roleopt = roleRepository.findById(roleid);
        // check if role exists
        if(!roleopt.isPresent()){
            // return error message 404
            return ResponseEntity.notFound().build();
        }
        Role role = roleopt.get();
        account.getRoles().add(role);
        // save account
        accountRepository.save(account);
        return ResponseEntity.ok("set role to account");
      
    }
}
