package everybikeInfo.robin.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BikeDetailToGson;
import cleanbean.QaBean;
import cleanbean.QaBeanToJson;
import everybikeInfo.robin.service.BikeDetailIFaceService;
import everybikeInfo.robin.service.EveryBikeInfoIFaceService;
import everybikeInfo.robin.service.EveryBikeMileageIFaceService;
import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.IdClassBikeDetail;
import projectbean.QAndA;

@Repository
public class BikeDetailDao implements BikeDetailIFaceDao {
	@Autowired
	SessionFactory Factory;
	@Autowired
	BikeDetailIFaceService bikeDetailIFaceService;
	@Autowired
	EveryBikeInfoIFaceService everyBikeInfoIFaceService;
	@Autowired
	EveryBikeMileageIFaceService everyBikeMileageIFaceService;

	@Override
	public boolean isDup(String id) {
		Session session = Factory.getCurrentSession();
		boolean dup = false;
		String hgl = "FROM BikeDetail WHERE id=:id";
		List<BikeDetail> list = session.createQuery(hgl).setParameter("id", id).getResultList();
		if (list.size() > 0) {
			dup = true;
		}
		return dup;

	}

	@Override
	public int merge(BikeDetail bikeDetail) {
		Session session = Factory.getCurrentSession();
	
		int updateCount = 0;

		session.merge(bikeDetail);

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<BikeDetail> getAllMembers() {
		Session session = Factory.getCurrentSession();

		List<BikeDetail> allMembers = null;
		String hgl = "FROM BikeDetail";

		allMembers = session.createQuery(hgl).getResultList();

		return allMembers;

	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {

		Session session = Factory.getCurrentSession();
		int updateCount = 0;
		// 複合主鍵(bikeModel型號,modelYear年分) 一次輸入兩個
		IdClassBikeDetail idClassBikeDetail = new IdClassBikeDetail(bikeModel, modelYear);
		session.delete(idClassBikeDetail);
		updateCount++;

		return updateCount;
	}

	@Override
	public int updateBikeDetai(BikeDetailToGson bikeDetailToGson) {
		Session session = Factory.getCurrentSession();

		System.out.println(bikeDetailToGson.getBikeType());
		BikeDetail op = everyBikeInfoIFaceService.selectbikeModelmodelYear(bikeDetailToGson.getBikeModel(),
				bikeDetailToGson.getModelYear());
		Date QQ = op.getOnSheftTime();
		IdClassBikeDetail idClassBikeDetailnew = new IdClassBikeDetail(bikeDetailToGson.getBikeModel(),
				bikeDetailToGson.getModelYear());
		BikeDetail bike = new BikeDetail(idClassBikeDetailnew, bikeDetailToGson.getBikeBrand(),
				bikeDetailToGson.getEngineType(), bikeDetailToGson.getBikeType(), bikeDetailToGson.getPlateType(),
				bikeDetailToGson.getFuelTankCapacity(), bikeDetailToGson.getSeatHeight(),
				bikeDetailToGson.getDryWeight(), bikeDetailToGson.getFuelConsumption(), bikeDetailToGson.getTire(),
				bikeDetailToGson.getFuelType(), bikeDetailToGson.getaBS(), bikeDetailToGson.getHourPrice(), QQ,bikeDetailToGson.getFrontSuspension(),bikeDetailToGson.getRearSuspension()
				,bikeDetailToGson.getRearTire(),bikeDetailToGson.getHorsePower(),bikeDetailToGson.getTorque(),bikeDetailToGson.getFrontBrake(),bikeDetailToGson.getRearBrake());

//		BikeDetail bikeDetail=new BikeDetail(bikeDetailToGson.getBikeBrand()
//											,bikeDetailToGson.getEngineType(),bikeDetailToGson.getBikeType()
//											,bikeDetailToGson.getPlateType(),bikeDetailToGson.getFuelTankCapacity(),bikeDetailToGson.getSeatHeight()
//											,bikeDetailToGson.getDryWeight(),bikeDetailToGson.getFuelConsumption(),bikeDetailToGson.getTire()
//											,bikeDetailToGson.getFuelType(),bikeDetailToGson.getaBS(),bikeDetailToGson.getHourPrice());
//		String hql = "UPDATE BikeDetail set engineType=:engineType,bikeType=:bikeType,plateType=:plateType,fuelTankCapacity=:fuelTankCapacity,seatHeight=:seatHeight,dryWeight=:dryWeight,fuelConsumption=:fuelConsumption,tire=:tire,fuelType=:fuelType,ABS=:aBS,hourPrice=:hourPrice where bikeModel=:bikeModel and modelYear=:modelYear";
		int updateCount = 0;
		System.out.println(1);
//		Query ta = session.createQuery(hql).setParameter("engineType", bikeDetailToGson.getEngineType())
//				.setParameter("bikeType", bikeDetailToGson.getBikeType())
//				.setParameter("plateType", bikeDetailToGson.getPlateType())
//				.setParameter("fuelTankCapacity", bikeDetailToGson.getFuelTankCapacity())
//				.setParameter("seatHeight", bikeDetailToGson.getSeatHeight())
//				.setParameter("dryWeight", bikeDetailToGson.getDryWeight())
//				.setParameter("fuelConsumption", bikeDetailToGson.getFuelConsumption())
//				.setParameter("tire", bikeDetailToGson.getTire())
//				.setParameter("fuelType", bikeDetailToGson.getFuelType()).setParameter("aBS", bikeDetailToGson.getaBS())
//				.setParameter("hourPrice", bikeDetailToGson.getHourPrice())
//				.setParameter("bikeModel", bikeDetailToGson.getModelYear())
//				.setParameter("modelYear", bikeDetailToGson.getModelYear());

		// ta.executeUpdate();
		session.merge(bike);
		System.out.println(2);
		updateCount++;
		System.out.println(3);

		return updateCount;
	}

	@Override
	public int updateMember(BikeReview mb) {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public int insertQA(QaBean qaBean) {
		Session session = Factory.getCurrentSession();
		int count = 0;
		String BikeModel = qaBean.getBikeModel();
		String ModelYear = qaBean.getModelYear();
		String Questioner = qaBean.getQuestioner();
		String QuestionCoten = qaBean.getQuestionCoten();
		Date now = new Date();
		IdClassBikeDetail IdClassBikeDetail = new IdClassBikeDetail(BikeModel, ModelYear);
		BikeDetail bikeDetail = new BikeDetail(IdClassBikeDetail);
		QAndA QAndA = new QAndA(Questioner, QuestionCoten, bikeDetail, now);
		QAndA QAndA1 = new QAndA("小明", "回答得太棒了", bikeDetail, now);
		session.save(QAndA);
		count++;
		return count;
	}

	@Override
	public List<QAndA> selectQA() {
		Session session = Factory.getCurrentSession();
		String hql = "From QAndA";
		List<QAndA> po = session.createQuery(hql).getResultList();
		if (po.size() == 0) {
			System.out.println("QAndA:沒東西");
			return null;
		}
		return po;
	}
	@Override
	public List<QAndA> selectQAwhere(String BikeModel,String ModelYear) {
		Session session = Factory.getCurrentSession();
		String hql = "From QAndA where bikeModel=:bikeModel and modelYear=:modelYear";
		IdClassBikeDetail IdClassBikeDetail = new IdClassBikeDetail(BikeModel, ModelYear);
		BikeDetail bikeDetail = new BikeDetail(IdClassBikeDetail);
		List<QAndA> po = session.createQuery(hql).setParameter("bikeModel", bikeDetail).setParameter("modelYear", bikeDetail).getResultList();
		if (po.size() == 0) {
			System.out.println("QAndA:沒東西");
			return null;
		}
		return po;
	}

	@Override
	public List<QaBeanToJson> QaBeanToJson(List<QAndA> QAndA) {
		SimpleDateFormat Format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList<QaBeanToJson> QaBeanToJson = new ArrayList<QaBeanToJson>();
		String Answer = null;
		String Question = null;
		for (QAndA op : QAndA) {
			int QAndASerialNum=op.getQAndASerialNum();
			String Questioner = op.getQuestioner();
			String QuestionCotent = op.getQuestionCotent();
			String BikeModel = op.getBikeDetail().getIdClassBikeDetail().getBikeModel();
			String ModelYear = op.getBikeDetail().getIdClassBikeDetail().getModelYear();
			String AdministratorID = op.getAdministratorID();
			String AnswerContent = op.getAnswerContent();
			Date QuestionDate = op.getQuestionDate();
			Date AnswerTime = op.getAnswerTime();
			System.out.println("這段時間:" + AnswerTime);
			if (QuestionDate == null) {
				Question = null;
			} else {
				Question = Format.format(QuestionDate);
			}
			if (AnswerTime == null) {
				Answer = null;
			} else {
				Answer = Format.format(AnswerTime);
			}
			String QuestionDate1 = Question;// 時間轉為文字
			String AnswerTime1 = Answer;// 時間轉為文字
			QaBeanToJson.add(new QaBeanToJson(QAndASerialNum,Questioner, QuestionCotent, BikeModel, ModelYear, AdministratorID,
					AnswerContent, QuestionDate1, AnswerTime1));
		}
		return QaBeanToJson;
	}

	@Override
	public int updateQA(int qAndASerialNum, String ans, String ansquction) {
		int count=0;
		Session session = Factory.getCurrentSession();
		Date now=new Date();
		String hql = "update QAndA set administratorID=:administratorID,answerContent=:answerContent,answerTime=:answerTime where QAndASerialNum=:QAndASerialNum";
		 session.createQuery(hql).setParameter("administratorID", ans).setParameter("answerContent", ansquction).setParameter("answerTime", now).setParameter("QAndASerialNum", qAndASerialNum).executeUpdate();
		count++;
		return count;
	}

	

}
