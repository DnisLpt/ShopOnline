package shoponline.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shoponline.models.Uzer;
import shoponline.repository.UzerRepository;
@Service
public class SecurityUserDetaillService implements UserDetailsService {

    @Autowired
    private UzerRepository uzerRepository;

    public void UzerAuthService(UzerRepository uzerRepository) {
        this.uzerRepository = uzerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Uzer user = this.uzerRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        UserAuth userAuth = new UserAuth(user);
        return userAuth;
    }
}
