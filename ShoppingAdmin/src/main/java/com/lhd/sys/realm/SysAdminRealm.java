package com.lhd.sys.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.lhd.sys.common.ActiverUser;
import com.lhd.sys.common.Constast;
import com.lhd.sys.entity.SysPermission;
import com.lhd.sys.entity.SysUser;
import com.lhd.sys.service.SysMenuService;
import com.lhd.sys.service.SysUserService;
import com.lhd.sys.untils.MD5AndSalt;

public class SysAdminRealm extends AuthorizingRealm {
	
	
	@Autowired
	@Lazy
	private SysUserService  sysUserService ;
	
	@Autowired
	@Lazy
	private SysMenuService sysMenuService ;
	
	/**
	 * 重写方法
	 */
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * 
	 * 认证
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		//1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken) arg0 ;
		String pwd = MD5AndSalt.MD5(String.valueOf(token.getPassword())) ;
		
		//UsersTable user = new UsersTable() ;
		SysUser sysUser = new SysUser() ;
		sysUser.setName(token.getUsername()) ;
		sysUser.setPwd(pwd) ;
		//String string = String.valueOf(token.getPassword()) ;
		//String pwdSalt = MD5AndSalt.generate(token.getPassword().toString()) ;
		/*user.setUsername(token.getUsername()) ;
		user.setPassword(pwd);*/
		
	/*	UsersTable user2 = userService.loginUser(user) ;*/
		 SysUser user = sysUserService.userLogin(sysUser.getName(), sysUser.getPwd()) ;
			System.out.println(user);
			if ( null == user ) {
				//用户信息错误
				System.out.println("执行认证方法");
				return null ; //Shiro 底层会抛出UnknowAccountException
			} else {
					ActiverUser activerUser = new ActiverUser();
					activerUser.setSysUser(user);
					
					//根据用户ID查询percode
					//查询所有菜单
					SysPermission permission = new SysPermission() ;
					//设置只能查询菜单
					permission.setAvailable(Constast.AVAILABLE_TRUE) ;
					permission.setType(Constast.TYPE_PERMISSION) ;
					List<SysPermission> list = null ;
					//根据用户ID+角色+权限去查询 
					Integer userId=user.getId();
					//根据用户查询sys_user_role表中rid
					List<Integer> findIds = this.sysUserService.findIds(userId) ;
					//根据角色表中的rid查询sys_role_permission表中的pid 
					if ( findIds.size() > 0 ) { //当前管理员是否获取了 菜单和权限
						List<Integer> findPidByRid = this.sysUserService.findPidByRid(findIds) ;
						//根据pid 查询sys_permission表中的id
						if ( findPidByRid.size() > 0 ) {
							list = this.sysMenuService.findByPidOfPermission(permission , findPidByRid) ;
						} else {
							list = new ArrayList<>() ;
						}
					} else {
						list = new ArrayList<>() ;
					}
					List<String> percodes=new ArrayList<>();
					for (SysPermission pe : list) {
						percodes.add(pe.getName());
					}
					//放到
					activerUser.setPermissions(percodes);

				//判断密码   
				return new SimpleAuthenticationInfo(activerUser, token.getPassword(), "") ;
			}
	}
	
	
	
	/**
	 * 
	 * 授权
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权方法");
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		ActiverUser activerUser=(ActiverUser) principals.getPrimaryPrincipal();
		SysUser user=activerUser.getSysUser() ;
		List<String> permissions = activerUser.getPermissions();
		System.out.println(permissions+"::::::权限");
		if(user.getType()==Constast.USER_TYPE_SUPER) {
			authorizationInfo.addStringPermission("*:*");
		}else {
			if(null!=permissions&&permissions.size()>0) {
				authorizationInfo.addStringPermissions(permissions);
			}
		}
		System.out.println(authorizationInfo.getStringPermissions().toString()+"::::::::");
		return authorizationInfo;
	}


}
