package com.shfb.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shfb.common.entity.Users;

@Service
public class UsersService extends BaseService {
//	@Autowired
//	@Transactional
//	public List<Object> getAllList(){
//		List<Object> list = getAllList("com.shfb.common.entity.Users");
//		return list;
//	}
	public boolean isExist(String username, String password){
		List<Object> list = findByWhere("com.shfb.common.entity.Users", new String[]{"username","passwd"}, new String[] {username,password});
		return list!=null?true:false;
	}
	
	public Users login(String username,String password){
		List<Object> list = findByWhere("com.shfb.common.entity.Users", new String[]{"username","passwd"}, new String[] {username,password});
		return list==null?null:(Users)list.get(0);
	}
	
	public Users findUser(String username){
		List<Object> list = findByWhere("com.shfb.common.entity.Users", new String[]{"username"}, new String[] {username});
		return list==null?null:(Users)list.get(0);
	}
}
