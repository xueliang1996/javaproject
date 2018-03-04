package com.myjo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.myjo.data.StatusMapper;
import com.myjo.domain.Oc_Info;
import com.myjo.util.SessionFactoryUtil;

public class StatusMapperService implements StatusMapper {

	private StatusMapper statusMapper;
	
	public Oc_Info selectStatus(){
		// TODO Auto-generated method stub
		SessionFactoryUtil util = new SessionFactoryUtil();
		try {
			SqlSession session = util.getSqlSession();
			
			Oc_Info oc = session.selectOne("selectStatus");
			
			return oc;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}

	
}
