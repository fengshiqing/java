package com.itheima.dao;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.itheima.domain.User;
import com.itheima.util.XmlDaoUtils;

public class XmlUserDao implements UserDao {
	
	public User findUserByUserName(String username){
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		//在xml中查找具有username属性值等于传入的用户名的元素
		List<Element> list = root.selectNodes("//user[@username='"+username+"']");
		if(list.size()>0){//大于0说明找到了这个用户
			Element userEle = list.get(0);
			//将找到的用户信息封装到bean后返回
			User user = new User();
			user.setUsername(userEle.attributeValue("username"));
			user.setPassword(userEle.attributeValue("password"));
			user.setNickname(userEle.attributeValue("nickname"));
			user.setEmail(userEle.attributeValue("email"));
			return user;
		}else{//说明没有找到这个用户
			return null;
		}
	}
	
	public void addUser(User user){
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		//凭空创建出一个<user>元素,根据传入的user信息,设置此元素的属性
		Element userEle = DocumentHelper.createElement("user");
		userEle.setAttributeValue("username", user.getUsername());
		userEle.setAttributeValue("password", user.getPassword());
		userEle.setAttributeValue("nickname", user.getNickname());
		userEle.setAttributeValue("email", user.getEmail());
		//挂载到<users>元素上
		root.add(userEle);
		//回写到xml文件中
		XmlDaoUtils.refXml();
	}
	
	public User findUserByUNandPSW(String username,String password){
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		//在xml中查找具有username属性值等于传入的用户名 并且 password 等于传入密码的元素
		List<Element> list = root.selectNodes("//user[@username='"+username+"' and @password='"+password+"']");
		if(list.size()>0){//大于0说明找到了这个用户
			Element userEle = list.get(0);
			//将找到的用户信息封装到bean后返回
			User user = new User();
			user.setUsername(userEle.attributeValue("username"));
			user.setPassword(userEle.attributeValue("password"));
			user.setNickname(userEle.attributeValue("nickname"));
			user.setEmail(userEle.attributeValue("email"));
			return user;
		}else{//说明没有找到这个用户
			return null;
		}
	}
}
