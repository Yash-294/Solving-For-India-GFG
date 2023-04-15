package com.gfgsih.emrsys.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gfgsih.emrsys.entities.Doctor;
import com.gfgsih.emrsys.repository.DoctorRepository;

@Service
public class DoctorAuthService implements UserDetailsService {
	private DoctorRepository docrepo;
	
	@Autowired
	public DoctorAuthService(DoctorRepository docrepo) {
		super();
		this.docrepo=docrepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String docid) throws UsernameNotFoundException{
		Doctor doc=docrepo.findFirstByDocid(docid);
		if(doc!=null) {
			List<GrantedAuthority> authority=new ArrayList<>();
			authority.add(new SimpleGrantedAuthority("ROLE_DOCTOR"));
			User authUser=new User(doc.getDocid(),doc.getDoc_pass(),authority);
			return authUser;
		}
		else {
			throw new UsernameNotFoundException("Invalid UserId/Password");
		}
	}
}
