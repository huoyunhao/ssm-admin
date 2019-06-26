package cc.aies.web.fifter;

import cc.aies.web.beans.MenuPermission;
import cc.aies.web.service.MenuService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 这是一个shiro 过滤器类
 * 从数据库动态的更新权限
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    private static final String ROLE_STRING = "roles[{0}]";
    private String filterChainDefinitions;
    @Autowired
    private MenuService menuService;

    @Override
    //重写过滤链 动态维护
    public void setFilterChainDefinitions(String definitions) {
        filterChainDefinitions = definitions;
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }
        //模拟从数据库中读取的数据
//        Map<String, String[]> permsMap = new HashMap<>();
//        permsMap.put("/dotest1.html", new String[]{"test"});
//        permsMap.put("/dotest2.html", new String[]{"test", "guest"});
        //permsMap.put("/dotest3.html", new String[]{"admin"});
        //得到角色所对应的菜单id
        List<MenuPermission> menuPermissions = menuService.getMenuPerms();
        System.out.println(menuPermissions);
        if (menuPermissions != null) {
            for (MenuPermission menuPermission : menuPermissions) {
                List<String> roleIds = menuPermission.getRoleIds();
                if (StringUtils.hasLength(menuPermission.getUrl()) && roleIds != null && roleIds.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (String role : roleIds) {
                        sb.append(role).append(",");
                    }
                    String str = sb.substring(0, sb.length() - 1);
                    //MessageFormat.format(ROLE_STRING, str)==roles[test,guest]
                    section.put(menuPermission.getUrl(), MessageFormat.format(ROLE_STRING, str)); //将那个url 有那个角色对应 存入 section
                }
            }
        }
        section.put("/**", "authc");
        this.setFilterChainDefinitionMap(section);
    }
    //    动态更新权限的方法
    public void update() {
        synchronized (this) {
            try {
                AbstractShiroFilter shiroFilter =
                        (AbstractShiroFilter) this.getObject();
                PathMatchingFilterChainResolver resolver =
                        (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
                manager.getFilterChains().clear();
                this.getFilterChainDefinitionMap().clear();

                this.setFilterChainDefinitions(filterChainDefinitions); //重置过滤链

                Map<String, String> chains = this.getFilterChainDefinitionMap();
                if (!CollectionUtils.isEmpty(chains)) {
                    Iterator var12 = chains.entrySet().iterator();

                    while (var12.hasNext()) {
                        Map.Entry<String, String> entry = (Map.Entry) var12.next();
                        String url = (String) entry.getKey();
                        String chainDefinition = (String) entry.getValue();
                        manager.createChain(url, chainDefinition);     //回复过滤链
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
