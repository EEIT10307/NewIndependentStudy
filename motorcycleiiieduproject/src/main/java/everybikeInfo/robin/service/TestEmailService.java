package everybikeInfo.robin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import everybikeInfo.robin.dao.TestEmailIFaceDao;
@Service
@Transactional
public class TestEmailService implements TestEmailIFaceService{
@Autowired
TestEmailIFaceDao testEmailIFaceDao;
	@Override
	public int sendemail(String or, String em) {
		// TODO Auto-generated method stub
		return testEmailIFaceDao.sendemail(or, em);
	}

}
