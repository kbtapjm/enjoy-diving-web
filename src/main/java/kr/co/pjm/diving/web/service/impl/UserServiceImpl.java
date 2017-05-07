package kr.co.pjm.diving.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.repasitory.UserRepository;
import kr.co.pjm.diving.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public User getById(Long id) throws Exception {
    return userRepository.findOne(id);
  }

}
