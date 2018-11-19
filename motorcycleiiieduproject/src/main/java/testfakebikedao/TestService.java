package testfakebikedao;

import java.io.IOException;
import java.text.ParseException;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	TestDAO testDAO;
	@Autowired
	SessionFactory factory;

	public TestService() {
		System.out.println("TestService start up ");
	}

	@Transactional
	public void makeFakeBranchDetail() throws ParseException, NumberFormatException, IOException {
		@SuppressWarnings("unused")
		Session session = factory.getCurrentSession();
		System.out.println("makeFakeBranchDetail start up  ");
		testDAO.makeFakeBranchDetail();

	}

	@Transactional
	public void makeFakeBikedetail_EveryBikeInfor() throws ParseException, NumberFormatException, IOException {
		@SuppressWarnings("unused")
		Session session = factory.getCurrentSession();
		System.out.println("makeFakeBikedetail_EveryBikeInfor start up  ");
		testDAO.makeFakeBikedetail_EveryBikeInfor();

	}

	@Transactional
	public void makeFakeOrderlist() throws ParseException, NumberFormatException, IOException {
		@SuppressWarnings("unused")
		Session session = factory.getCurrentSession();
		System.out.println("makeFakeBikedetail_EveryBikeInfor start up  ");
		testDAO.makeFakeOrderlist();

	}

	@Transactional
	public void makeFakeMaintenanceDetail() throws ParseException, NumberFormatException, IOException {
		@SuppressWarnings("unused")
		Session session = factory.getCurrentSession();
		System.out.println("makeFakeMaintenanceDetail start up  ");
		testDAO.makeFakeMaintenanceDetail();

	}

	@Transactional
	public void makeFakeBikeDescription() throws ParseException, NumberFormatException, IOException {
		@SuppressWarnings("unused")
		Session session = factory.getCurrentSession();
		System.out.println("makeFakeMaintenanceDetail start up  ");
		testDAO.makeFakeBikeDescription();

	}

	@Transactional
	public void createCriteria() {

		System.out.println("createCriteria go");

		testDAO.createCriteria();

	}

	@Transactional
	public void insertEveryBikeMileage() throws IOException {
		Session session = factory.getCurrentSession();
		System.out.println("EveryBikeMileage新增");

		testDAO.insertEveryBikeMileage();

	}
	@Transactional
	public void insertMemberDetail() throws IOException, ParseException {
		Session session = factory.getCurrentSession();
		System.out.println("MemberDetail新增");

		testDAO.insertMemberDetail();

	}
	@Transactional
	public void insertFakeAcceSerialNum() throws IOException, ParseException {
		Session session = factory.getCurrentSession();
		System.out.println("AcceSerialNum新增");

		testDAO.insertFakeAcceSerialNum();

	}
}
