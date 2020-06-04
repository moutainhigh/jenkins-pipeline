package com.creditharmony.approve.users.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.service.OrgManager;
import com.creditharmony.core.users.service.UserManager;


/**
 * 用户View和Entity转换层
 * @Class Name UserFacade
 * @author 张永生
 * @Create In 2015年11月27日
 */
@Service
public class UserFacade {

	@Autowired
	private UserManager userManager;
	@Autowired
	private OrgManager orgManager;
	
	public List<Org> queryOrg(StringBuffer result, String parentId, int level){
		
		return null;
//		{
//		  "id": "1",
//		  "title": "Node 1",
//		  "has_children": "1",
//		  "children": [
//		    {
//		      "id": "11",
//		      "title": "Node 11",
//		      "has_children": "1",
//		      "children": [
//
//		      ]
//		    },
//		  ]
//		}
		
		
//		if(result.length()==0){
//			result.append("{");
//		}
//		List<Org> dataList = orgManager.loadOrg(parentId);
//		for(Org item : dataList){
//			result.append("id");
//			boolean hasChild = orgManager.isHasChildren(item.getId());
//			if (hasChild) {
//				if(level<3){
//					queryOrg(dataList, parentId, ++level);
//				}
//			} else {
//				return dataList;
//			}
//		}
//		return dataList;
		
		
	}
	
}
