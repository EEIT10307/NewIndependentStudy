package branchscenedao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BranchScenesForJson;
import cleanbean.EveryBikeInfoToGson;
import projectbean.BranchDetail;
import projectbean.BranchScenes;
import projectbean.EveryBikeInfo;

@Repository
public class BranchScenesDao implements BranchScenesIFaceDao{

	@Autowired
	SessionFactory factory;
//	@Autowired
//	BranchScenes spotName;
	
	@Override
	public List<BranchScenes> selectBranchScenes(String spotName) {
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchScenes> createQuery = buider.createQuery(BranchScenes.class);
		Root<BranchScenes> fromClass = createQuery.from(BranchScenes.class);
		ParameterExpression<String> branchName = buider.parameter(String.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("branchName"), branchName));
		Query<BranchScenes> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(branchName, spotName);
		List<BranchScenes> list = queryword.getResultList();

//		for (OrderList loop : list) {
//			System.out.println("showAllOrderFromShop ="+loop.getBikeModel()+":"+loop.getPickupDate()+"=>"+loop.getDropoffDate());
//		}
		return list;
	}

	@Override
	public List<BranchScenesForJson> getBranchScenes() {
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchScenes> createQuery = buider.createQuery(BranchScenes.class);
		Root<BranchScenes> fromClass = createQuery.from(BranchScenes.class);
		createQuery.select(fromClass);
		List<BranchScenes> sceneslist = factory.getCurrentSession().createQuery(createQuery).getResultList();
		Map<String,String> spotlist = null;
		List<BranchScenesForJson> branchScenesForJson=new ArrayList<BranchScenesForJson>();
		for (BranchScenes loop : sceneslist) {
//			BranchScenesForJson branchScenes=new BranchScenesForJson();
//			branchScenes.setSpotName(loop.getSpotName());
//			branchScenes.setSpotAddress(loop.getSpotAddress());
//			branchScenes.setSpotArea(loop.getSpotArea());
//			branchScenes.setSpotDetail(loop.getSpotDetail());
//			branchScenes.setSpotPhoto(loop.getSpotPhoto());
//			branchScenes.setBranchName(loop.getBranchName().getBranchName());
//			
//			branchScenesForJson.add(branchScenes);

		}
		return branchScenesForJson;
	}

	@Override
	public List<BranchScenes> getScenes() {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchScenes> createQuery = builder.createQuery(BranchScenes.class);
		Root<BranchScenes> fromClass = createQuery.from(BranchScenes.class);
		createQuery.select(fromClass);
		List<BranchScenes> scenes = factory.getCurrentSession().createQuery(createQuery).getResultList();
		return scenes;
	}

	@Override
	public int saveBranchScenes() throws MalformedURLException, IOException {
		 URL url = new URL("https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=36847f3f-deff-4183-a5bb-800737591de5&format=xml");
		  //  URL url = new URL("https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=35aa3c53-28fb-423c-91b6-2c22432d0d70&format=xml");
			        Document xmlDoc =  Jsoup.parse(url, 50000);//使用Jsoup jar 去解析網頁
			        Elements stitle = xmlDoc.select("stitle");
			        Elements xbody = xmlDoc.select("xbody");
			        Elements address = xmlDoc.select("address");
			        Elements file = xmlDoc.select("file");
			       //(要解析的文件,timeout)
//			       System.out.println(xmlDoc.toString());
			       int updateCount = 0;
			       for(int i=0; i<stitle.size(); i++) {
			    	   BranchScenes branchScenes = new BranchScenes();
			    	   Session session=factory.getCurrentSession();
			    	   int jpgPosition = file.get(i).text().replaceAll("JPG", "jpg").replaceAll("png", "jpg").indexOf("jpg");//抓第一次遇到jpg或png的位子
			    	   branchScenes.setSpotName(stitle.get(i).text());
			    	   branchScenes.setSpotArea(address.get(i).text().substring(4,7));//ex:中山區
			    	   branchScenes.setSpotAddress(address.get(i).text());
			    	   branchScenes.setSpotDetail(xbody.get(i).text());
			    	   branchScenes.setSpotPhoto(file.get(i).text().replace("JPG", "jpg").substring(0,jpgPosition+3));//取第一張圖片網址，png不能取代為jpg會破圖
			    	   session.saveOrUpdate(branchScenes);
			    	   updateCount += 1;
			       }
		return updateCount;
	}

	@Override
	public List<BranchScenes> showBranchScenes(String spotArea) {
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchScenes> createQuery = buider.createQuery(BranchScenes.class);
		Root<BranchScenes> fromClass = createQuery.from(BranchScenes.class);
		System.out.println("select before");
		createQuery.select(fromClass).where(buider.equal(fromClass.get("spotArea"), spotArea));
		System.out.println("select after");
		Query<BranchScenes> querySpot = factory.getCurrentSession().createQuery(createQuery);
		List<BranchScenes> list = querySpot.getResultList();
		System.out.println("showBranchScenes");
		return list;
	}

	@Override
	public List<BranchScenesForJson> showBranchScenesGson(List<BranchScenes> finalShowAreaSpot) {
		ArrayList<BranchScenesForJson> showBranchScenesGson=new ArrayList<BranchScenesForJson>();
		System.out.println("gson before");
		for(BranchScenes loop:finalShowAreaSpot) {
			System.out.println("66666666 ="+loop.getBranchName().getBranchName());
			showBranchScenesGson.add(new BranchScenesForJson(loop.getBranchName().getBranchName(),loop.getSpotArea(),
			loop.getSpotName(),loop.getSpotAddress(),loop.getSpotPhoto(),loop.getSpotDetail()));
		}
		System.out.println("gson after");
		System.out.println("showBranchScenesGson");
		return showBranchScenesGson;
	}
}
