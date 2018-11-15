package everybikeInfo.robin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BackService implements BackIFaceService {
	@Autowired
	BackIFaceDao backIFaceDao;

	@Override
	public String back(String Mess) {
		// TODO Auto-generated method stub
		return backIFaceDao.back(Mess);
	}

}
