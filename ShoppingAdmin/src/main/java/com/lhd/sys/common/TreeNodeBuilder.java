package com.lhd.sys.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {
	
	
	
	/**
	 * 
	 * 把没有层级关系的集合变成有层级关系的集合
	 * @param treeNode
	 * @param pid
	 * @return
	 */
	public static List<TreeNode> build( List<TreeNode> treeNodes , Integer topPid ) {
		List<TreeNode> nodes = new ArrayList<TreeNode>() ;
		
		for (TreeNode n1 : treeNodes) {
			if ( n1.getPid() == topPid ) {
				nodes.add(n1) ;
			}
			//在 当前循环中 判断是否有n1.getId()的子节点 添加在节点
			for (TreeNode n2 : treeNodes) {
				if( n1.getId() == n2.getPid() ) {
					n1.getChildren().add(n2) ;
				}
			}
		}
		
		return nodes ;
		
	}

}
